package by.kostyl.findhome.dal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.kostyl.findhome.entity.user.User;

@Repository
@Transactional
public class SecurityDao {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public User findUserByEmail(String email) {

		List<User> users = new ArrayList<User>();

		Query query = sessionFactory.getCurrentSession()
				.createQuery("select u from User u where u.email=\'" + email + "\'");
		users = query.getResultList();
		if (!users.isEmpty()) {
			return users.get(0);
		}
		return null;

	}
	
}
