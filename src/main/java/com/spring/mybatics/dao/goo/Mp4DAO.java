package com.spring.mybatics.dao.goo;

import java.util.List;
import java.util.Map;

import com.spring.mybatics.domain.goo.Mp4AppVersion;
import org.apache.ibatis.annotations.Param;

import com.spring.mybatics.dao.CommonDAO;
import com.spring.mybatics.domain.goo.Log;

public interface Mp4DAO extends CommonDAO<Log,Integer>{

	List<Map<String, String>> getMp4_category();

	List<Map<String, String>> findMp4_data(@Param("type")String type,@Param("pageStart")Integer pageStart,@Param("pageSize")Integer pageSize);

	List<Map<String, String>> findMp4Novel();

	int getTotleCount(@Param("type")String type);

	Integer saveFileName(@Param("fileType")String fileType,@Param("fileContentType")String fileContentType,@Param("fileName")String fileName);

	Map<String, String> getAppVersionAndUrl();

	List<Map<String,String>> getImgLink();
}
