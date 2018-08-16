package com.spring.mybatics.service.impl.goo;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.spring.mybatics.domain.goo.Mp4AppVersion;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.haier.result.ServiceResult;
import com.spring.mybatics.dao.goo.Mp4DAO;
import com.spring.mybatics.service.goo.Mp4Service;



@Service("mp4Service")
public class Mp4ServiceImpl implements Mp4Service{
	
	
	@Resource
	private Mp4DAO mp4DAO;

	@Override
	public ServiceResult getMp4_category() {
		ServiceResult serviceResult=null;
		try {
			serviceResult=new ServiceResult();
			List<Map<String, String>> mapList=mp4DAO.getMp4_category();
			if(mapList!=null && mapList.size()>=0) {
				serviceResult.setResultCode("200");
				serviceResult.setResult(mapList);
			}else {
				serviceResult.setResultCode("300");
				serviceResult.setResult(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceResult;
	}

	public ServiceResult findMp4_data(String type,Integer pageStart,Integer pageSize) {
		ServiceResult serviceResult=null;
		try {
			serviceResult=new ServiceResult();
			JSONObject object=new JSONObject();
			List<Map<String, String>> mapList=mp4DAO.findMp4_data(type,pageStart,pageSize);
			object.put("list", mapList);
			/*总条数*/
			int totalCount=mp4DAO.getTotleCount(type);
			object.put("totalCount", totalCount);
			if(object!=null) {
				serviceResult.setResultCode("200");
				serviceResult.setResult(object);
			}else {
				serviceResult.setResultCode("300");
				serviceResult.setResult(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceResult;
	}

	@Override
	public ServiceResult findMp4Novel() {
		ServiceResult serviceResult=null;
		try {
			serviceResult=new ServiceResult();
			List<Map<String, String>> mapList=mp4DAO.findMp4Novel();
			if(mapList!=null && mapList.size()>=0) {
				serviceResult.setResultCode("200");
				serviceResult.setResult(mapList);
			}else {
				serviceResult.setResultCode("300");
				serviceResult.setResult(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceResult;
	}

	@Override
	public Integer saveFileName(String fileType,String fileContentType,String fileName) {
		return mp4DAO.saveFileName(fileType,fileContentType,fileName);
	}

	@Override
	public ServiceResult getAppVersionAndUrl() {
		ServiceResult serviceResult=null;
		try {
			serviceResult = new ServiceResult();
			Map<String, String> app = mp4DAO.getAppVersionAndUrl();
			serviceResult.setResult(app);
			serviceResult.setResultCode("200");
		}catch (Exception e){
			serviceResult.setResultCode("300");
			serviceResult.setResult(null);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult getImgLink() {
		ServiceResult serviceResult=null;
		try {
			serviceResult = new ServiceResult();
			List<Map<String, String>> app = mp4DAO.getImgLink();
			serviceResult.setResult(app);
			serviceResult.setResultCode("200");
		}catch (Exception e){
			serviceResult.setResultCode("300");
			serviceResult.setResult(null);
		}
		return serviceResult;
	}

}
