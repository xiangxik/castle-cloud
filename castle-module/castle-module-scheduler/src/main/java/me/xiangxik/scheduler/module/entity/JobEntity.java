package me.xiangxik.scheduler.module.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.quartz.JobDataMap;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "QRTZ_JOB_DETAILS")
public class JobEntity extends AbstractPersistable<JobPrimaryKey> {

	private static final long serialVersionUID = -4601792937530510015L;

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
	@Lob
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

}
