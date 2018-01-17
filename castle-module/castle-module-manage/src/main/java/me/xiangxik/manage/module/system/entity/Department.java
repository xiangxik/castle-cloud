package me.xiangxik.manage.module.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import me.xiangxik.data.jpa.HierarchicalEntity;

@Entity
@Table(name = "tbl_department")
public class Department extends HierarchicalEntity<User, Long, Department> {

	private static final long serialVersionUID = 7729363319778564275L;

	@NotNull
	@Size(min = 2, max = 100)
	private String name;

	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
