package board.service;

import java.sql.SQLException;
import java.util.ArrayList;

import board.dao.BoardDao;
import board.dao.BoardDaoImpl;
import board.dto.Board;

public class BoardServiceImpl implements BoardService{
	private static BoardServiceImpl impl=new BoardServiceImpl();
	private BoardServiceImpl() {	}
	
	public static BoardServiceImpl getImpl() {
		return impl;
	}

	@Override
	public int add(Board board) throws SQLException {
		int result=-1;
		BoardDao dao=BoardDaoImpl.getImpl();
		result=dao.add(board);
		return result;
	}

	@Override
	public ArrayList<Board> list() throws SQLException {
		BoardDao dao=BoardDaoImpl.getImpl();
		ArrayList<Board> boards=dao.list();
		return boards;
	}
	
	
}
