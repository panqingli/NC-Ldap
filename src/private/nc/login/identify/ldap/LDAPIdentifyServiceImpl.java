package nc.login.identify.ldap;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import nc.bs.sm.identityverify.IllegalUserException;
import nc.bs.sm.identityverify.LdapServiceException;
import nc.login.identify.ldap.bs.ILDAPIdentifyService;
import nc.vo.sm.login.ldap.LdapConfigInfoVO;

public class LDAPIdentifyServiceImpl implements ILDAPIdentifyService {

	public static int LOGIN_NORMAL = 1;
	public static int LOGIN_ILLEGAL = 2;
	public static int LOGIN_LINK_ERROR = 3;
	
	private LdapConfigInfoVO configInfo = null;

	public LDAPIdentifyServiceImpl() {
		if (null == configInfo) {
			configInfo = new LdapConfigInfoVO();
		}
	}

	/**
	 * 是否是特殊用户
	 * 
	 * @param usercode
	 * @return
	 */
	public boolean isSpecialUser(String usercode) {
		return configInfo.getSpecialUsers().contains(usercode);
	}

	public boolean verifyPasswd(String usercode, String password) {
		List<String> ldapservers = configInfo.getServers();
		String port = configInfo.getPort();
		String rootDN = configInfo.getRoot();
		String domain = configInfo.getDomain();

		boolean isOK = false;
		for (String server : ldapservers) {
			int login_state = verifyPasswd(server, port, rootDN, domain, usercode,
					password);
			if (login_state == LDAPIdentifyServiceImpl.LOGIN_LINK_ERROR){
				// 服务器连接失败等异常，直接跳过
				continue;
			}else if (login_state == LDAPIdentifyServiceImpl.LOGIN_NORMAL){
				// 用户名密码正常
				isOK = true;
			}else if (login_state == LDAPIdentifyServiceImpl.LOGIN_ILLEGAL){
				// 密码错误
				break;
			}
			if (isOK) {
				break;
			}
		}
		return isOK;
	}

	/**
	 * 
	 * @param server
	 * @param port
	 * @param root
	 * @param domain
	 * @param usercode
	 * @param password
	 * @return 1  验证正常  2 验证失败   3 验证异常
	 */
	private int verifyPasswd(String server, String port, String root,
			String domain, String usercode, String password) {
		try {
			getLdapContext(server, port, root, domain, usercode, password);
			return LDAPIdentifyServiceImpl.LOGIN_NORMAL;
		} catch (LdapServiceException e) {
			return LDAPIdentifyServiceImpl.LOGIN_LINK_ERROR;
		} catch (IllegalUserException e) {
			return LDAPIdentifyServiceImpl.LOGIN_ILLEGAL;
		}
	}

	private static DirContext ctx;

	private String getLdapContext(String server, String port, String root,
			String domain, String account, String password)
			throws LdapServiceException, IllegalUserException {
		String msgResult = null;
		if (null == account || null == password
			|| account.length() == 0 || password.length() == 0 ){
			msgResult = "用户名或密码为空!";
			throw new LdapServiceException(msgResult);
		}

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + server + ":" + port + "/"
				+ root);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, domain + "\\" + account);
		env.put(Context.SECURITY_CREDENTIALS, password);
		try {
			// 连接ldap
			ctx = new InitialDirContext(env);
			msgResult = "ok";
		} catch (javax.naming.AuthenticationException e) {
			throw new IllegalUserException(e.getMessage());
		} catch (NamingException e) {
			msgResult = "Ldap Server Unknown Exception!";
			throw new LdapServiceException(msgResult);
		}
		return msgResult;
	}
}
