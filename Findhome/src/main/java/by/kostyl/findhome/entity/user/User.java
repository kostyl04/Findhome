package by.kostyl.findhome.entity.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import by.kostyl.findhome.entity.AbstractEntity;
import by.kostyl.findhome.entity.offers.Contact;
import by.kostyl.findhome.entity.offers.Flat;

@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "user_seq", allocationSize = 1)
public class User extends AbstractEntity{
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private Date date;
	@Column
	private String role;
	@Column
	private String name;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade=CascadeType.ALL)
	private Set<Contact> contacts=new HashSet<Contact>(0);
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade=CascadeType.ALL)
	private Set<Flat> flats=new HashSet<Flat>(0);
	

	public User(String email, String password, Date date, String role) {
		super();
		this.email = email;
		this.password = password;
		this.date = date;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Set<Flat> getFlats() {
		return flats;
	}

	public void setFlats(Set<Flat> flats) {
		this.flats = flats;
	}

	public User() {
	}

}
