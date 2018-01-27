package me.xiangxik.scheduler.support;

import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class QuartzConfig {

	@Value("${quartz.auto_startup}")
	private Boolean autoStartup;

	@Autowired
	private ObjectFactory<DataSource> dataSource;

	@Autowired
	private ObjectFactory<PlatformTransactionManager> transactionManager;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setSchedulerName("DEFAULT");
		schedulerFactoryBean.setDataSource(dataSource.getObject());
		schedulerFactoryBean.setTransactionManager(transactionManager.getObject());
		schedulerFactoryBean.setAutoStartup(autoStartup);
		schedulerFactoryBean.setJobFactory(jobFactory());

		Properties quartzProperties = new Properties();
		quartzProperties.setProperty("org.quartz.plugin.jobHistory.class",
				"org.quartz.plugins.history.LoggingJobHistoryPlugin");
		quartzProperties.setProperty("org.quartz.plugin.triggerHistory.class",
				"org.quartz.plugins.history.LoggingTriggerHistoryPlugin");
		schedulerFactoryBean.setQuartzProperties(quartzProperties);
		return schedulerFactoryBean;
	}

	@Bean
	public JobFactory jobFactory() {
		return new SpringJobFactory();
	}
}
