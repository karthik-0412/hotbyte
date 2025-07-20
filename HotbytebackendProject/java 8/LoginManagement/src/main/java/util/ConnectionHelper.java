package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class ConnectionHelper {
	public static Connection getConnection() {

		Connection con = null;

		try {

			ResourceBundle rb = ResourceBundle.getBundle("db");

			String driver = rb.getString("driver");
			String url = rb.getString("url");

			String user = rb.getString("user");
			String pwd = rb.getString("password");

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pwd);
		} catch (Exception msg) {

			System.out.println(msg.getMessage());
		}

		return con;

	}
}
