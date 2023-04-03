package search.dto;

public class Place {
	private String title;
	private String img;
	private String addr1;
	private String addr2;
	private String latitude;
	private String longtitude;
	public Place(String title, String img, String addr1, String addr2, String latitude, String longtitude) {
		super();
		this.title = title;
		this.img = img;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	@Override
	public String toString() {
		return "Place [title=" + title + ", img=" + img + ", addr1=" + addr1 + ", addr2=" + addr2 + ", latitude="
				+ latitude + ", longtitude=" + longtitude + "]";
	}
	
	
	
	
}
