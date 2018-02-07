package me.xiangxik.scheduler.module.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.quartz.JobDataMap;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "QRTZ_JOB_DETAILS")
public class JobEntity implements Persistable<JobPrimaryKey> {

	private static final long serialVersionUID = -4601792937530510015L;

	@Id
	@GeneratedValue
	private JobPrimaryKey id;

	@Size(max = 250)
	@Column(name = "DESCRIPTION")
	private String description;

	@Size(max = 250)
	@Column(name = "JOB_CLASS_NAME")
	private String jobClassName;

	@Column(name = "IS_DURABLE")
	private Boolean durable;

	@Column(name = "IS_NONCONCURRENT")
	private Boolean nonConcurrent;

	@Column(name = "IS_UPDATE_DATA")
	private Boolean updateData;

	@Column(name = "REQUESTS_RECOVERY")
	private Boolean requestsRecovery;

	@Column(name = "JOB_DATA")
	@Convert(converter = me.xiangxik.scheduler.support.SerializeJobDataConverter.class)
	private JobDataMap data;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public Boolean getDurable() {
		return durable;
	}

	public void setDurable(Boolean durable) {
		this.durable = durable;
	}

	public Boolean getNonConcurrent() {
		return nonConcurrent;
	}

	public void setNonConcurrent(Boolean nonConcurrent) {
		this.nonConcurrent = nonConcurrent;
	}

	public Boolean getUpdateData() {
		return updateData;
	}

	public void setUpdateData(Boolean updateData) {
		this.updateData = updateData;
	}

	public Boolean getRequestsRecovery() {
		return requestsRecovery;
	}

	public void setRequestsRecovery(Boolean requestsRecovery) {
		this.requestsRecovery = requestsRecovery;
	}

	public JobDataMap getData() {
		return data;
	}

	public void setData(JobDataMap data) {
		this.data = data;
	}

	public void setId(JobPrimaryKey id) {
		this.id = id;
	}

	@Override
	public JobPrimaryKey getId() {
		return id;
	}

	@Transient
	@Override
	public boolean isNew() {
		return null == getId();
	}

}
