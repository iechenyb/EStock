package com.cyb.updown;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			    response.setCharacterEncoding("utf-8");
			    response.setContentType("text/html; charset=utf-8");
			    request.setCharacterEncoding("utf-8");
//				ServletFileUpload y;
				PrintWriter out = response.getWriter();
				int MAX_SIZE = 102400 * 102400;
				String rootPath;
				DataInputStream in = null;
				FileOutputStream fileOut = null;
				String remoteAddr = request.getRemoteAddr();
				String serverName = request.getServerName();
				String realPath = request.getRealPath(serverName);
				realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
				rootPath = realPath + "\\upload\\";
				String contentType = request.getContentType();
				//multipart/form-data; boundary=----WebKitFormBoundaryz48Y6U471ox14Inm
				try {
					if (contentType.indexOf("multipart/form-data") >= 0) {
						in = new DataInputStream(request.getInputStream());
						int formDataLength = request.getContentLength();
						if (formDataLength > MAX_SIZE) {
							out.println("<P>" + MAX_SIZE + "</p>");
							return;
						}
						byte dataBytes[] = new byte[formDataLength];
						int byteRead = 0;
						int totalBytesRead = 0;
						while (totalBytesRead < formDataLength) {
							byteRead = in.read(dataBytes, totalBytesRead,
									formDataLength);
							totalBytesRead += byteRead;
						}
						String file = new String(dataBytes);
						//out.println(file);
						String saveFile = file.substring(file
								.indexOf("filename=\"") + 10);
						saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
						saveFile = saveFile.substring(
								saveFile.lastIndexOf("\\") + 1,
								saveFile.indexOf("\""));
						int lastIndex = contentType.lastIndexOf("=");
						String boundary = contentType.substring(lastIndex + 1,
								contentType.length());
						String fileName = rootPath + saveFile;
						int pos;
						pos = file.indexOf("filename=\"");
						pos = file.indexOf("\n", pos) + 1;
						pos = file.indexOf("\n", pos) + 1;
						pos = file.indexOf("\n", pos) + 1;
						int boundaryLocation = file.indexOf(boundary, pos) - 4;
						int startPos = ((file.substring(0, pos)).getBytes()).length;
						int endPos = ((file.substring(0, boundaryLocation))
								.getBytes()).length;
						File checkFile = new File(fileName);
						if (checkFile.exists()) {
							out.println("<p> File Name " + saveFile + "</p>");
						}
						File fileDir = new File(rootPath);
						if (!fileDir.exists()) {
							fileDir.mkdirs();
						}
						fileOut = new FileOutputStream(fileName);
						System.out.println("filename="+fileName);
						fileOut.write(dataBytes, startPos, (endPos - startPos));
						fileOut.close();
						out.println("<p> File upload is success! save path on server is "+fileName + "</p>");
						out.println("file contentype is "+contentType);
						out.println(",file length is "+checkFile.length()+" byte");
					} else {
						String content = request.getContentType();
						out.println("<p>"+content+"</p>");
					}
				} catch (Exception ex) {
					throw new ServletException(ex.getMessage());
				}
	}

}
