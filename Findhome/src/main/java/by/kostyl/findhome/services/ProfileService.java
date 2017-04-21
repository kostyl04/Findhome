package by.kostyl.findhome.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.kostyl.findhome.dal.CrudDao;
import by.kostyl.findhome.dal.ProfileDao;
import by.kostyl.findhome.dal.RegistrationDao;
import by.kostyl.findhome.entity.offers.Contact;
import by.kostyl.findhome.entity.offers.Flat;
import by.kostyl.findhome.entity.user.User;

@Service
public class ProfileService {
	@Autowired
	CrudDao cruddao;
	@Autowired
	ProfileDao profileDao;

	public void changeName(String name, User user) {
		user.setName(name);
		cruddao.merge(user);

	}
	public Set<Contact> getContacts(Long long1){
		
		return profileDao.getContacts(long1);
	}
	public void deleteContact(int id){
		cruddao.delete(Contact.class, id);
	}
	public void addContact(int id,String contactType,String contactString){
		User user=new User();
		user.setId((long) id);
		Contact contact=new Contact();
		contact.setContactType(contactType);
		contact.setContactString(contactString);
		contact.setUser(user);
		cruddao.merge(contact);
	}
	public Set<Flat> getFlats(Long long1) {
		
		return profileDao.getFlats(long1);
	}
}
