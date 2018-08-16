package com.spring.mybatics.service.goo;

import com.haier.result.ServiceResult;
import com.spring.mybatics.domain.goo.Mp4AppVersion;

public interface Mp4Service {

	ServiceResult getMp4_category();

	ServiceResult findMp4_data(String type,Integer pageStart,Integer pageSize);

	ServiceResult findMp4Novel();

	Integer saveFileName(String fileType,String fileContentType,String fileName);


	ServiceResult getAppVersionAndUrl();

	ServiceResult getImgLink();
}
