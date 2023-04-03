package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.dto.Board;
import util.DBUtil;

public class BoardDaoImpl implements BoardDao{
	
	private static BoardDaoImpl impl=new BoardDaoImpl();
	private BoardDaoImpl() {	}
	
	public static BoardDaoImpl getImpl() {
		return impl;
	}
	
	@Override
	public int add(Board board) throws SQLException {
		int result=-1;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBUtil.getInstance().getConnection();
			String sql="insert into board (user_id, title, content) values (?,?,?) \n";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			
			result=pstmt.executeUpdate();
			
		}finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
		
		return result;
	}

	@Override
	public ArrayList<Board> list() throws SQLException {
		ArrayList<Board> boards=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		
		try {
			conn=DBUtil.getInstance().getConnection();
			String sql="select * from board";
			pstmt=conn.prepareStatement(sql);
			set=pstmt.executeQuery();
			
			while(set.next()) {
				int articleNo=set.getInt("article_no");
				String userId=set.getString("user_id");
				String title=set.getString("title");
				String content=set.getString("content");
				
				boards.add(new Board(articleNo, userId, title, content));
			}
			
		}finally {
			DBUtil.getInstance().close(set, pstmt, conn);
		}
		
		return boards;
	}

	
}
