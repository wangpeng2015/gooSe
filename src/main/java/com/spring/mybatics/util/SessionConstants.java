package com.spring.mybatics.util;

/**
 * @author WangXuzheng
 *
 */
public class SessionConstants {
	
	public static final String KEY_USER_ID = "_user_id";
	/**
	 * Session存放的用戶登录名
	 */
	public static final String KEY_USER_NAME = "_user_name";
	/**
	 * Session中存放的用户真实姓名
	 */
	public static final String KEY_USER_NICK_NAME = "_user_nick_name";
	/**
	 * Session存放的用户id
	 */
	public static final String KEY_USER_CODE = "_user_code";
	
	public static final String KEY_USER_ROLE ="_user_role";
	/**
	 * 门店编码
	 */
	public static final String KEY_STORE_CODE ="_KEY_STORE_CODE";
	/**
	 * 门店名称
	 */
	public static final String KEY_STORE_NAME ="_KEY_STORE_NAME";
	/**
	 * 用户授权信息
	 */
	public static final String KEY_AUTHENTICATION = "_user_authentication";
	/**
	 * 是否修改过默认密码，是否密码过期
	 */
	public static final String KEY_PASSWORD_TIP = "_password_tip_flag";
	/**
	 * 上次登录ip
	 */
	public static final String KEY_LAST_LOGIN_IP = "_user_last_login_ip";
	/**
	 * 上次访问url
	 */
	public static final String KEY_LAST_VISIT_URL = "_user_last_visit_url";
	/**
	 * 验证码
	 */
	public static final String KEY_CHECKCODE = "checkCode";
	/**
	 * 客户端语言
	 */
	public static final String KEY_LOCAL_LANGUAGE = "WW_TRANS_I18N_LOCALE";
	/**
	 * 密码
	 */
	public static final String KEY_PASSWORD = "_password";
	/**
	 * 可逆密碼
	 */
	public static final String KEY_REVERSIBLE_PASSWORD = "_reversible_password";
	/**
	 * 单点登录session id
	 */
	public static final String KEY_CAS_SESSIONID = "_cas_session_id";
}
