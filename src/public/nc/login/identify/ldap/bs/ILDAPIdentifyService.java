package nc.login.identify.ldap.bs;

public interface ILDAPIdentifyService {

	/**
	 * �Ƿ��ر��û�������Ldap��֤��
	 * @param user_code
	 * @return
	 */
	boolean isSpecialUser(String user_code);

	/**
	 * У������
	 * @param user_code
	 * @param userPWD
	 * @return
	 */
	boolean verifyPasswd(String user_code, String userPWD);

}
