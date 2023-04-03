package user.service;

import java.sql.SQLException;

import user.dto.User;



public interface UserService {
	
	public  User login(User user) throws SQLException;
	public  int signUp(User user) throws SQLException;
	public boolean idCheck(String userName) throws SQLException;
	public int modify(Long userId, User userdto) throws SQLException ;
	public String getPassword(String userName, String nickname) throws SQLException;
	public User select(long userId) throws SQLException ;
	public int delete(long userId) throws SQLException;

}
