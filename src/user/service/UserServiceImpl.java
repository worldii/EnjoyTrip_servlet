package user.service;

import java.sql.SQLException;

import user.dao.UserDao;
import user.dao.UserDaoImpl;
import user.dto.User;


public class UserServiceImpl implements UserService {

	private static  UserServiceImpl userServiceImpl = new UserServiceImpl(); 
	private UserServiceImpl() {}
	public static UserServiceImpl getImpl () {
		return userServiceImpl;
	}
	@Override
	public User login(User user) throws SQLException {
		// TODO Auto-generated method stub
		return UserDaoImpl.getImpl().login(user) ;
	}

	@Override
	public int signUp(User user) throws SQLException {
		// TODO Auto-generated method stub
		return UserDaoImpl.getImpl().signUp(user);
	}
	@Override
	public boolean idCheck(String userName) throws SQLException {
		// TODO Auto-generated method stub
		return UserDaoImpl.getImpl().idCheck(userName);
	}

	@Override
	public int modify(Long userId, User userdto) throws SQLException {
		// TODO Auto-generated method stub
		return UserDaoImpl.getImpl().modify(userId, userdto);
	}
	
	@Override
	public String getPassword(String userName, String nickname) throws SQLException {
		// TODO Auto-generated method stub
		return UserDaoImpl.getImpl().getPassword(userName, nickname);
	}

	@Override
	public int delete(long userId) throws SQLException {
		// TODO Auto-generated method stub
		return UserDaoImpl.getImpl().delete(userId);
	}
	@Override
	public User select(long userId) throws SQLException {
		// TODO Auto-generated method stub
		return UserDaoImpl.getImpl().select(userId);
	}

	
}
