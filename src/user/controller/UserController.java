package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import user.dto.Response;
import user.dto.User;
import user.service.UserServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		System.out.println(action);
		if ("login".equals(action)) {
			login(request, response);

		} else if ("logout".equals(action)) {
			System.out.println("logout");

			logout(request, response);
		} else if ("signUp".equals(action)) {
			signUp(request, response);
		} else if ("mvSignup".equals(action)) {
			mvSignup(request, response);
		} else if ("idCheck".equals(action)) {
			idCheck(request, response);
		}
		else if ("mvLogin".equals(action)) {
			mvLogin(request, response);
		}
		else if ("userInfo".equals(action)) {
			userInfo(request,response);
		}
		else if ("delete".equals(action)) {
			delete(request,response);
		}
		else if ("mvModify".equals(action)) {
			mvModify(request,response);
		}
		else if ("modify".equals(action)) {
			modify(request,response);
		}
		else if ("findPassword".equals(action)) {
			findPassword(request, response);
		}
		else if ("mvPassword".equals(action)) {
			mvPassword(request, response);
		}
		else {
			redirect(request, response, "/error/error.jsp");
		}
		

	}

	private void mvPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		redirect(request, response, "/password.jsp");
	}

	private void findPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String userName = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		try {
			String password = UserServiceImpl.getImpl().getPassword(userName, nickname);
			System.out.println(password);
			request.setAttribute("msg", userName+ "의 비밀번호는 "+ password + "입니다");
			forward(request, response, "login.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private void mvModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		redirect(request, response, "/mypage_modify.jsp");
		
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session =request.getSession();
		User user = (User) session.getAttribute("loginUser");

		if (user == null) {redirect(request, response, "/index.jsp");}
		
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String emailOption = request.getParameter("emailOption");
		

		if ("1".equals(emailOption)) {
			email+= "@naver.com";
		}
		else {
			email+="@google.com";
		}
			
		
		User userDto =User.builder().email(email).password(password).build();
		
	
		if (user !=null) {
			
			try {
				int result =UserServiceImpl.getImpl().modify( user.getUserId(), userDto);
				
				userDto = UserServiceImpl.getImpl().select(user.getUserId());
				session.setAttribute("loginUser", userDto);
				
				redirect(request, response, "/user?action=userInfo");

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				redirect(request, response, "/index.jsp");

			}
			
			
		}
		else redirect(request, response, "/index.jsp");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();

		User user = (User) session.getAttribute("loginUser");
		if (user !=null) {
			
			try {
				int result =UserServiceImpl.getImpl().delete(user.getUserId());
				redirect(request, response, "/user?action=logout");

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				redirect(request, response, "/index.jsp");

			}
			
			
		}
		else redirect(request, response, "/index.jsp");


		

	}

	private void userInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		redirect(request, response, "/mypage.jsp");
	}

	private void mvLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		redirect(request, response, "/login.jsp");
	}

	protected void forward(HttpServletRequest request, HttpServletResponse response, String location)
			throws ServletException, IOException {

		RequestDispatcher disp = request.getRequestDispatcher(location);
		disp.forward(request, response);

	}

	protected void redirect(HttpServletRequest request, HttpServletResponse response, String location)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + location);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		doGet(request, response);
	}

	protected void idCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 String userName = request.getParameter("nickname");
		 response.setContentType("application/json");
		 response.setCharacterEncoding("utf-8");
		 ObjectMapper objectMapper = new ObjectMapper();

		try {
			if (UserServiceImpl.getImpl().idCheck(userName)) 
			{
		
			    Response res = Response.builder().result(0).build();
			    String result = objectMapper.writeValueAsString(res);
			    response.getWriter().write(result);

			}
			else {
				   Response res = Response.builder().result(1).build();    
				   String result = objectMapper.writeValueAsString(res);
				   response.getWriter().write(result);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String emailOption = request.getParameter("emailOption");
		

		if ("1".equals(emailOption)) {
			email+= "@naver.com";
		}
		else {
			email+="@google.com";
		}
		
		User user= User.builder().password(password).nickName(nickname).email(email).name(name).build();
		try {
			
			int result = UserServiceImpl.getImpl().signUp(user);
			System.out.println(result);
			if (result != -1) {
				redirect(request, response, "/index.jsp");
			} 
			else {
				request.setAttribute("msg", "다시 입력해 주세요");
				forward(request, response, "/index.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			forward(request, response, "/index.jsp");
		}
	}

	private void mvSignup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			redirect(request, response, "/signup.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			redirect(request, response, "/index.jsp");
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession();
		ses.invalidate();
		System.out.println("로그아웃");
		redirect(request, response, "/index.jsp");

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println(userName+  " " + password);
		User userDto = User.builder().nickName(userName).password(password).build();
		try {
			User user  = UserServiceImpl.getImpl().login(userDto);
			
			System.out.print(user);
			if (user !=null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);
				// 존재 하여야함.
				System.out.println("login 성공");
				redirect(request, response, "/index.jsp");

			} else {
				request.setAttribute("msg", "로그인 실패");
				forward(request, response, "/index.jsp");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			redirect(request, response, "/index.jsp");

		}

	}
}
