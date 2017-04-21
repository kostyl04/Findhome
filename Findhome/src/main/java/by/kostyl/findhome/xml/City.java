package by.kostyl.findhome.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="city")
public class City {
	@Element(name="city_id")
	private int cityID;
	@Element(name="country_id")
	private int countryID;
	@Element(name="region_id")
	private int regionID;
	@Element(name="name")
	private String name;
	public int getCityID() {
		return cityID;
	}
	public void setCityID(int cityID) {
		this.cityID = cityID;
	}
	public int getCountryID() {
		return countryID;
	}
	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}
	public int getRegionID() {
		return regionID;
	}
	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
