package nc.login.identify.ldap.bs;

public interface ILDAPIdentifyService {

	/**
	 * 是否特别用户（不走Ldap认证）
	 * @param user_code
	 * @return
	 */
	boolean isSpecialUser(String user_code);

	/**
	 * 校验密码
	 * @param user_code
	 * @param userPWD
	 * @return
	 */
	boolean verifyPasswd(String user_code, String userPWD);

}
