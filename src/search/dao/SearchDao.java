package search.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import search.dto.Place;

public interface SearchDao {
	Map<Integer, String> getGugun(int sidoCode) throws SQLException;
	ArrayList<Place> search(int sidoCode, int gugunCode, int contentTypeId) throws SQLException;
}
