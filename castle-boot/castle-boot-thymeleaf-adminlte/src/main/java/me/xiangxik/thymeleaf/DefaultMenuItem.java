package me.xiangxik.thymeleaf;

import java.util.List;

public class DefaultMenuItem implements ThymeleafMenuItem<DefaultMenuItem> {

	private DefaultMenuItem parent;

	private List<DefaultMenuItem> children;

	private Integer sortNo;

	private String name;
	private String code;
	private String iconCls;
	private String href;
	private String parameters;

	public static DefaultMenuItem root(String name, String code) {
		return create(name, code, null, null, null);
	}

	public static DefaultMenuItem create(String name, String code, String iconCls, String href, String parameters) {
		DefaultMenuItem menuItem = new DefaultMenuItem(name, code, iconCls, href, parameters);
		return menuItem;
	}

	public DefaultMenuItem(String name, String code, String iconCls, String href, String parameters) {
		this.name = name;
		this.code = code;
		this.iconCls = iconCls;
		this.href = href;
		this.parameters = parameters;
	}

	@Override
	public DefaultMenuItem getParent() {
		return parent;
	}

	@Override
	public void setParent(DefaultMenuItem parent) {
		this.parent = parent;
	}

	@Override
	public List<DefaultMenuItem> getChildren() {
		return children;
	}

	@Override
	public void setChildren(List<DefaultMenuItem> children) {
		this.children = children;
	}

	@Override
	public Integer getSortNo() {
		return sortNo;
	}

	@Override
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getIconCls() {
		return iconCls;
	}

	@Override
	public String getHref() {
		return href;
	}

	@Override
	public String getParameters() {
		return parameters;
	}

	public boolean isLeaf() {
		return children == null || children.size() == 0;
	}

	public boolean isRoot() {
		return parent == null;
	}
}
