package me.xiangxik.manage.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import me.xiangxik.data.domain.Userable;
import me.xiangxik.manage.module.system.entity.User;
import me.xiangxik.manage.module.system.service.MenuItemService;
import me.xiangxik.thymeleaf.ThymeleafMenuItem;
import me.xiangxik.thymeleaf.ThymeleafViewVariable;

@Component
public class ViewVariable implements ThymeleafViewVariable {

	@Autowired
	private MenuItemService menuItemService;

	@Autowired
	private AuditorAware<User> auditorAware;

	@Override
	public Userable getCurrentUser() {
		return auditorAware.getCurrentAuditor();
	}

	@Override
	public List<? extends ThymeleafMenuItem<?>> getMenuRoots() {
		return menuItemService.findRoots();
	}

}
