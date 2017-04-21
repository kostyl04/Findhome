package by.kostyl.findhome.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

import by.kostyl.findhome.entity.AbstractEntity;
import by.kostyl.findhome.form.models.UserRegistration;

@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "unconfirmedusers_seq", allocationSize = 1)
public class UnconfirmedUsers extends AbstractEntity{
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String urlforcheck;
	@Column
	private Date date;
	
	public UnconfirmedUsers() {
	}
	
	public UnconfirmedUsers(UserRegistration user,String generatedURL) {
		this.email=user.getEmail();
		this.password=user.getPassword();
		this.urlforcheck=generatedURL;
		this.date=new Date();
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public String getUrlforcheck() {
		return urlforcheck;
	}
	public void setUrlforcheck(String urlforcheck) {
		this.urlforcheck = urlforcheck;
	}

	@Override
	public String toString() {
		return "UnconfirmedUsers [email=" + email + ", password=" + password + ", urlforcheck=" + urlforcheck
				+ ", date=" + date + "]";
	}
	
}
