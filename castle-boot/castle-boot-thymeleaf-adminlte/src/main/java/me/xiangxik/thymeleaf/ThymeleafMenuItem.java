package me.xiangxik.thymeleaf;

import me.xiangxik.data.domain.Hierarchical;

public interface ThymeleafMenuItem<T> extends Hierarchical<T> {

	public String getName();

	public String getCode();

	public String getIconCls();

	public String getHref();

	public String getParameters();
}
