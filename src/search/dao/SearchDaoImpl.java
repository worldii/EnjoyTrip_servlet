package search.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import search.dto.Place;
import util.DBUtil;

public class SearchDaoImpl implements SearchDao{
	private static SearchDaoImpl impl=new SearchDaoImpl();
	private SearchDaoImpl() {
		
	}
	public static SearchDaoImpl getImpl() {
		return impl;
	}
	@Override
	public Map<Integer, String> getGugun(int sidoCode) throws SQLException {
		Map<Integer, String> result=new HashMap<Integer, String>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		try {
			conn=DBUtil.getInstance().getConnection();
			String sql="select * from gugun where sido_code=? \n";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sidoCode);
			
			set=pstmt.executeQuery();
			while(set.next()) {
				int gugunCode=set.getInt("gugun_code");
				String gugunName=set.getString("gugun_name");
				
				result.put(gugunCode, gugunName);
			}
		}
		finally {
			DBUtil.getInstance().close(set, pstmt, conn);
		}
		
		return result;
	}
	@Override
	public ArrayList<Place> search(int sidoCode, int gugunCode, int contentTypeId) throws SQLException {
		ArrayList<Place> result=new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		try {
			conn=DBUtil.getInstance().getConnection();
			String sql="select * from attraction_info where sido_code=? and gugun_code=? and content_type_id=? \n";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sidoCode);
			pstmt.setInt(2, gugunCode);
			pstmt.setInt(3, contentTypeId);
			
			set=pstmt.executeQuery();
			while(set.next()) {
				String title=set.getString("title");
				String img=set.getString("first_image");
				String addr1=set.getString("addr1");
				String addr2=set.getString("addr2");
				String latitude=set.getString("latitude");
				String longitude=set.getString("longitude");
				
				result.add(new Place(title, img, addr1, addr2, latitude, longitude));
			}
		}
		finally {
			DBUtil.getInstance().close(set, pstmt, conn);
		}
		
		return result;
	}
}
