package by.kostyl.findhome.controllers;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.kostyl.findhome.entity.offers.Contact;
import by.kostyl.findhome.entity.offers.Flat;
import by.kostyl.findhome.entity.user.User;
import by.kostyl.findhome.json.models.FlatModel;
import by.kostyl.findhome.services.FlatService;
import by.kostyl.findhome.services.ProfileService;
import by.kostyl.findhome.services.RegistrationService;
import by.kostyl.findhome.xml.GeoStorage;
import by.kostyl.findhome.xml.RBdb;

@Controller
public class FlatController {
	@Autowired
	RegistrationService regService;
	@Autowired
	FlatService flatService;
	@ModelAttribute("user")
	public User user(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, "email");
		User user = null;
		if (request.getSession().getAttribute("user") != null) {
			request.getSession().setAttribute("user", request.getSession().getAttribute("user"));
			return (User) request.getSession().getAttribute("user");
		}
		if (cookie != null) {
			try {
				String email = cookie.getValue();
				System.out.println(email);
				user = regService.getUser(email);
				request.setAttribute("user", user);
				return user;
			} catch (Exception e) {

			}
		}
		return user;

	}
	@ResponseBody
	@RequestMapping(value="/saveflat",produces = "application/json; charset=utf-8")
	public boolean saveFlat(HttpServletResponse response,HttpServletRequest request,@RequestBody FlatModel flat,@ModelAttribute User user) throws IOException{
		System.out.println(user.getId());
		flatService.saveFlat(flat,user.getId());
		return true;
	}
	@RequestMapping(value="/profile/flats/delete/{flatID}")
	public String deleteFlat(HttpServletResponse response,HttpServletRequest request,@PathVariable long flatID ) throws IOException{
		flatService.deleteFlat(flatID);
		return "redirect:/profile";
	}
}

