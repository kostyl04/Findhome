package by.kostyl.findhome.dal;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.kostyl.findhome.entity.offers.Contact;
import by.kostyl.findhome.entity.offers.Flat;
import by.kostyl.findhome.entity.user.UnconfirmedUsers;
import by.kostyl.findhome.entity.user.User;

@Repository
@Transactional
public class ProfileDao {
	@Autowired
	private SessionFactory sessionFactory;

	public ProfileDao() {

	}

	

	public Set<Contact> getContacts(Long long1) {
		Set set =currentSession().find(User.class, long1).getContacts();
				set.size();
				
		
		return set;

	}
	public Set<Flat> getFlats(Long long1) {
		Set set =currentSession().find(User.class, long1).getFlats();
				set.size();
				
		
		return set;

	}

	private Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		// Session currentSession = sessionFactory.openSession();
		return currentSession;
	}
}
