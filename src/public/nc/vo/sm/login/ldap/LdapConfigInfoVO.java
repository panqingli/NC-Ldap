package nc.vo.sm.login.ldap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class LdapConfigInfoVO {
	public static String configFilePath = "ierp/bin/ldap.properties";

	private List<String> m_servers = null;
	
	private String m_port = "389";
	
	private Set<String> m_specialusers = null;
	
	private String m_rootDN = null;
	
	private String m_domain = null;
	
	public LdapConfigInfoVO(){
		initConfig();
	}

	private void initConfig() {
		FileInputStream fin = null;
		Properties prop = new Properties();
		try {
			fin = new FileInputStream(new File(LdapConfigInfoVO.configFilePath));
			prop.load(fin);
			
			String servers = prop.getProperty("server");
			String[] arrSevers = servers.split(",");
			List<String> lstServer = new ArrayList<String>();
			for (String server : arrSevers){
				lstServer.add(server);
			}
			setServers(lstServer);
			
			setPort(prop.getProperty("port"));
			
			setRoot(prop.getProperty("rootDN"));
			
			setDomain(prop.getProperty("domain"));
			
			String specialusers = prop.getProperty("specialusers");
			String[] arrUsers = specialusers.split(",");
			Set<String> set = new HashSet<String>();
			for (String server : arrUsers){
				set.add(server);
			}
			setSpecialUsers(set);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (null != fin){
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	private void setSpecialUsers(Set<String> specialusers) {
		m_specialusers = specialusers;
	}

	public Set<String> getSpecialUsers() {
		return m_specialusers;
	}
	
	private void setDomain(String domain) {
		m_domain = domain;
	}

	public String getDomain() {
		return m_domain;
	}
	
	private void setRoot(String root) {
		m_rootDN = root;
	}

	public String getRoot() {
		return m_rootDN;
	}
	
	private void setServers(List<String> lstServer) {
		m_servers = lstServer;
	}
	
	public List<String> getServers() {
		return m_servers;
	}

	public void setPort(String m_port) {
		this.m_port = m_port;
	}

	public String getPort() {
		return m_port;
	}
	
}
