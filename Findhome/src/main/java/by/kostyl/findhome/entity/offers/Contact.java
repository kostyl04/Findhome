package by.kostyl.findhome.entity.offers;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import by.kostyl.findhome.entity.AbstractEntity;
import by.kostyl.findhome.entity.user.User;


@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "contact_seq", allocationSize = 1)
public class Contact extends AbstractEntity{
	@Column
	private String contactType;
	@Column
	private String contactString;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
	
	
	public Contact() {
	}


	public Contact(String contactType, String contactString, User user) {
		super();
		this.contactType = contactType;
		this.contactString = contactString;
		this.user = user;
	}


	public String getContactType() {
		return contactType;
	}


	public void setContactType(String contactType) {
		this.contactType = contactType;
	}


	public String getContactString() {
		return contactString;
	}


	public void setContactString(String contactString) {
		this.contactString = contactString;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
