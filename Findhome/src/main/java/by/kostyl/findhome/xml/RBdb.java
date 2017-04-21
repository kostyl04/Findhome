package by.kostyl.findhome.xml;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="RBdb")
public class RBdb {
	
	
	    @ElementList(inline=true, name="city")
	    private List<City> citys;
	    @ElementList(inline=true, name="region")
	    private List<Region> regions;

		public List<City> getCitys() {
			return citys;
		}

		public void setCitys(List<City> citys) {
			this.citys = citys;
		}

		public List<Region> getRegions() {
			return regions;
		}

		public void setRegions(List<Region> regions) {
			this.regions = regions;
		}

		
		

		
	
}
