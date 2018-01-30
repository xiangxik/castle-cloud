package me.xiangxik.scheduler.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import com.google.common.base.Joiner;

@Embeddable
public class TriggerPrimaryKey implements Serializable {

	private static final long serialVersionUID = 8489386302822354294L;

	@Size(max = 120)
	@Column(name = "SCHED_NAME", insertable = false, updatable = false)
	private String scheduler;

	@Size(max = 190)
	@Column(name = "TRIGGER_NAME", insertable = false, updatable = false)
	private String name;

	@Size(max = 190)
	@Column(name = "TRIGGER_GROUP", insertable = false, updatable = false)
	private String group;

	public String getScheduler() {
		return scheduler;
	}

	public void setScheduler(String scheduler) {
		this.scheduler = scheduler;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return Joiner.on(":").join(scheduler, name, group);
	}
}
