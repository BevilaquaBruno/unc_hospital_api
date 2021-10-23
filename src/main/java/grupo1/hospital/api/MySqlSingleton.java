package grupo1.hospital.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlSingleton {
	private static Connection singletonConnection;
	
	public static Connection getConnection() {
		if(singletonConnection == null) {
			return conectar();
		}else {
			return singletonConnection;
		}
	}
	
	public static Connection conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			singletonConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unc_hospital", "never publish your user in github", "never publish your password in github");
			return singletonConnection;
		} catch (SQLException | ClassNotFoundException e) {
			return null;
		}
	}
	
	public void desconectar(Connection conn) {
		singletonConnection = null;
	}
}
