package by.kostyl.findhome.form.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.kostyl.findhome.entity.user.UnconfirmedUsers;
import by.kostyl.findhome.entity.user.User;
import by.kostyl.findhome.form.models.UserRegistration;
import by.kostyl.findhome.services.RegistrationService;

@Component
public class RegistrationValidator implements Validator {
	@Autowired
	RegistrationService regService;
	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	@Autowired
	@Qualifier("passwordValidator")
	PasswordValidator passwordValidator;

	@Override
	public boolean supports(Class<?> clazz) {

		return UserRegistration.class.equals(clazz);
	}

	@Override
	public void validate(Object u, Errors errors) {
		UserRegistration user = (UserRegistration) u;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "registrationForm.notEmpty.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "registrationForm.notEmpty.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "registrationForm.notEmpty.confirmPassword");
		if(!regService.checkEmail(user.getEmail(),UnconfirmedUsers.class)||!regService.checkEmail(user.getEmail(),User.class)){
			errors.rejectValue("email", "registrationForm.exists.email");
		}
		if (!emailValidator.validate(user.getEmail())) {
			errors.rejectValue("email", "registrationForm.pattern.email");
		}
		if (!passwordValidator.validate(user.getPassword())) {
			errors.rejectValue("password", "registrationForm.pattern.password");
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "registrationForm.diff.confirmPassword");
		}

	}

}
