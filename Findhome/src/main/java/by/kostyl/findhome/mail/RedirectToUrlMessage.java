package by.kostyl.findhome.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import by.kostyl.findhome.entity.user.UnconfirmedUsers;

public class RedirectToUrlMessage extends MessageTemplate {
	private final static String subject="Регистрация на сайте findhome.by";
	public RedirectToUrlMessage(UnconfirmedUsers user){
		super.setTo(user.getEmail());
		super.setMessage(this.generateMessage(user));
		super.setSubject(this.subject);
	};

	public String generateMessage(UnconfirmedUsers user){
		String msg="Здравствуйте "+user.getEmail()+"!\n"+"Вы прошли регистрацию на сайте findhome.by, пожалуйста перейдите на ссылку для подтверждения регистрации:\n"+"http://localhost:8080/FindHome/registration/confrim/"+user.getUrlforcheck()+"\n"+"Ваш пароль:"+user.getPassword();
		return msg;
	}
	
	
}
