NC v6原生支持 LDAP功能  1.0

主要功能：
1、安全认证支持LDAP认证，Windows下AD域认证。在nchome\bin\ncSysConfig.bat（sysConfig.bat）下安全认证配置“LDAP 认证”
2、支持配置LDAP验证服务器组（一台LDAP服务器出现问题时，可通过备用服务器认证）
3、支持指定用户不通过LDAP认证（root，系统管理员等）,使用普通静态密码认证

使用说明：
#安全中心配置：CA、StaticPwd（静态密码）、LDAP（AD域等）
nchome\ierp\sf\authenConfig.xml

#LDAP 配置
nchome\ierp\bin\ldap.properties
#-------------start------------
#LDAP 服务器地址，逗号间隔，按先后顺序去匹配，所有匹配失败则认证失败
server=192.168.60.10,192.168.60.11,192.168.60.14
#port 端口号，默认389
port=389
#root dn 根目录
rootDN=DC=hs,DC=handsome,DC=com,DC=cn
# 域名 登录用户名会被以 hs\user 提交验证
domain=hs
#特殊用户：不通过LDAP认证
specialusers=root,super,uf01,uf02
#--------------end-------------

浙江用友LE开发部
潘庆利   2013-12-4
