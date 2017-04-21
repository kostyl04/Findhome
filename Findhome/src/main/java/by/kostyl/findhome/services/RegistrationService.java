package by.kostyl.findhome.services;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import by.kostyl.findhome.dal.CrudDao;
import by.kostyl.findhome.dal.CrudDaoBean;
import by.kostyl.findhome.dal.RegistrationDao;
import by.kostyl.findhome.entity.offers.Contact;
import by.kostyl.findhome.entity.user.UnconfirmedUsers;
import by.kostyl.findhome.entity.user.User;
import by.kostyl.findhome.form.models.UserLogin;
import by.kostyl.findhome.form.models.UserRegistration;
import by.kostyl.findhome.mail.Mailer;
import by.kostyl.findhome.mail.RedirectToUrlMessage;

@Service
public class RegistrationService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	CrudDao cruddao;
	@Autowired
	RegistrationDao regDao;
	
	public UnconfirmedUsers mergeUncheckedUser(UserRegistration user) {
		UnconfirmedUsers u = new UnconfirmedUsers(user, generateURL());
		Mailer m = new Mailer();
		m.send(new RedirectToUrlMessage(u));
		u.setPassword(passwordEncoder.encode(user.getPassword()));
		cruddao.merge(u);

		return u;
	}

	public void confirmUser(String url) {
		UnconfirmedUsers unconfirmUser = regDao.getUserByURL(url);
		if (unconfirmUser != null) {
			User user = new User(unconfirmUser.getEmail(), unconfirmUser.getPassword(), unconfirmUser.getDate(),
					"user");
			
			Contact contact = new Contact("email", user.getEmail(), user);
			user.getContacts().add(contact);
			
			
			cruddao.merge(user);
			cruddao.delete(UnconfirmedUsers.class, unconfirmUser.getId());
		}

	}

	public <T> boolean checkEmail(String email, Class<T> type) {
		return regDao.checkEmail(email, type);
	}

	public boolean checkPassword(String password, String email) {
		String pw = regDao.checkPassword(email);
		if (passwordEncoder.matches(password, pw))
			return true;
		return false;

	}
	public void loginUser(boolean checked,UserLogin userLogin,HttpServletRequest request,HttpServletResponse response){
		User user=regDao.getUserByEmail(userLogin.getEmail());
		request.getSession().setAttribute("user", user);
		if(checked){
			Cookie cookie=new Cookie("email",user.getEmail());
			cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
		}
	}
	
	public User getUser(String email){
		System.out.println("SERVICE");
		return (User)regDao.getUserByEmail(email);
	}
	private String generateURL() {
		String url = Long.toHexString(Double.doubleToLongBits(Math.random()))
				+ Long.toHexString(Double.doubleToLongBits(Math.random()))
				+ Long.toHexString(Double.doubleToLongBits(Math.random()));
		while (!regDao.checkGeneratedUrl(url)) {
			url = Long.toHexString(Double.doubleToLongBits(Math.random()))
					+ Long.toHexString(Double.doubleToLongBits(Math.random()))
					+ Long.toHexString(Double.doubleToLongBits(Math.random()));
		}
		return url;

	}
	
}
