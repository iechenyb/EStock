package com.cyb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cyb.qutoes.constant.QutoesContants;
import com.cyb.utils.FileUtils;

@RequestMapping("file")
@Controller
public class FileController {
	Log log = LogFactory.getLog(FileController.class);
   @RequestMapping("downList")
   public ModelAndView down(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/upload/download");
		List<File> lst = FileUtils.readDirectory(QutoesContants.WEBPATH+"upload/");
		List<Map<String,String>> names = new ArrayList<Map<String,String>>();
		for(int i=0;i<lst.size();i++){
			Map<String, String> tmp = new HashMap<String, String>();
			tmp.put("name", lst.get(i).getName());
			names.add(tmp);
		}
		view.addObject("lst", names);
		return view;
   }
   @RequestMapping("upload2")
   @ResponseBody
   public String upload2(HttpServletRequest request,@RequestParam(value = "file") MultipartFile[] files) {
		try {
			for (MultipartFile mf : files) {
				if(!mf.isEmpty()){
					String path1=QutoesContants.WEBPATH+File.separator+"upload";
					//  下面的加的日期是为了防止上传的名字一样
					String path = path1+File.separator
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date()) + mf.getOriginalFilename();
					log.info("保存路径："+path);
					FileUtils.copyFileByStream(mf.getInputStream(), path);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "upload";
	}
   @RequestMapping("upload3")
   @ResponseBody
   public String upload3(HttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> list = upload.parseRequest(request);
			log.info("文件个数："+list.size());
			for(FileItem item : list){
				if(item.isFormField()){
					log.info(item.getInputStream());
					log.info(item.getFieldName()+","+item.getName());
					String path1=QutoesContants.WEBPATH+File.separator+"upload";
					//  下面的加的日期是为了防止上传的名字一样
					String path = path1+File.separator
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date()) + item.getName();
					log.info("保存路径："+path);
					FileUtils.copyFileByStream(item.getInputStream(), path);
				}else{
					log.info("isFormField=false");
					log.info(item.getInputStream());
					log.info(item.getFieldName()+","+item.getName());
					String path1=QutoesContants.WEBPATH+File.separator+"upload";
					//  下面的加的日期是为了防止上传的名字一样
					String path = path1+File.separator
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date()) + item.getName();
					log.info("保存路径："+path);
					FileUtils.copyFileByStream(item.getInputStream(), path);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "uploadsuccess";
	}
    @RequestMapping("upload")
    @ResponseBody
	public String upload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
              // 这里我用到了jar包
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			log.info("开始上传.");
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile((String) iter.next());
				if (file != null) {
					String fileName = file.getOriginalFilename();

					/*String path1 = Thread.currentThread()
							.getContextClassLoader().getResource("").getPath()
							+ "download" + File.separator;*/
					String path1=QutoesContants.WEBPATH+File.separator+"upload";
					//  下面的加的日期是为了防止上传的名字一样
					String path = path1+File.separator
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date()) + fileName;
					log.info("保存路径："+path);
					File localFile = new File(path);
					file.transferTo(localFile);
				}

			}

		}else{
			log.info("非文件上传请求！");
		}
		return "uploadSuccess";
	}
	@RequestMapping("toUpload")
	public String toUpload() {
		return "upload";
	}

	@SuppressWarnings("resource")
	@RequestMapping("/download")
	public String download(String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);
		OutputStream os =null;
		InputStream inputStream  = null;
		try {
			/*String path = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath()*/
			String path=QutoesContants.WEBPATH+File.separator+"upload";//这个download目录为啥建立在classes下的
			inputStream = new FileInputStream(new File(path
					+ File.separator + fileName));
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			 // 这里主要关闭。
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				//os.close();
				//inputStream.close();
			}catch(Exception e){
				
			}
		}
           //  返回值要注意，要不然就出现下面这句错误！
           //java+getOutputStream() has already been called for this response
		return null;
	}
	/*
    *采用spring提供的上传文件的方法
    */
   @RequestMapping("springUpload")
   public String  springUpload(HttpServletRequest request) throws IllegalStateException, IOException
   {
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
       CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
               request.getSession().getServletContext());
       //检查form中是否有enctype="multipart/form-data"
       if(multipartResolver.isMultipart(request))
       {
           //将request变成多部分request
           MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
          //获取multiRequest 中所有的文件名
           Iterator iter=multiRequest.getFileNames();
            
           while(iter.hasNext())
           {
               //一次遍历所有文件
               MultipartFile file=multiRequest.getFile(iter.next().toString());
               if(file!=null)
               {
                   String path="E:/springUpload"+file.getOriginalFilename();
                   //上传
                   file.transferTo(new File(path));
               }
                
           }
          
       }
       long  endTime=System.currentTimeMillis();
       System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
   return "/success"; 
   }
   @RequestMapping("fileUpload2")
   public String  fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {
        long  startTime=System.currentTimeMillis();
       System.out.println("fileName："+file.getOriginalFilename());
       String path="E:/"+new Date().getTime()+file.getOriginalFilename();
        
       File newFile=new File(path);
       //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
       file.transferTo(newFile);
       long  endTime=System.currentTimeMillis();
       System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
       return "/success"; 
   }
   /*
    * 通过流的方式上传文件
    * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
    */
   @RequestMapping("fileUpload")
   public String  fileUpload(@RequestParam("file") CommonsMultipartFile file) throws IOException {
        
        
       //用来检测程序运行时间
       long  startTime=System.currentTimeMillis();
       System.out.println("fileName："+file.getOriginalFilename());
        
       try {
           //获取输出流
           OutputStream os=new FileOutputStream("E:/"+new Date().getTime()+file.getOriginalFilename());
           //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
           InputStream is=file.getInputStream();
           int temp;
           //一个一个字节的读取并写入
           while((temp=is.read())!=(-1))
           {
               os.write(temp);
           }
          os.flush();
          os.close();
          is.close();
        
       } catch (FileNotFoundException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       long  endTime=System.currentTimeMillis();
       System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
       return "/success"; 
   }
}
