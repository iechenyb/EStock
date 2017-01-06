package com.cyb.jbpm;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyb.jbpm.vo.ProcessDefinitionVo;
import com.cyb.jbpm.vo.ProcessInstanceVo;
import com.cyb.jbpm.vo.TaskVo;
import com.cyb.qutoes.constant.QutoesContants;
import com.cyb.utils.DateUtil;
import com.cyb.utils.FileUtils;

//@RequestMapping("/jbpm")
//@Controller
public class JpbmController {
	//@Resource(name = "taskService")
	public TaskService taskService;
	//@Resource(name = "processEngine")
	public ProcessEngine processEngine;
	//@Resource(name = "executionService")
	public ExecutionService executionService;
	//@Resource(name = "repositoryService")
	public org.jbpm.api.RepositoryService repositoryService;

	@RequestMapping("/deploy")
	public ModelAndView deploy(String file) {
		ModelAndView view = new ModelAndView();
		view.setViewName("jbpm/list");
		String path = QutoesContants.WEBPATH + "jbpm/" + file;
		System.out.println("deploy file is :" + path);
		repositoryService.createDeployment()
				.addResourceFromFile(new File(path)).deploy();
		// repositoryService.createDeployment().addResourceFromClasspath(file).deploy();
		System.out.println("deploy success!");
		return view;
	}

	@RequestMapping("/start")
	public ModelAndView startProcess(String id) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("content", "bad");
		System.out.println("id====" + id);
		ExecutionService executionService = processEngine.getExecutionService();
		/** 根据流程的key来创建一个流程实例 */
		// ProcessInstance pi =
		// executionService.startProcessInstanceByKey("draw_key",variables);
		ProcessInstance pi = executionService.startProcessInstanceById(
				"draw_key-1", variables);
		System.out.println(pi);
		// 检验流程实例pi，是否结束了
		System.out.println(pi.isEnded());
		// 当流程处理等待时，我们可以手动让其向下运行
		// 参数是根据流程实例的ID
//		pi = executionService.signalExecutionById(pi.getId());
//		System.out.println(pi.isEnded());
		ModelAndView view = new ModelAndView();
		view.setViewName("jbpm/list");
		return view;
	}

	@RequestMapping("/del")
	public ModelAndView deleteDeploy(String deploymentId) {
		repositoryService.deleteDeploymentCascade(deploymentId);
		ModelAndView view = new ModelAndView();
		view.setViewName("jbpm/list");
		return view;
	}

	@ResponseBody
	@RequestMapping("/lst")
	public JSONArray showList() {
		List<ProcessDefinitionVo> data = new ArrayList<ProcessDefinitionVo>();
		// 2.1创建一个流程搜索类
		ProcessDefinitionQuery processDefinitionQuery = repositoryService
				.createProcessDefinitionQuery();
		// 2.2搜索流程定义
		List<ProcessDefinition> processDefitionList = processDefinitionQuery
				.list();
		// 2.3循环输出流程定义ID（看看和流程定义文件：jpdl.xml的name哦）
		ProcessDefinitionVo obj = null;
		for (ProcessDefinition pd : processDefitionList) {
			obj = new ProcessDefinitionVo();
			System.out.println("流程ID：" + pd.getId());
			obj.setDeploymentId(pd.getDeploymentId());
			obj.setId(pd.getId());
			obj.setPicName(pd.getImageResourceName());
			obj.setKey(pd.getKey());
			obj.setVersion(pd.getVersion() + "");
			data.add(obj);
		}

		List<ProcessInstance> instances = executionService
				.createProcessInstanceQuery().list();
		List<ProcessInstanceVo> data_proc = new ArrayList<ProcessInstanceVo>();
		System.out.println(instances);
		ProcessInstanceVo obj2 = null;
		if (instances != null && instances.size() > 0) {
			for (ProcessInstance vo : instances) {
				obj2 = new ProcessInstanceVo();
				obj2.setId(vo.getId());
				obj2.setName(vo.getName());
				obj2.setKey(vo.getKey());
				obj2.setState(vo.getState());
				obj2.setProcessDefinitionId(vo.getProcessDefinitionId());
				data_proc.add(obj2);
			}
		}
		Map ret = new HashMap();
		ret.put("data", data);
		ret.put("data2", data_proc);
		ret.put("data3", myTasks());
		return JSONArray.fromObject(ret);
	}

	@RequestMapping("/image")
	public ModelAndView showPic(String id) {
		ModelAndView view = new ModelAndView();
		view.setViewName("jbpm/jbpmPic");
		ProcessInstance processInstance = executionService
				.findProcessInstanceById(id);
		Set<String> activityName = processInstance.findActiveActivityNames();// [b]
																				// taskname
		ActivityCoordinates ac = repositoryService.getActivityCoordinates(
				processInstance.getProcessDefinitionId(), activityName
						.iterator().next());
		System.out.println("x = " + ac.getX());
		view.addObject("x", ac.getX());
		view.addObject("y", ac.getY());
		view.addObject("w", ac.getWidth());
		view.addObject("h", ac.getHeight());
		view.addObject("id", id);
		String path = QutoesContants.WEBPATH + "jbpm"+File.separator+"process.png";
		view.addObject("path", path);
		return view;
	}

	@RequestMapping("/image1")
	public ModelAndView showPic1(String id) {
		ModelAndView view = new ModelAndView();
		view.setViewName("jbpm/jbpmPic");
		ProcessInstance processInstance = executionService
				.findProcessInstanceById(id);
		String processDefinitionId = processInstance.getProcessDefinitionId();
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).uniqueResult();
		InputStream inputStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(), "process.png");
		String path = QutoesContants.WEBPATH + "jbpm//process.png";
		System.out.println("path:" + path);
		FileUtils.copyFileByStream(inputStream, path);
		Set<String> activityName = processInstance.findActiveActivityNames();// [b]
																				// taskname
		ActivityCoordinates ac = repositoryService.getActivityCoordinates(
				processInstance.getProcessDefinitionId(), activityName
						.iterator().next());
		System.out.println("x = " + ac.getX());
		System.out.println("y = " + ac.getY());
		System.out.println("w = " + ac.getWidth());
		System.out.println("h = " + ac.getHeight());
		view.addObject("x", ac.getX());
		view.addObject("y", ac.getY());
		view.addObject("w", ac.getWidth());
		view.addObject("h", ac.getHeight());
		view.addObject("id", id);
		view.addObject("path", path);
		return view;
	}
	public List<TaskVo> myTasks(){
		List<Task> taskList = taskService.findPersonalTasks("chenyb"); 
		List<TaskVo> ret = new ArrayList<TaskVo>();
		TaskVo task = null;
		if(taskList!=null&&taskList.size()>0){
			for(Task vo:taskList){
			task = new TaskVo();
			task.setId(vo.getId());
			task.setActivityName(vo.getActivityName());
			task.setAssignee(vo.getAssignee());
			task.setExecutionId(vo.getExecutionId());
			task.setName(vo.getName());
			task.setProgress(vo.getProgress()+"");
			task.setCreateTime(DateUtil.date2long14(vo.getCreateTime()).toString());
			ret.add(task);
			}
		}
		/*Task task = taskList.get(0);
		String taskId = task.getId();
		// read task variables  
		Set<String> variableNames = taskService.getVariableNames(taskId);  
		Map<String, Object> variables = taskService.getVariables(taskId, variableNames); 
		// write task variables  
		variables = new HashMap<String, Object>();  
		variables.put("category", "small");  
		variables.put("lires", 923874893);  
		taskService.setVariables(taskId, variables); 
		//taskSerice也用来完成任务。 
		taskService.completeTask(taskId);  
		taskService.completeTask(taskId, variables);  
		taskService.completeTask(taskId, "");  
		taskService.completeTask(taskId, "", variables); */
		return ret;
	}
	
	@RequestMapping("/handle")
	public ModelAndView handlerTask(String cmd,String id) {
		Map<String, Object> variables = new HashMap<String, Object>();
		ModelAndView view = new ModelAndView();
		if(cmd.equals("go")){
			taskService.completeTask(id);  //go next
		}else{
			variables.put("content", cmd); //go next with content conditions
			taskService.setVariables(id,variables);  
			taskService.completeTask(id);  
		}
		view.setViewName("jbpm/list");
		return view;
	}
}
