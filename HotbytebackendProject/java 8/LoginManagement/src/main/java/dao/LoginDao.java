package dao;

import java.util.List;

import model.Login;

public interface LoginDao {
	boolean signUp(Login login);
    String forgotPassword(int userId, String email);
    Login signIn(int userId, String password);
    List<Login> getAllUsers();
}
