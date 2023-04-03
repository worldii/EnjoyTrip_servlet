package board.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.Board;
import board.service.BoardService;
import board.service.BoardServiceImpl;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/Board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		try {
			if(action.equals("Add")) {
				add(request,response);
				redirect(request, response, "/index.jsp");
			}else if(action.equals("List")) {
				list(request, response);
				forward(request, response, "/board.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			redirect(request, response, "/error/error.jsp");
		}
	}
	
	protected void forward(HttpServletRequest request, HttpServletResponse response, String location) throws ServletException, IOException {
		RequestDispatcher disp=request.getRequestDispatcher(location);
		disp.forward(request, response);
	}
	
	protected void redirect(HttpServletRequest request, HttpServletResponse response, String location) throws ServletException, IOException {
		response.sendRedirect(location);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String userId=request.getParameter("userId");
		String title=request.getParameter("title");
		String content=request.getParameter("title");
		
		Board board=new Board(userId, title, content);
		BoardService service=BoardServiceImpl.getImpl();
		service.add(board);
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		BoardService service=BoardServiceImpl.getImpl();
		ArrayList<Board> boards=service.list();
		request.setAttribute("boards", boards);
	}
	
	protected void template(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
