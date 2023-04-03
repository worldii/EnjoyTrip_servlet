package hotPlace.service;

import java.sql.SQLException;
import java.util.ArrayList;

import hotPlace.dto.HotPlace;

public interface HotPlaceService {
	int addHotPlace(HotPlace hotPlace) throws SQLException;

	ArrayList<HotPlace> list() throws SQLException;
}
