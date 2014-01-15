package nc.login.identify.ldap.bs;

import nc.bs.framework.common.NCLocator;
import nc.identityverify.vo.AuthenSubject;
import nc.login.vo.ILoginConstants;
import nc.vo.sm.UserVO;
import nc.vo.uap.rbac.util.RbacUserPwdUtil;

public class LDAPIdentityVerifier extends
		nc.identityverify.pub.StaticPWDVerifier {

	public int verify(AuthenSubject subject, UserVO user) throws Exception {
		ILDAPIdentifyService util = (ILDAPIdentifyService) NCLocator
				.getInstance().lookup(ILDAPIdentifyService.class);
		if (user != null) {
			boolean isOK = false;
			if (util.isSpecialUser(user.getUser_code())) {
				// 特殊用户走NC标准产品验证
				isOK = RbacUserPwdUtil.checkUserPassword(user,
						subject.getUserPWD());
			} else {
				// 普通用户走LDAP认证
				isOK = util.verifyPasswd(user.getUser_code(),
						subject.getUserPWD());
			}
			if (isOK) {// 身份合法
				return ILoginConstants.USER_IDENTITY_LEGAL;

			} else {// 密码错误，身份不合法.
				return ILoginConstants.USER_NAME_RIGHT_PWD_WRONG;
			}
		} else { // 说明用户名称错误
			return ILoginConstants.USER_NAME_WRONG;
		}
	}
}
