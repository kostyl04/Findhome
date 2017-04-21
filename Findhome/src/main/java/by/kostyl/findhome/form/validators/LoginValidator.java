package by.kostyl.findhome.form.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.kostyl.findhome.entity.user.User;
import by.kostyl.findhome.form.models.UserLogin;
import by.kostyl.findhome.form.models.UserRegistration;
import by.kostyl.findhome.services.RegistrationService;

@Component
public class LoginValidator implements Validator {
	@Autowired
	RegistrationService regService;

	@Override
	public boolean supports(Class<?> clazz) {

		return UserLogin.class.equals(clazz);
	}

	@Override
	public void validate(Object u, Errors errors) {
		UserLogin user = (UserLogin) u;
		if (regService.checkEmail(user.getEmail(), User.class)) {
			errors.rejectValue("email", "loginForm.wrong.email");
		} else if (!regService.checkPassword(user.getPassword(), user.getEmail())) {
			errors.rejectValue("password", "loginForm.wrong.password");
		}
		System.out.println(errors.getErrorCount());

	}

}
