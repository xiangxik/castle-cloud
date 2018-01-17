package me.xiangxik.manage.module.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.querydsl.core.types.Predicate;

import me.xiangxik.manage.module.system.entity.User;
import me.xiangxik.manage.module.system.service.UserService;
import me.xiangxik.webapp.EntityController;

@Controller
@RequestMapping("/system/user")
public class UserController extends EntityController<User, Long> {

	@Autowired
	private UserService userService;

	@RequestMapping("/test")
	public String test() {
		return "aa" + userService.count();
	}

	@Override
	public Page<User> doPage(Predicate predicate, Pageable pageable) {
		return super.doInternalPage(predicate, pageable);
	}
}
