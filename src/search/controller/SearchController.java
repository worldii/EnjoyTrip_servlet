package search.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import search.dao.SearchDaoImpl;
import search.dto.Place;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/Search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		//System.out.println("doGet");
		String reString = null;
		try {
			if(action.equals("gugun")) {
				reString = gugun(Integer.parseInt(request.getParameter("sidoCode")));
			}else if(action.equals("search")) {
				reString = search(request);
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		//System.out.println("Send Data :");
        //System.out.println(reString);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(reString);
	}
	
	public String gugun(int sidoCode) throws SQLException {
        Map<Integer, String> data=SearchDaoImpl.getImpl().getGugun(sidoCode);
        //System.out.println(data.toString());
        JSONArray array=new JSONArray();
        for(int key:data.keySet()) {
            JSONObject object=new JSONObject();
            object.put("code", key);
            object.put("name", data.get(key));
            array.add(object);
        }
        //System.out.println("array : "+array.toJSONString());
        JSONObject response = new JSONObject();
        response.put("items", array);
        return response.toJSONString();
    }
	
	protected String search(HttpServletRequest request) throws ServletException, IOException, SQLException {
		int sidoCode=Integer.parseInt(request.getParameter("sidoCode"));
		int gugunCode=Integer.parseInt(request.getParameter("gugunCode"));
		int contentTypeId=Integer.parseInt(request.getParameter("contentTypeId"));
		
		SearchDaoImpl dao=SearchDaoImpl.getImpl();
		ArrayList<Place> places=dao.search(sidoCode, gugunCode, contentTypeId);
		JSONArray array=new JSONArray();
        for(Place place:places) {
            JSONObject object=new JSONObject();
            object.put("title", place.getTitle());
            object.put("img", place.getImg());
            object.put("addr1", place.getAddr1());
            object.put("addr2", place.getAddr2());
            object.put("latitude", place.getLatitude());
            object.put("longitude", place.getLongtitude());
            array.add(object);
        }
        //System.out.println("array : "+array.toJSONString());
        //System.out.println("array size : "+array.size());
        JSONObject response = new JSONObject();
        response.put("places", array);
        return response.toJSONString();
	}

}
