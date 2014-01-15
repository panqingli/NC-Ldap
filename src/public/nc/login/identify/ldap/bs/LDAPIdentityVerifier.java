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
				// �����û���NC��׼��Ʒ��֤
				isOK = RbacUserPwdUtil.checkUserPassword(user,
						subject.getUserPWD());
			} else {
				// ��ͨ�û���LDAP��֤
				isOK = util.verifyPasswd(user.getUser_code(),
						subject.getUserPWD());
			}
			if (isOK) {// ��ݺϷ�
				return ILoginConstants.USER_IDENTITY_LEGAL;

			} else {// ���������ݲ��Ϸ�.
				return ILoginConstants.USER_NAME_RIGHT_PWD_WRONG;
			}
		} else { // ˵���û����ƴ���
			return ILoginConstants.USER_NAME_WRONG;
		}
	}
}
