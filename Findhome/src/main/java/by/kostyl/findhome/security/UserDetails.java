package by.kostyl.findhome.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetails extends User {
	private by.kostyl.findhome.entity.user.User user;
	public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,by.kostyl.findhome.entity.user.User user) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.user=user;
	}
	public by.kostyl.findhome.entity.user.User getUser() {
		return user;
	}
	public void setUser(by.kostyl.findhome.entity.user.User user) {
		this.user = user;
	}
	

}
