package persistence.connection;

public class ConnectionProperties {

	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	private String dbDriverClass;
	
	public ConnectionProperties(String dbUrl, String dbUser, String dbPassword, String dbDriverClass) {
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
		this.dbDriverClass = dbDriverClass;
	}

	public String getDbUrl() {
		return dbUrl;
	}
	
	public String getDbUser() {
		return dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getDbDriverClass() {
		return dbDriverClass;
	}
	
}
