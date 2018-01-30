package me.xiangxik.scheduler.module.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.quartz.JobDataMap;
import org.quartz.Trigger.TriggerState;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "QRTZ_TRIGGERS")
public class TriggerEntity extends AbstractPersistable<TriggerPrimaryKey> {

	private static final long serialVersionUID = 8182620052146932797L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "SCHED_NAME", insertable = false, updatable = false),
			@JoinColumn(name = "JOB_NAME", insertable = false, updatable = false),
			@JoinColumn(name = "JOB_GROUP", insertable = false, updatable = false) })
	private JobEntity job;

	@Size(max = 250)
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NEXT_FIRE_TIME")
	private Date nextFireTime;

	@Column(name = "PREV_FIRE_TIME")
	private Date prevFireTime;

	@Column(name = "PRIORITY")
	private int priority;

	@Enumerated(EnumType.STRING)
	private TriggerState state;

	@Column(name = "START_TIME")
	private Date startTime;

	@Column(name = "END_TIME")
	private Date endTime;

	@Column(name = "MISFIRE_INSTR")
	private int misfireInstruction;

	@Column(name = "JOB_DATA")
	@Convert(converter = me.xiangxik.scheduler.support.SerializeJobDataConverter.class)
	private JobDataMap data;
}
