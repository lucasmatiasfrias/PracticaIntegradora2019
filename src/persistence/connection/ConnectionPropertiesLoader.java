package persistence.connection;

import java.io.IOException;
import java.util.Properties;

public class ConnectionPropertiesLoader {
	private static Properties prop;
	private static ConnectionProperties connProp;

	private static final String PROPERTIES_RESOURCE_PATH = "db-properties";
	public static final String DB_URL_KEY = "url";
	public static final String DB_USER_KEY = "user";
	public static final String DB_PASSWORD_KEY = "pass";
	public static final String DB_DRIVER_KEY = "driverClass";

	private ConnectionPropertiesLoader() {
		throw new AssertionError();
	}

	public static synchronized ConnectionProperties load() throws IOException {
		prop = new Properties();
		prop.loadFromXML(ConnectionPropertiesLoader.class.getResourceAsStream(PROPERTIES_RESOURCE_PATH));
		connProp = new ConnectionProperties(prop.getProperty(DB_URL_KEY), prop.getProperty(DB_USER_KEY),
				prop.getProperty(DB_PASSWORD_KEY), prop.getProperty(DB_DRIVER_KEY));
		return connProp;
	}
}
