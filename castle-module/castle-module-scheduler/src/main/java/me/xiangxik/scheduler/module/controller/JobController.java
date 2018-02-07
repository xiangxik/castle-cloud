package me.xiangxik.scheduler.module.controller;

import java.util.Objects;
import java.util.Set;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Strings;
import com.querydsl.core.types.Predicate;

import me.xiangxik.scheduler.module.entity.JobEntity;
import me.xiangxik.scheduler.module.entity.JobPrimaryKey;
import me.xiangxik.scheduler.module.entity.JobType;
import me.xiangxik.webapp.EntityController;

@Controller
@RequestMapping("/job")
public class JobController extends EntityController<JobEntity, JobPrimaryKey> {

	@Autowired
	private Scheduler scheduler;
	
	@Override
	public Page<JobEntity> doPage(Predicate predicate, Pageable pageable) {
		
		try {
			Set<JobKey> jobKey = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
			System.out.println(jobKey);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.doInternalPage(predicate, pageable);
	}

	@Override
	protected void onShowListPage(Model model) {
		model.addAttribute("jobTypes", JobType.values());
	}

	@Override
	protected void onShowEditPage(JobEntity entity, Model model) {
		if (Strings.isNullOrEmpty(entity.getJobClassName())) {
			JobType jobType = getParameter("type", JobType.class, JobType.no_op);
			entity.setJobClassName(jobType.getJobClass().getName());
		}

		model.addAttribute("jobType", jobClassNameToType(entity.getJobClassName()));
	}

	private JobType jobClassNameToType(String jobClassName) {
		for (JobType jobType : JobType.values()) {
			if (Objects.equals(jobClassName, jobType.getJobClass().getName())) {
				return jobType;
			}
		}

		return null;
	}
}
