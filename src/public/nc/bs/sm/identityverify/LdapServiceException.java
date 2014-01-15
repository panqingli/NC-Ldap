package nc.bs.sm.identityverify;

public class LdapServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -414988962272247662L;

	public LdapServiceException(String message) {
		// TODO Auto-generated constructor stub
		super("Ldap认证服务器异常！");
	}

}
