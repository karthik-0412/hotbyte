package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Login;
import util.ConnectionHelper;

public class LoginDaoImpl implements LoginDao {

	static Connection con;
	PreparedStatement stat;

	static {
		try {
			con = ConnectionHelper.getConnection();
		} catch (Exception e) {
			System.out.println("Connection Error: " + e.getMessage());
		}
	}

	@Override
	public boolean signUp(Login login) {
		try {
			String sql = "INSERT INTO Login VALUES (?, ?, ?, ?)";
			stat = con.prepareStatement(sql);
			stat.setInt(1, login.getUserId());
			stat.setString(2, login.getName());
			stat.setString(3, login.getEmail());
			stat.setString(4, login.getPassword());

			int rows = stat.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			System.out.println("SignUp Error: " + e.getMessage());
		}
		return false;
	}

	@Override
	public String forgotPassword(int userId, String email) {
		try {
			String sql = "SELECT password FROM Login WHERE userId = ? AND email = ?";
			stat = con.prepareStatement(sql);
			stat.setInt(1, userId);
			stat.setString(2, email);
			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				return rs.getString("password");
			} else {
				System.out.println("No matching user found.");
			}

		} catch (Exception e) {
			System.out.println("ForgotPassword Error: " + e.getMessage());
		}
		return null;
	}

	@Override
	public Login signIn(int userId, String password) {
		try {
			String sql = "SELECT * FROM Login WHERE userId = ? AND password = ?";
			stat = con.prepareStatement(sql);
			stat.setInt(1, userId);
			stat.setString(2, password);
			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				return new Login(rs.getInt("userId"), 
								 rs.getString("name"), 
								 rs.getString("email"),
								 rs.getString("password"));
			} else {
				System.out.println("Invalid credentials.");
			}

		} catch (Exception e) {
			System.out.println("SignIn Error: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Login> getAllUsers() {
		List<Login> users = new ArrayList<>();
		try {
			String sql = "SELECT userId, name FROM Login";
			stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Login user = new Login();
				user.setUserId(rs.getInt("userId"));
				user.setName(rs.getString("name"));
				users.add(user);
			}

		} catch (Exception e) {
			System.out.println("GetAllUsers Error: " + e.getMessage());
		}
		return users;
	}
}
