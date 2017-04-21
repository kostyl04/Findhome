package by.kostyl.findhome.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import by.kostyl.findhome.dal.RegistrationDao;
import by.kostyl.findhome.dal.SecurityDao;
import by.kostyl.findhome.services.RegistrationService;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	//get user from the database, via Hibernate
		@Autowired
		private SecurityDao userService;

		@Transactional(readOnly=true)
		@Override
		public UserDetails loadUserByUsername(final String email)
			throws UsernameNotFoundException {
			by.kostyl.findhome.entity.user.User user = userService.findUserByEmail(email);
			
			List<GrantedAuthority> authorities =buildUserAuthority(user.getRole());
			User userDetail = buildUserForAuthentication(user, authorities);
			return userDetail;

		}

		// Converts com.mkyong.users.model.User user to
		// org.springframework.security.core.userdetails.User
		private User buildUserForAuthentication(by.kostyl.findhome.entity.user.User user,
			List<GrantedAuthority> authorities) {
			return new by.kostyl.findhome.security.UserDetails(user.getEmail(), user.getPassword(),
				true, true, true, true, authorities,user);
		}

		private List<GrantedAuthority> buildUserAuthority(String role) {

			Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
				setAuths.add(new SimpleGrantedAuthority(role));
			List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

			return Result;
		}
		public void test(){
			System.out.println("YES");
		}

}