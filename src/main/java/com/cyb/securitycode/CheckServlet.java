package com.cyb.securitycode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.controller.QutoesController;

/**
 * @author gacl
 * 服务器端接收到验证码后的处理
 */
public class CheckServlet extends HttpServlet {
	static Log log = LogFactory.getLog(QutoesController.class);
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientCheckcode_nl = request.getParameter("nlValidateCode");//接收客户端浏览器提交上来的验证码
        String clientCheckcode_n = request.getParameter("nValidateCode");//接收客户端浏览器提交上来的验证码
        String clientCheckcode_l = request.getParameter("lValidateCode");//接收客户端浏览器提交上来的验证码
        String clientCheckcode_ch = request.getParameter("chValidateCode");//接收客户端浏览器提交上来的验证码
        String serverCheckcode_nl = (String) request.getSession().getAttribute("nlcheckcode");//从服务器端的session中取出验证码
        String serverCheckcode_n = (String) request.getSession().getAttribute("ncheckcode");//从服务器端的session中取出验证码
        String serverCheckcode_l = (String) request.getSession().getAttribute("lcheckcode");//从服务器端的session中取出验证码
        String serverCheckcode_ch = (String) request.getSession().getAttribute("chcheckcode");//从服务器端的session中取出验证码
        StringBuffer ret = new StringBuffer("");
        if (clientCheckcode_ch.equals(serverCheckcode_ch)) {//将客户端验证码和服务器端验证比较，如果相等，则表示验证通过
            log.info("ch验证码验证通过！,");
            ret.append("ch验证码验证通过！,");
        }else {
        	ret.append("ch验证码验证失败！,");
            log.info("ch验证码验证失败！,");
        }
        if (clientCheckcode_nl.equals(serverCheckcode_nl.toLowerCase())) {//将客户端验证码和服务器端验证比较，如果相等，则表示验证通过
            log.info("nl验证码验证通过！,");
            ret.append("nl验证码验证通过！,");
        }else {
            log.info("nl验证码验证失败！,");
            ret.append("nl验证码验证失败！,");
        }
        if (clientCheckcode_n.equals(serverCheckcode_n)) {//将客户端验证码和服务器端验证比较，如果相等，则表示验证通过
            log.info("n验证码验证通过！,");
            ret.append("n验证码验证通过！,");
        }else {
            log.info("n验证码验证失败！,");
            ret.append("n验证码验证失败！,");
        }
        if (clientCheckcode_l.equals(serverCheckcode_l.toLowerCase())) {//将客户端验证码和服务器端验证比较，如果相等，则表示验证通过
            log.info("l验证码验证通过！,");
            ret.append("l验证码验证通过！,");
        }else {
            log.info("l验证码验证失败！,");
            ret.append("l验证码验证失败！,");
        }
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(ret.toString());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
