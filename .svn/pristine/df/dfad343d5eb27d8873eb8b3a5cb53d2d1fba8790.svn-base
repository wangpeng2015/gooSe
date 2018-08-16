package com.spring.mybatics.service.impl.goo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.haier.result.ServiceResult;
import com.spring.mybatics.dao.goo.GooDAO;
import com.spring.mybatics.dao.goo.LogDao;
import com.spring.mybatics.service.goo.GooService;


@Service("gooService")
public class GooServiceImpl implements GooService{
	
	private static Logger logger = Logger.getLogger(GooServiceImpl.class);
	
	@Resource
	private GooDAO gooDAO;
	
	@Resource
	private LogDao logDao;

	/***
	 * 登录
	 * 
	 */
	@Override
	public ServiceResult findUserMsg(String name,String passwd,String uid) {
		ServiceResult serviceResult=null;
		logger.error("登录-----"+name+",passwd-----"+passwd+",uid------"+uid);
		try {
			serviceResult=new ServiceResult();
			Map<String, String> mapGoo=gooDAO.findUserMsgDao(name,passwd);
			if(mapGoo!=null && mapGoo.size()>0){
				Object resUid=mapGoo.get("uid");
				if(resUid!=null && !resUid.equals("")) {
					resUid=resUid.toString();
					if(!resUid.equals(uid)){
						serviceResult.setMessage("您的手机因刷机或更换手机原因，造成不能登录，请联系客户人员qq群:673058338");
						serviceResult.setResultCode("300");
						serviceResult.setResult(null);
						logDao.savelog(name, "登录uid不一致", "您的账号在别出登录，不允许登录");
						return serviceResult;
					}
				}
				
				Object endDateString=mapGoo.get("recharge_money_endDate");
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//到期时间
				Date endDate=sf.parse(endDateString.toString());
				//现在时间
				Date time = Calendar.getInstance().getTime();
				if(endDate.before(time)){
					serviceResult.setMessage("您好，您的时间已到期，请联系客服人员充值");
					serviceResult.setResultCode("200");
					serviceResult.setResult(null);
					logDao.savelog(name, "登录已到期", serviceResult.getMessage());
					return serviceResult;
				}
				//设置还有多长时间
				Object mins=mapGoo.get("mins");
				int mi=Integer.parseInt(mins.toString());
				String days="剩余:"+mi/1440+"天-"+mi%1440/60+"小时-"+mi%1440%60%60+"分钟";
				mapGoo.put("daysHoursMins", days);
				
				//更新用户uid
				if(!StringUtils.isBlank(uid)) {
					gooDAO.updateUid(name,uid);
				}
				
				serviceResult.setResult(mapGoo);
				serviceResult.setResultCode("200");
				serviceResult.setMessage("登录成功");
			}else{
				serviceResult.setResult(null);
				serviceResult.setMessage("请检查用户名和密码是否正确,请联系客户人员qq群:673058338");
				serviceResult.setResultCode("300");
			}
			logDao.savelog(name, "登录", serviceResult.getMessage());
		} catch (Exception e) {
			logger.error("登录错误-----"+e.toString());
		}
		return serviceResult;
	}
	
	public static void main(String[] args) {
		int ss=1483;
//		求商  /   求余数 %
		System.out.println(ss/1440);//求天数   
		System.out.println(ss%1440/60);//求小时数
		System.out.println(ss%1440%60%60);//求分钟
	}

	/**
	 * 注册 android
	 */
	@Override
	public ServiceResult addSignUserMsg(String name, String passwd,
			String confirmPass,String tuijianCode) {
		ServiceResult serviceResult=null;
		//查看是否注册
		try {
			serviceResult=new ServiceResult();
			Map<String, String> uu=gooDAO.findUserByName(name);
			if(uu!=null && uu.size()>0){
				serviceResult.setMessage("已经存在这个用户");
				serviceResult.setResultCode("300");
			}else{
				//插入信息
				int res=gooDAO.saveSignUserMsgDao(name,passwd,confirmPass,tuijianCode);
				if(res==1){
					serviceResult.setMessage("注册成功");
					serviceResult.setResultCode("200");
				}else{
					serviceResult.setMessage("注册失败");
					serviceResult.setResultCode("300");
				}
				//保存日志
				logDao.savelog(name,"注册",serviceResult.getMessage());
			}
			
		} catch (Exception e) {
			logger.info("注册错误-----"+e.toString());
			logDao.savelog(name,"注册错误原因",(e.toString()).substring(0, 80));
		}
		
		return serviceResult;
	}
	
	
	
	/**
	 * 注册 ios
	 */
	@Override
	public ServiceResult addIosSignUserMsg(String name, String passwd,
			String confirmPass,String tuijianCode,String uid) {
		ServiceResult serviceResult=null;
		//查看是否注册
		try {
			serviceResult=new ServiceResult();
			Map<String, String> uu=gooDAO.findUserByName(name);
			if(uu!=null && uu.size()>0){
				serviceResult.setMessage("已经存在这个用户");
				serviceResult.setResultCode("300");
			}else{
				//插入信息
				int res=gooDAO.saveIosSignUserMsgDao(name,passwd,confirmPass,tuijianCode,uid);
				if(res==1){
					serviceResult.setMessage("注册成功");
					serviceResult.setResultCode("200");
				}else{
					serviceResult.setMessage("注册失败");
					serviceResult.setResultCode("300");
				}
				//保存日志
				logDao.savelog(name,"注册",serviceResult.getMessage());
			}
			
		} catch (Exception e) {
			logger.info("注册错误-----"+e.toString());
			logDao.savelog(name,"注册错误原因",(e.toString()).substring(0, 80));
		}
		
		return serviceResult;
	}

	/**
	 * @return
	 * @see com.spring.mybatics.service.goo.GooService#getLBMessageService()
	 */
	@Override
	public ServiceResult getLBMessageService() {
		ServiceResult serviceResult=null;
		try {
			Map<String, String>  mes=gooDAO.getLBMessageDao();
			serviceResult=new ServiceResult();
			serviceResult.setResult(mes);
		} catch (Exception e) {
			logger.info("获取信息错误-----"+e.toString());
		}
		return serviceResult;
	}

	/**
	 * 判断是否在gooSe表中
	 * @param trim
	 * @return
	 * @see com.spring.mybatics.service.goo.GooService#isPhoneInLangBoService(java.lang.String)
	 */
	@Override
	public ServiceResult isPhoneInLangBoService(String phone) {
		ServiceResult serviceResult=new ServiceResult();
		Integer mapGoo=gooDAO.isPhoneInLangBoDao(phone);
		if(mapGoo!=null && mapGoo.intValue()>0) {
			serviceResult.setResult(mapGoo);
			serviceResult.setMessage("已经存在该用户");
			serviceResult.setResultCode("300");
		}else {
			serviceResult.setResult("0");
			serviceResult.setMessage("没有存在该用户");
			serviceResult.setResultCode("200");
		}
		return serviceResult;
	}

	/**
	 * @param version
	 * @return
	 * @see com.spring.mybatics.service.goo.GooService#findLatestVersion(java.lang.String)
	 */
	@Override
	public ServiceResult findLatestVersion(String version) {
		ServiceResult serviceResult=new ServiceResult();
		Map<String, String> versiondto = gooDAO.findLatestVersionDao(version);
		if(versiondto==null || versiondto.isEmpty()) {
			serviceResult.setResult(null);
			serviceResult.setResultCode("300");
			serviceResult.setMessage("已经是最新版本");
		}else {
			serviceResult.setResult(versiondto);
			serviceResult.setResultCode("200");
			serviceResult.setMessage("抱歉您还不是最新版本，需要升级");
		}
		return serviceResult;
	}

}
