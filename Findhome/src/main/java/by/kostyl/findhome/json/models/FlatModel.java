package by.kostyl.findhome.json.models;

import java.util.List;

public class FlatModel {
	private String region;
	private String city;
	private String adress;
	private String description;
	private List<String> comforts;
	private List<RoomModel> rooms;
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<String> getComforts() {
		return comforts;
	}
	public void setComforts(List<String> comforts) {
		this.comforts = comforts;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public List<RoomModel> getRooms() {
		return rooms;
	}
	public void setRooms(List<RoomModel> rooms) {
		this.rooms = rooms;
	}
	@Override
	public String toString() {
		return "Flat [region=" + region + ", city=" + city + ", adress=" + adress + ", description=" + description
				+ ", comforts=" + comforts + ", rooms=" + rooms + "]";
	}
	
	

}
