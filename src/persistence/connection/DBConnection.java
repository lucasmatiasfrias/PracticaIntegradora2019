package persistence.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static String url;
	private static String user;
	private static String pass;
	private static String driverClass;

	public DBConnection(ConnectionProperties prop) throws IOException, ClassNotFoundException {
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		pass = prop.getProperty("pass");
		driverClass = prop.getProperty("driverClass");
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(driverClass);
		return DriverManager.getConnection(url, user, pass);
	}

	// METODOS A REFACTORIZAR
	public static boolean createBackupLinux(String fileBackup) {
		String executeCmd = "mysqldump -u " + user + " -p" + pass + " " + "tp2" + " -r " + fileBackup;
		try {
			Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete;
			processComplete = runtimeProcess.waitFor();
			if (processComplete == 0) {
				System.out.println("Ok");
				return true;
			} else {
				System.out.println("Error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean restoreBackupLinux(String fileBackup) {
		try {
			String[] executeCmd = new String[] { "/bin/sh", "-c",
					"mysql -u" + user + " -p" + pass + " " + "tp2" + " < " + fileBackup };
			Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();
			if (processComplete == 0) {
				System.out.println("Ok");
				return true;
			} else {
				System.out.println("Error");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public static void main(String []a) {
		try {
			new DBConnection(ConnectionProperties.getConnProp());
			System.out.println("conecto");
		} catch (Exception e) {
			System.out.println("no conecto");
		} 
	}
}
