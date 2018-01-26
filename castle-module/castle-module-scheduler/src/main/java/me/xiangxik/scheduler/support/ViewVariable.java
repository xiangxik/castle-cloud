package me.xiangxik.scheduler.support;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import me.xiangxik.data.domain.Userable;
import me.xiangxik.thymeleaf.DefaultMenuItem;
import me.xiangxik.thymeleaf.DefaultUser;
import me.xiangxik.thymeleaf.ThymeleafMenuItem;
import me.xiangxik.thymeleaf.ThymeleafViewVariable;

@Component
public class ViewVariable implements ThymeleafViewVariable {

	private Userable currentUser = new DefaultUser("管理员");

	@Override
	public Userable getCurrentUser() {
		return currentUser;
	}

	@Override
	public List<? extends ThymeleafMenuItem<?>> getMenuRoots() {
		return Lists.newArrayList(DefaultMenuItem.create("我的工作台", "dashboard", "fa fa-dashboard", "/", null),
				DefaultMenuItem.create("作业列表", "job", "fa fa-tasks", "/job", null));
	}

}
