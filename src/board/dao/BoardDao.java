package board.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import board.dto.Board;

public interface BoardDao {
	int add(Board board) throws SQLException;

	ArrayList<Board> list() throws SQLException;
}
