package me.xiangxik.manage.module.system.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import me.xiangxik.data.domain.Disabledable;
import me.xiangxik.data.domain.Lockedable;
import me.xiangxik.data.domain.LogicDeleteable;
import me.xiangxik.data.domain.Userable;
import me.xiangxik.data.jpa.DataEntity;

@Entity
@Table(name = "tbl_user")
public class User extends DataEntity<User, Long> implements Lockedable, Disabledable, LogicDeleteable, Userable {

	private static final long serialVersionUID = -3910248020640674714L;

	@NotNull
	@Size(min = 4, max = 20)
	@Pattern(regexp = "^$|^[\\u4E00-\\u9FA5\\uf900-\\ufa2d_a-zA-Z][\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{4,16}$")
	@Column(unique = true, length = 100)
	private String username;

	@Column(length = 225)
	private String password;

	@NotNull
	@Size(min = 2, max = 20)
	@Column(length = 100)
	private String name;

	private Integer age;

	@Pattern(regexp = "^$|\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
	private String mail;

	@Pattern(regexp = "^$|^[1][0-9]{10}$")
	private String mobile;

	private boolean accountNonExpired = true;
	private boolean credentialsNonExpired = true;

	private boolean disabled = false;
	private boolean locked = false;
	private boolean deleted = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_user_to_role")
	private Set<Role> roles = new HashSet<>();

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public void markDeleted() {
		this.deleted = true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
	@Override
	public String getUsername() {
		return username;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return !disabled;
	}
}
