package by.kostyl.findhome.entity.offers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import by.kostyl.findhome.entity.AbstractEntity;
import by.kostyl.findhome.entity.user.User;

@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "flat_seq", allocationSize = 1)
public class Flat extends AbstractEntity {
	private String region;
	private String city;
	private String adress;
	private String description;
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "flat",cascade=CascadeType.ALL)
	private Set<Room> rooms=new HashSet<Room>(0);
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "flat",cascade=CascadeType.ALL)
	private Set<Comfort> comforts=new HashSet<Comfort>(0);
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Room> getRooms() {
		return rooms;
	}
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
	public Set<Comfort> getComforts() {
		return comforts;
	}
	public void setComforts(Set<Comfort> comforts) {
		this.comforts = comforts;
	}
	public Flat() {
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

	
}
