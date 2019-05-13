package persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private ConnectionProperties properties;

	public DBConnection(ConnectionProperties prop) {
		properties = prop;
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(properties.getDbDriverClass());
		return DriverManager.getConnection(properties.getDbUrl(), properties.getDbUser(), properties.getDbPassword());
	}

	public void closeConnection() throws ClassNotFoundException, SQLException {
		getConnection().close();
	}
	// METODOS A REFACTORIZAR
	public boolean createBackupLinux(String fileBackup) {
		String executeCmd = "mysqldump -u " + properties.getDbUser() + " -p" + properties.getDbPassword() + " " + "tp2" + " -r " + fileBackup;
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

	public boolean restoreBackupLinux(String fileBackup) {
		try {
			String[] executeCmd = new String[] { "/bin/sh", "-c",
					"mysql -u" + properties.getDbUser() + " -p" + properties.getDbPassword() + " " + "tp2" + " < " + fileBackup };
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

//	public static void main(String[] a) {
//		try {
//			ConnectionProperties prop=ConnectionPropertiesLoader.load();
//			new DBConnection(prop);
//			System.out.println("conecto");
//		} catch (Exception e) {
//			System.out.println("no conecto");
//		}
//	}
}
