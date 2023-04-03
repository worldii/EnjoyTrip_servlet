package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.dto.User;
import util.DBUtil;

public class UserDaoImpl implements UserDao {

	private UserDaoImpl() {
	}

	private static UserDaoImpl userDao = new UserDaoImpl();

	public static UserDaoImpl getImpl() {
		return userDao;
	}

	public boolean idCheck(String userName) throws SQLException {
		Connection con = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			con = DBUtil.getInstance().getConnection();
			sql.append("select * from user where nickname = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, userName);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				return false;
			}
		} finally {
			DBUtil.getInstance().close(rset, pstmt, con);
		}
		return true;
	}

	@Override
	public User login(User userDto) throws SQLException {
		// TODO Auto-generated method stub

		String nickname = userDto.getNickName();
		String password = userDto.getPassword();

		Connection con = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			con = DBUtil.getInstance().getConnection();
			sql.append("select * from user where nickname = ? and password = ? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, nickname);
			pstmt.setString(2, password);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				return User.builder().email(rset.getString("email")).name(rset.getString("name"))
						.nickName(rset.getString("nickname")).userId(rset.getLong("user_id"))
						.password(rset.getString("password")).build();
			}
		} finally {
			DBUtil.getInstance().close(rset, pstmt, con);
		}

		return null;
	}

	@Override
	public int signUp(User userDto) throws SQLException {
		// TODO Auto-generated method stub
		String name = userDto.getName();
		String email = userDto.getEmail();
		String password = userDto.getPassword();
		String nickName = userDto.getNickName();

		System.out.println("password" + password);
		Connection con = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		int result = -1;

		try {
			con = DBUtil.getInstance().getConnection();
			sql.append("insert into user (name, nickname, password, email) values (?, ?,?,?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, nickName);
			pstmt.setString(3, password);
			pstmt.setString(4, email);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("회원가입 실패");
		}

		return result;
	}

	@Override
	public int modify(Long userId, User userDto) throws SQLException {
		// TODO Auto-generated method stub
		int result = -1;

		Connection con = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getInstance().getConnection();
			sql.append("update user set  password = ? , email = ?  where user_id = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, userDto.getPassword());
			pstmt.setString(2, userDto.getEmail());
			pstmt.setLong(3, userId);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("수정실패");
		}
		return result;
	}

	@Override
	public String getPassword(String userName, String nickname) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rset = null;
		Connection con = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getInstance().getConnection();
			sql.append("select * from user where name = ? and nickname = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, userName);
			pstmt.setString(2, nickname);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				return rset.getString("password");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("회원가입 실패");
		}
		return null;
	}

	@Override
	public User select(long userId) throws SQLException {
		// TODO Auto-generated method stub

		ResultSet rset = null;

		Connection con = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			con = DBUtil.getInstance().getConnection();
			sql.append("select * from user where user_id = ?  ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setLong(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				return User.builder().email(rset.getString("email")).name(rset.getString("name"))
						.nickName(rset.getString("nickname")).userId(rset.getLong("user_id"))
						.password(rset.getString("password")).build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("회원가입 실패");
		}
		return null;
	}

	@Override
	public int delete(long userId) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		int result = -1;

		try {
			con = DBUtil.getInstance().getConnection();
			sql.append("delete from user where user_id = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setLong(1, userId);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("회원가입 실패");
		}
		return result;
	}

}
