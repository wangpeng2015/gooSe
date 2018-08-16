package com.haier.result.exception;

public class ExceptionConstants {
	/**
	 * @ClassName: BusinessExceptionCode
	 * @Description: 定义所有的异常类型和对应的异常信息
	 * 
	 */
	public enum BusinessExceptionCode {
		parse_date_error("当前默认的时间格式都不支持指定的时间字符串",""),
		param_error("输入的参数不正确","200_1"),
		login_null_username("用户名不能为空","300_1"), 
		login_null_password("密码不能为空","300_2"),
		login_user_locked("用户已经被锁定","300_3"), 
		login_invalid_username("用户名不正确","300_4"), 
		login_invalid_password("用户密码不正确","300_5"),
		no_auth("用户没有权限","300_6"),
		portal_validate_login("无法连接portal网站验证登录信息","300_6"),
		portal_set_password("修改密码失败","300_6"),
		delete_error("删除出错","300_6"),
		parse_format_error("参数格式无法转换","300_6"),
		database_none_data("数据库中没有找到指定记录","300_6"),
		database_duplicate_data("数据库已存在有效数据","300_6"),
		database_save_error("数据保存失败","300_6"),
		ws_result_error("webservice调用失败","300_6"),
		not_allow_report_sales("该直销员不允许上传销量","300_6"),
		no_user("未找到相应用户","300_6"),
		no_employee("未找到对应的员工信息","300_6"),
		no_shop("没有找到您的门店信息","300_6"),
		find_file_null("获取文件为空","300_6"),
		daily_info("签到数据不正确","300_6");
		
		private String context;
		private String errorCode;

		private BusinessExceptionCode() {
		}

		private BusinessExceptionCode(String context,String errorCode) {
			this.context = context;
			this.errorCode = errorCode;
		}

		/**
		 * @Description:返回当前枚举对应的信息
		 * @author Liu Wenjie
		 * @date 2013-11-8 下午12:42:50
		 * @return
		 */
		public String getContext() {
			return this.context;
		}
		

		/**
		 * @Description: 属性 errorCode 的get方法 
		 * @return errorCode
		 */
		public String getErrorCode() {
			return errorCode;
		}
		
	}

	

}