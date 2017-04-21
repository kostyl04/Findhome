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

@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "room_foto_seq", allocationSize = 1)
public class RoomPhoto extends AbstractEntity {
	@Column(length = 500000) 
	private byte [] source;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="room_id", nullable=false)
    private Room room;
	
	public byte[] getSource() {
		return source;
	}
	public void setSource(byte[] source) {
		this.source = source;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
}
