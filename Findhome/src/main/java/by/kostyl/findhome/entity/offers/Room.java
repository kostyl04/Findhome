package by.kostyl.findhome.entity.offers;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "room_seq", allocationSize = 1)
public class Room extends AbstractEntity {
	@Column
	private String name;
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "room",cascade=CascadeType.ALL) 
	private List <RoomPhoto> photos;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="flat_id", nullable=false)
	private Flat flat;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RoomPhoto> getPhotos() {
		return photos;
	}
	public void setPhotos(List<RoomPhoto> photos) {
		this.photos = photos;
	}
	public Flat getFlat() {
		return flat;
	}
	public void setFlat(Flat flat) {
		this.flat = flat;
	}

}
