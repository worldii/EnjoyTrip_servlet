package hotPlace.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotPlace.dto.HotPlace;
import hotPlace.service.HotPlaceService;
import hotPlace.service.HotPlaceServiceImpl;

/**
 * Servlet implementation class hotPlaceController
 */
@WebServlet("/HotPlace")
public class HotPlaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HotPlaceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		//hotplace기능 : 글 저장
		try {
			if(action.equals("addHotPlace")) {
				addHotPlace(request,response);
				forward(request, response, "/HotPlace?action=list");
			}
			else if(action.equals("list")){
				list(request, response);
				forward(request, response, "/hotPlaceList.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
//			redirect(request,response, "/error/error.jsp");
		}
	}
	
	protected void forward(HttpServletRequest request, HttpServletResponse response, String location) throws ServletException, IOException {
		RequestDispatcher disp=request.getRequestDispatcher(location);
		disp.forward(request, response);
	}
	
	protected void redirect(HttpServletRequest request, HttpServletResponse response, String location) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+ location);
	}
	
	protected void addHotPlace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String Longitude=request.getParameter("Longitude");
		String Latitude=request.getParameter("Latitude");
		String placeName=request.getParameter("placeName");
		Date visiteDate=Date.valueOf(request.getParameter("visiteDate"));
		int placeType=Integer.parseInt(request.getParameter("placeType"));
		String placeDescribe=request.getParameter("placeDescribe");
		
		HotPlace hotplace=new HotPlace(Longitude, Latitude, placeName, visiteDate, placeType, placeDescribe);
		HotPlaceService service=HotPlaceServiceImpl.getImpl();
		int result = service.addHotPlace(hotplace);
		System.out.println(result);
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HotPlaceService service=HotPlaceServiceImpl.getImpl();
		ArrayList<HotPlace> list=service.list();
		request.setAttribute("articles", list);
	}
	
	protected void template(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
