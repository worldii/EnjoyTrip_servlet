package user.dao;

import java.sql.SQLException;

import user.dto.User;


public interface UserDao {
	
	public User login (User userDto) throws SQLException;
	public int signUp (User userDto) throws SQLException;
	public boolean idCheck (String userName) throws SQLException;
	public int modify(Long userId, User userDto) throws SQLException ;
	public String getPassword(String userName, String nickname) throws SQLException;
	public User select(long userId) throws SQLException ;
	public int delete(long userId) throws SQLException;
}
