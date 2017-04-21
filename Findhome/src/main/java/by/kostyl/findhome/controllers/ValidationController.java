package by.kostyl.findhome.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import by.kostyl.findhome.entity.user.User;
import by.kostyl.findhome.form.models.UserLogin;
import by.kostyl.findhome.form.models.UserRegistration;
import by.kostyl.findhome.form.validators.LoginValidator;
import by.kostyl.findhome.form.validators.RegistrationValidator;
import by.kostyl.findhome.security.UserDetails;
import by.kostyl.findhome.services.RegistrationService;

@Controller
public class ValidationController {
	@Autowired
	RegistrationService regService;
	@Autowired
	RegistrationValidator registrationValidator;
	@Autowired
	LoginValidator loginValidator;

	

	@InitBinder("userRegistration")
	protected void userRegistrationBinder(WebDataBinder binder) {
		binder.setValidator(registrationValidator);
	}

	@InitBinder("userLogin")
	protected void userLoginBinder(WebDataBinder binder) {
		binder.setValidator(loginValidator);
	}

	@RequestMapping("/")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("main");
		return mav;

	}

	@RequestMapping("/register")
	public ModelAndView register(@ModelAttribute User user) {
		if (user != null)
			return new ModelAndView("redirect:/");
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("userRegistration", new UserRegistration());
		return mav;

	}

	@RequestMapping("/exit")
	public ModelAndView exit(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("user", null);
		Cookie cookie = WebUtils.getCookie(request, "email");
		if (cookie != null) {
			cookie.setValue(null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		return new ModelAndView("redirect:/");

	}

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView mav = new ModelAndView("login");
		if (error != null) {
			mav.addObject("error", "ERROR");
		}

		return mav;
	}
	/*
	 * @RequestMapping("/login") public ModelAndView login(@ModelAttribute User
	 * user) { if(user!=null) return new ModelAndView("redirect:/");
	 * ModelAndView mav = new ModelAndView("login"); mav.addObject("userLogin",
	 * new UserLogin()); return mav;
	 * 
	 * }
	 */

	@RequestMapping("/registeraction")
	public ModelAndView register(@ModelAttribute("userRegistration") @Validated UserRegistration user,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("register", result.getModel());
			mav.addObject("userRegistration", user);
			mav.addObject("first", false);
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:/registrationConfirm");
		mav.addObject("user", regService.mergeUncheckedUser(user));
		return mav;

	}

	@RequestMapping("/registration/confrim/{url}")
	public ModelAndView confirmRegistration(@PathVariable String url) {
		ModelAndView mav = new ModelAndView("login");
		regService.confirmUser(url);
		mav.addObject("userLogin", new UserLogin());
		return mav;

	}

	/*
	 * @RequestMapping("/loginaction") public ModelAndView
	 * login(@ModelAttribute("userLogin") @Validated UserLogin user,
	 * BindingResult result,
	 * 
	 * @RequestParam(required = false) boolean rememberme, Model model,
	 * HttpServletRequest request, HttpServletResponse response) { if
	 * (result.hasErrors()) { ModelAndView mav = new ModelAndView("login",
	 * result.getModel()); mav.addObject("userLogin", user);
	 * mav.addObject("first", false); return mav; }
	 * regService.loginUser(rememberme, user, request, response); ModelAndView
	 * mav = new ModelAndView("redirect:/"); mav.addObject("userRegistration",
	 * new UserRegistration()); return mav;
	 * 
	 * }
	 */
}
