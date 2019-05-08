package persistence.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionProperties {

	private Properties prop;
	private static ConnectionProperties connProp;
	private static final String PROPERTIES_RESOURCE_PATH = "./db-properties";
	public static final String DB_URL_KEY = "url";
	public static final String DB_USER_KEY = "user";
	public static final String DB_PASSWORD_KEY = "pass";
	public static final String DB_DRIVER_KEY = "driverClass";

	private ConnectionProperties() throws IOException {
		this.prop = new Properties();
		this.prop
				.loadFromXML(new FileInputStream(new File(getClass().getResource(PROPERTIES_RESOURCE_PATH).getFile())));
	}

	public String getProperty(String key) throws IOException {
		return getConnProp().prop.getProperty(key);
	}

	public static synchronized ConnectionProperties getConnProp() throws IOException {
		if (connProp == null)
			connProp = new ConnectionProperties();
		return connProp;
	}
}
