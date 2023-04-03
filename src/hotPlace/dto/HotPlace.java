package hotPlace.dto;

import java.sql.Date;

public class HotPlace {
	private String Longitude; //경도
	private String Latitude; //위도
	private int articleNo;
	private String image; //db엔 만들었는데 폼에 입력은 없음
	private String placeName;
	private Date visiteDate;
	private int placeType;
	private String placeDescribe;
	
	
	//articleNo, image 없음
	public HotPlace(String longitude, String latitude, String placeName, Date visiteDate, int placeType,
			String placeDescribe) {
		super();
		Longitude = longitude;
		Latitude = latitude;
		this.placeName = placeName;
		this.visiteDate = visiteDate;
		this.placeType = placeType;
		this.placeDescribe = placeDescribe;
	}

	//image 없음
	public HotPlace(String longitude, String latitude, String image, String placeName, Date visiteDate,
			int placeType, String placeDescribe) {
		super();
		Longitude = longitude;
		Latitude = latitude;
		this.image = image;
		this.placeName = placeName;
		this.visiteDate = visiteDate;
		this.placeType = placeType;
		this.placeDescribe = placeDescribe;
	}

	//전체 받아옴
	public HotPlace(String longitude, String latitude, int articleNo, String image, String placeName, Date visiteDate,
			int placeType, String placeDescribe) {
		super();
		Longitude = longitude;
		Latitude = latitude;
		this.articleNo = articleNo;
		this.image = image;
		this.placeName = placeName;
		this.visiteDate = visiteDate;
		this.placeType = placeType;
		this.placeDescribe = placeDescribe;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Date getVisiteDate() {
		return visiteDate;
	}

	public void setVisiteDate(Date visiteDate) {
		this.visiteDate = visiteDate;
	}

	public int getPlaceType() {
		return placeType;
	}

	public void setPlaceType(int placeType) {
		this.placeType = placeType;
	}

	public String getPlaceDescribe() {
		return placeDescribe;
	}

	public void setPlaceDescribe(String placeDescribe) {
		this.placeDescribe = placeDescribe;
	}

	@Override
	public String toString() {
		return "hotPlace [Longitude=" + Longitude + ", Latitude=" + Latitude + ", articleNo=" + articleNo + ", image="
				+ image + ", placeName=" + placeName + ", visiteDate=" + visiteDate + ", placeType=" + placeType
				+ ", placeDescribe=" + placeDescribe + "]";
	}
	
	
	
}
