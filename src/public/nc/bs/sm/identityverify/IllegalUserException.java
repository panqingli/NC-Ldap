package nc.bs.sm.identityverify;

public class IllegalUserException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6184232127103630639L;

	public IllegalUserException(String msg){
		super("用户认证密码错误！");
	}
	
}
