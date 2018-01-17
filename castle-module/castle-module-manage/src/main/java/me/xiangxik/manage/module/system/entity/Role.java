package me.xiangxik.manage.module.system.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import me.xiangxik.data.domain.Disabledable;
import me.xiangxik.data.domain.Lockedable;
import me.xiangxik.data.jpa.DataEntity;

@Entity
@Table(name = "tbl_role")
public class Role extends DataEntity<User, Long> implements Lockedable, Disabledable {

	private static final long serialVersionUID = -1297638531448069645L;

	private String name;
	private String code;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<>();

	private boolean disabled = false;
	private boolean locked = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public boolean isDisabled() {
		return disabled;
	}

	@Override
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@Override
	public void markDisabled() {
		this.disabled = true;
	}

	@Override
	public boolean isLocked() {
		return locked;
	}

	@Override
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
	public void markLocked() {
		this.locked = true;
	}
}
