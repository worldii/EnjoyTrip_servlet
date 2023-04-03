package hotPlace.service;

import java.sql.SQLException;
import java.util.ArrayList;

import hotPlace.dao.HotPlaceDao;
import hotPlace.dao.HotPlaceDaoImpl;
import hotPlace.dto.HotPlace;

public class HotPlaceServiceImpl implements HotPlaceService{
	
	private static HotPlaceServiceImpl impl=new HotPlaceServiceImpl();
	private HotPlaceServiceImpl() {
		
	}
	public static HotPlaceServiceImpl getImpl() {
		return impl;
	}
	
	@Override
	public int addHotPlace(HotPlace hotPlace) throws SQLException {
		int result=-1;
		
		HotPlaceDao dao=HotPlaceDaoImpl.getImpl();
		result = dao.addHotPlace(hotPlace);
		
		return result;
	}
	@Override
	public ArrayList<HotPlace> list() throws SQLException {
		HotPlaceDao dao=HotPlaceDaoImpl.getImpl();
		ArrayList<HotPlace> list=dao.list();
		return list;
	}

}
