package persistence.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

	public static Connection getConnection(ConnectionProperties prop) throws SQLException, ClassNotFoundException {
		Class.forName(prop.getDbDriverClass());
		return DriverManager.getConnection(prop.getDbUrl(), prop.getDbUser(), prop.getDbPassword());
	}

	// METODOS A REFACTORIZAR
	public static boolean createBackupLinux(ConnectionProperties properties, String fileBackup)
			throws IOException, InterruptedException {
		String executeCmd = "mysqldump -u " + properties.getDbUser() + " -p" + properties.getDbPassword() + " " + "tp2"
				+ " -r " + fileBackup;
		Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		return runtimeProcess.waitFor() == 0;
	}

	public static boolean restoreBackupLinux(ConnectionProperties properties, String fileBackup)
			throws IOException, InterruptedException {
		String[] executeCmd = new String[] { "/bin/sh", "-c", "mysql -u" + properties.getDbUser() + " -p"
				+ properties.getDbPassword() + " " + "tp2" + " < " + fileBackup };
		Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		return runtimeProcess.waitFor() == 0;
	}

	public static void main(String[] a) {
		try {
			ConnectionProperties prop=ConnectionPropertiesLoader.load();
			Connection c=DBConnectionManager.getConnection(prop);
			c.close();
			System.out.println(c==DBConnectionManager.getConnection(prop));
			System.out.println("conecto");
		} catch (Exception e) {
			System.out.println("no conecto");
		}
	}
}
