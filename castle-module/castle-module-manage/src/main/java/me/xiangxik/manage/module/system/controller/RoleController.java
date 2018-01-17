package me.xiangxik.manage.module.system.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.querydsl.core.types.Predicate;

import me.xiangxik.manage.module.system.entity.Role;
import me.xiangxik.webapp.EntityController;

@Controller
@RequestMapping("/system/role")
public class RoleController extends EntityController<Role, Long> {

	@Override
	public Page<Role> doPage(Predicate predicate, Pageable pageable) {
		return super.doInternalPage(predicate, pageable);
	}

}
