NC v6ԭ��֧�� LDAP����  1.0

��Ҫ���ܣ�
1����ȫ��֤֧��LDAP��֤��Windows��AD����֤����nchome\bin\ncSysConfig.bat��sysConfig.bat���°�ȫ��֤���á�LDAP ��֤��
2��֧������LDAP��֤�������飨һ̨LDAP��������������ʱ����ͨ�����÷�������֤��
3��֧��ָ���û���ͨ��LDAP��֤��root��ϵͳ����Ա�ȣ�,ʹ����ͨ��̬������֤

ʹ��˵����
#��ȫ�������ã�CA��StaticPwd����̬���룩��LDAP��AD��ȣ�
nchome\ierp\sf\authenConfig.xml

#LDAP ����
nchome\ierp\bin\ldap.properties
#-------------start------------
#LDAP ��������ַ�����ż�������Ⱥ�˳��ȥƥ�䣬����ƥ��ʧ������֤ʧ��
server=192.168.60.10,192.168.60.11,192.168.60.14
#port �˿ںţ�Ĭ��389
port=389
#root dn ��Ŀ¼
rootDN=DC=hs,DC=handsome,DC=com,DC=cn
# ���� ��¼�û����ᱻ�� hs\user �ύ��֤
domain=hs
#�����û�����ͨ��LDAP��֤
specialusers=root,super,uf01,uf02
#--------------end-------------

�㽭����LE������
������   2013-12-4
