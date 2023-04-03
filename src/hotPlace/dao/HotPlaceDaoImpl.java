package hotPlace.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hotPlace.dto.HotPlace;
import util.DBUtil;

public class HotPlaceDaoImpl implements HotPlaceDao{
	
	private static HotPlaceDaoImpl impl=new HotPlaceDaoImpl();
	private HotPlaceDaoImpl() {	}
	public static HotPlaceDaoImpl getImpl() {
		return impl;
	}

	@Override
	public int addHotPlace(HotPlace hotPlace) throws SQLException {
		int result=-1;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBUtil.getInstance().getConnection();
			String sql="insert into hotplace (longitude, latitude, place_name, visite_date, place_type, place_describe) values (?,?,?,?,?,?) \n";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, hotPlace.getLongitude());
			pstmt.setString(2, hotPlace.getLatitude());
			pstmt.setString(3, hotPlace.getPlaceName());
			pstmt.setDate(4, hotPlace.getVisiteDate());
			pstmt.setInt(5, hotPlace.getPlaceType());
			pstmt.setString(6, hotPlace.getPlaceDescribe());
			
			result=pstmt.executeUpdate();
		}finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
		
		
		return result;
	}
	@Override
	public ArrayList<HotPlace> list() throws SQLException {
		ArrayList<HotPlace> result=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet set=null;
		try {
			conn=DBUtil.getInstance().getConnection();
			String sql="select * from hotplace \n";
			pstmt=conn.prepareStatement(sql);
			
			set=pstmt.executeQuery();
			
			while(set.next()) {
				String Longitude=set.getString("longitude");
				String Latitude=set.getString("latitude");
				int articleNo=set.getInt("article_no");
				String image=set.getString("image");
				String placeName=set.getString("place_name");
				Date visiteDate=set.getDate("visite_date");
				int placeType=set.getInt("place_type");
				String placeDescribe=set.getString("place_describe");
				
				result.add(new HotPlace(Longitude, Latitude, articleNo, image, placeName, visiteDate, placeType, placeDescribe));
			}
			
		}finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
		
		
		return result;
	}
	
}
