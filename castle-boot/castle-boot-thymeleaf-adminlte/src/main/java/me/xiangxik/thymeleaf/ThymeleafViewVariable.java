package me.xiangxik.thymeleaf;

import java.util.List;

import me.xiangxik.data.domain.Userable;

public interface ThymeleafViewVariable {

	Userable getCurrentUser();

	public List<? extends ThymeleafMenuItem<?>> getMenuRoots();

}
