package com.haier.kj.controller.goo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.spring.mybatics.domain.goo.Mp4AppVersion;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.haier.result.ServiceResult;
import com.spring.mybatics.service.goo.Mp4Service;
import com.spring.mybatics.util.FtpUtils;

@Controller
@RequestMapping(value="/mp4Controller")
public class Mp4Controller {
	
	@Resource
	private Mp4Service mp4Service;
	
	
	@RequestMapping(value="/getMp4category")
	@ResponseBody
	public Object getMp4_category(){
		ServiceResult serviceResult = new ServiceResult();
		serviceResult = mp4Service.getMp4_category();
		return serviceResult;
	}
	
	
	@RequestMapping(value="/findMp4Data")
	@ResponseBody
	public Object findMp4_data(String type,Integer pageStart,Integer pageSize){
		ServiceResult serviceResult = new ServiceResult();
		serviceResult = mp4Service.findMp4_data(type,pageStart,pageSize);
		return serviceResult;
	}
	
	
	@RequestMapping(value="/findMp4Novel")
	@ResponseBody
	public Object findMp4_novel(){
		ServiceResult serviceResult = new ServiceResult();
		serviceResult = mp4Service.findMp4Novel();
		return serviceResult;
	}
	
	
	  @RequestMapping(value = "/upload", method={RequestMethod.POST,RequestMethod.GET})
	  @ResponseBody
	  public Object upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
	      HttpServletRequest request) throws IOException {
			ServiceResult serviceResult = new ServiceResult();
//	    String message = "";
//	    FileEntity entity = new FileEntity();
//	    FileUploadTool fileUploadTool = new FileUploadTool();
	    
	    CommonsMultipartFile cFile = (CommonsMultipartFile) multipartFile;
        DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
        String fileName=fileItem.getFieldName();
        InputStream input = fileItem.getInputStream();
        
	    try {
//	      entity = fileUploadTool.createFile(multipartFile, request);
	      FtpClientUtils ftp=new FtpClientUtils();
	      String url="47.100.103.172";
	      int port=22;
	      String username="ftpuser";
	      String password="ftpuser";
	      String path="/home/ftpuser";
	      String filename=fileName;
	      ftp.uploadFile(url, port, username, password, path, filename, input);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return serviceResult;
	  }
	  
	  
	  /*@RequestMapping(value = "/upload2", method={RequestMethod.POST,RequestMethod.GET})
	  @ResponseBody
	  public Object upload2(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
	      HttpServletRequest request) {
		  ServiceResult serviceResult = new ServiceResult();
	      try {
	    	  CommonsMultipartFile cFile = (CommonsMultipartFile) multipartFile;
		      DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
		      String fileName=fileItem.getFieldName();
		      InputStream input = fileItem.getInputStream();
		        
			  //创建客户端对象
		      FTPClient ftp = new FTPClient();
		      InputStream local=null;
	          //连接ftp服务器
	          ftp.connect("47.100.103.172", 22);
	          //登录
	          ftp.login("ftpuser", "ftpuser");
	          //设置上传路径
	          String path="/home/ftpuser/videos";
	          //检查上传路径是否存在 如果不存在返回false
	          boolean flag = ftp.changeWorkingDirectory(path);
	          if(!flag){
	              //创建上传的路径  该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
	              ftp.makeDirectory(path);
	          }
	          //指定上传路径
	          ftp.changeWorkingDirectory(path);
	          //指定上传文件的类型  二进制文件
	          ftp.setFileType(FTP.BINARY_FILE_TYPE);
	          boolean bo = ftp.storeFile(fileName, input);
	      }catch (Exception e) {
	      }
	      return serviceResult;
	  }*/

	/**
	 * 上传linux地址
	 * @param multipartFile
	 * @param fileType
	 * @param fileContentType
	 * @param request
	 * @return
	 */
	  @RequestMapping(value = "/uploadFile", method={RequestMethod.POST,RequestMethod.GET})
	  @ResponseBody
	  public Object upload3(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
	      @RequestParam(value="fileType")String fileType,
	      @RequestParam(value="fileContentType")String fileContentType,
	      HttpServletRequest request) {
		  
		  ServiceResult serviceResult = new ServiceResult();
	      try {
	    	  CommonsMultipartFile cFile = (CommonsMultipartFile) multipartFile;
		      DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
		      String fileName=fileItem.getName();
		      InputStream input = fileItem.getInputStream();
		        
		      FtpUtils ff=FtpUtils.getSftpUtil("640661.ichengyun.net", 22, "testuser", "*IKMNHY^2018a");
		      ff.uploadByStream("/web/java/tomcat/apache-tomcat-gooSe-8088/webapps/videos", fileName, input);
		      
		      /*把文件名字存起来*/
		      Integer res=mp4Service.saveFileName(fileType,fileContentType,fileName);
		      
		      serviceResult.setMessage("上传成功");
		      serviceResult.setResultCode("200");
	      }catch (Exception e) {
	    	  serviceResult.setMessage("上传失败");
		      serviceResult.setResultCode("200");
	      }
	      return serviceResult;
	  }


	/**
	 * 上传linux地址
	 * @param multipartFile
	 * @param fileType
	 * @param fileContentType
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadPicture", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object uploadPicture(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
						  @RequestParam(value="fileType")String fileType,
						  @RequestParam(value="fileContentType")String fileContentType,
						  HttpServletRequest request) {

		ServiceResult serviceResult = new ServiceResult();
		try {
			CommonsMultipartFile cFile = (CommonsMultipartFile) multipartFile;
			DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
			String fileName=fileItem.getName();
			InputStream input = fileItem.getInputStream();

			FtpUtils ff=FtpUtils.getSftpUtil("640661.ichengyun.net", 22, "testuser", "*IKMNHY^2018a");
			ff.uploadByStream("/web/java/tomcat/apache-tomcat-gooSe-8088/webapps/videos", fileName, input);

			/*把文件名字存起来*/
			Integer res=mp4Service.saveFileName(fileType,fileContentType,fileName);

			serviceResult.setMessage("上传成功");
			serviceResult.setResultCode("200");
		}catch (Exception e) {
			serviceResult.setMessage("上传失败");
			serviceResult.setResultCode("200");
		}
		return serviceResult;
	}


	/**
	 * 获取app的版本和version
	 * @return
	 */
	@RequestMapping(value = "/getAppVersionAndUrl", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object getAppVersionAndUrl(){
		ServiceResult app = null;
		app=mp4Service.getAppVersionAndUrl();
		return  app;

	}


	/**
	 * 获取图片轮播连接
	 * @return
	 */
	@RequestMapping(value = "/getImgLink", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object getImgLink(){
		ServiceResult app = null;
		app=mp4Service.getImgLink();
		return  app;

	}
}
