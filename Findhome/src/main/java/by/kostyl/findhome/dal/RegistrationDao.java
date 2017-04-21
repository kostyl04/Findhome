package by.kostyl.findhome.dal;

import java.util.List;

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

import by.kostyl.findhome.entity.user.UnconfirmedUsers;
import by.kostyl.findhome.entity.user.User;

@Repository
@Transactional
public class RegistrationDao {
	@Autowired
	private SessionFactory sessionFactory;

	public RegistrationDao() {

	}

	public UnconfirmedUsers getUserByURL(String url) {
		Query query = currentSession()
				.createQuery("select u from UnconfirmedUsers u where u.urlforcheck=\'" + url + "\'");
		List<UnconfirmedUsers> list = query.getResultList();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;

	}

	public User getUserByEmail(String email) {
		System.out.println("dao+ " + email);
		
		Query query = currentSession()
				.createQuery("select u from User u where u.email=\'" + email + "\'");
		System.out.println("userem= ");
		List<User> list = query.getResultList();
		
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;

	}

	public <T> boolean checkEmail(String email, Class<T> type) {
		Query query = currentSession()
				.createQuery("select u from " + type.getSimpleName() + " u where u.email=\'" + email + "\'");
		List<T> list = (List<T>) query.getResultList();
		if (list.isEmpty())
			return true;
		return false;
	}

	public String checkPassword(String email) {
		Query query = currentSession().createQuery("select u.password from User u where u.email=\'" + email + "\'");
		List<String> list = (List<String>) query.getResultList();
		return list.get(0);
	}

	public boolean checkGeneratedUrl(String url) {
		Query query = currentSession()
				.createQuery("select u from UnconfirmedUsers u where u.urlforcheck=\'" + url + "\'");
		List<UnconfirmedUsers> list = (List<UnconfirmedUsers>) query.getResultList();
		if (list.isEmpty())
			return true;
		return false;

	}

	private Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		// Session currentSession = sessionFactory.openSession();
		return currentSession;
	}
}
