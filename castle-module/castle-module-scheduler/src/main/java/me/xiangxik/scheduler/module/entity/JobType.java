package me.xiangxik.scheduler.module.entity;

import org.quartz.Job;
import org.quartz.jobs.DirectoryScanJob;
import org.quartz.jobs.FileScanJob;
import org.quartz.jobs.NativeJob;
import org.quartz.jobs.NoOpJob;
import org.quartz.jobs.ee.jms.SendDestinationMessageJob;
import org.quartz.jobs.ee.mail.SendMailJob;

public enum JobType {

	no_op("无操作", NoOpJob.class, true), send_mail("发送邮件", SendMailJob.class), navite("本地执行", NativeJob.class), file_scan(
			"文件扫描", FileScanJob.class, true), directory_scan("文件夹扫描", DirectoryScanJob.class,
					true), send_destination("发送目标消息", SendDestinationMessageJob.class, true), send_queue("发送目标消息",
							SendDestinationMessageJob.class,
							true), send_topic("发送目标消息", SendDestinationMessageJob.class, true);
	private String label;
	private Class<? extends Job> jobClass;
	private boolean hidden;

	private JobType(String label, Class<? extends Job> jobClass, boolean hidden) {
		this.label = label;
		this.jobClass = jobClass;
		this.hidden = hidden;
	}

	private JobType(String label, Class<? extends Job> jobClassName) {
		this.label = label;
		this.jobClass = jobClassName;
	}

	public String getLabel() {
		return label;
	}

	public Class<? extends Job> getJobClass() {
		return jobClass;
	}

	public boolean isHidden() {
		return hidden;
	}

}
