package by.kostyl.findhome.xml;


import java.util.Set;
import java.util.TreeSet;

import org.simpleframework.xml.core.Persister;

public class GeoStorage {
	private RBdb db;
	public static final GeoStorage INSTANCE = new GeoStorage();

	private GeoStorage() {
		try {
			this.db = getRBdb();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private RBdb getRBdb() throws Exception {
		Persister serializer = new Persister();
		ClassLoader loader = this.getClass().getClassLoader();
		
		RBdb rbdb = serializer.read(RBdb.class, loader.getResourceAsStream("RBdbxml/RBdb.xml"));
		return rbdb;
	}

	public RBdb getDb() {
		return db;
	}


	public static GeoStorage getInstance() {
		return INSTANCE;
	}
	public Set<City> getResults(int regionID){
		Set <City> set=new TreeSet();
		for(City city:db.getCitys()){
			if(city.getRegionID()==regionID)set.add(city);
		}
		return set;
	}
	
}
