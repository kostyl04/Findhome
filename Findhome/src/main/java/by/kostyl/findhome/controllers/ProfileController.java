package by.kostyl.findhome.controllers;


import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import by.kostyl.findhome.entity.offers.Contact;
import by.kostyl.findhome.entity.offers.Flat;
import by.kostyl.findhome.entity.user.User;
import by.kostyl.findhome.services.ProfileService;
import by.kostyl.findhome.services.RegistrationService;
import by.kostyl.findhome.xml.GeoStorage;
import by.kostyl.findhome.xml.RBdb;

@Controller
public class ProfileController {
	@Autowired
	ProfileService profileService;
	@Autowired
	RegistrationService regService;

	

	@RequestMapping("/profile")
	public ModelAndView profile(@ModelAttribute User user) {
		if (user == null)
			return new ModelAndView("redirect:/login");
		ModelAndView mav = new ModelAndView("profile");
		Set<Contact> contacts = profileService.getContacts(user.getId());
		Set<Flat> flats = profileService.getFlats(user.getId());
		mav.addObject("contacts", contacts);
		mav.addObject("flats", flats);
		return mav;
	}

	@RequestMapping("/profile/changename")
	public ModelAndView changename(@RequestParam String name, @ModelAttribute User user) {
		profileService.changeName(name, user);
		ModelAndView mav = new ModelAndView("redirect:/profile");
		return mav;

	}

	@RequestMapping("/profile/contacts/delete/{id}")
	public ModelAndView deleteContact(@PathVariable String id) {
		profileService.deleteContact(Integer.parseInt(id));
		ModelAndView mav = new ModelAndView("redirect:/profile");
		return mav;

	}

	@RequestMapping("/profile/contacts/add/{id}")
	public ModelAndView addContact(@PathVariable String id, @RequestParam String contactString,
			@RequestParam String contactType) {
		profileService.addContact(Integer.parseInt(id), contactType, contactString);
		ModelAndView mav = new ModelAndView("redirect:/profile");
		return mav;

	}

	@RequestMapping("/profile/flat")
	public ModelAndView flat() {
		
		ModelAndView mav = new ModelAndView("flat");

		return mav;

	}

	@ResponseBody
	@RequestMapping(value="/getDB",produces = "application/json; charset=utf-8")
	public RBdb getDB(HttpServletResponse response) throws JsonProcessingException{
		//ObjectMapper mapper=new ObjectMapper();
		//String jsonInString = mapper.writeValueAsString();
		return GeoStorage.getInstance().getDb();
	}
}
