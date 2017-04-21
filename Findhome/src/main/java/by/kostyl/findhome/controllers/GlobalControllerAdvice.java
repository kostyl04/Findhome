package by.kostyl.findhome.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import by.kostyl.findhome.entity.user.User;
import by.kostyl.findhome.security.UserDetails;

@ControllerAdvice
public class GlobalControllerAdvice {
	@ModelAttribute("user")
	public User getMsg(Authentication auth) {
		if (auth != null) {
			UserDetails u = (UserDetails) auth.getPrincipal();
			return u.getUser();
		}
		return null;

	}
}
