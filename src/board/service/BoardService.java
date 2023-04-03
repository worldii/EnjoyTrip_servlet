package board.service;

import java.sql.SQLException;
import java.util.ArrayList;

import board.dto.Board;

public interface BoardService {
	int add(Board board) throws SQLException;

	ArrayList<Board> list() throws SQLException;
}
