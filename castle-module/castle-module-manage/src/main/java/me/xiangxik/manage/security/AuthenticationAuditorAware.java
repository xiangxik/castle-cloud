package me.xiangxik.manage.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import me.xiangxik.manage.module.system.entity.User;
import me.xiangxik.manage.security.DefaultDetailsService.CurrentUserDetails;

@Component
public class AuthenticationAuditorAware implements AuditorAware<User> {

	@Override
	public User getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof CurrentUserDetails) {
			return ((CurrentUserDetails) principal).getCustomUser();
		}
		return null;
	}

}
