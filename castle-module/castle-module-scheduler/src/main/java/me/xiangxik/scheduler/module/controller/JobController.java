package me.xiangxik.scheduler.module.controller;

import java.util.Set;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import me.xiangxik.scheduler.module.entity.JobType;
import me.xiangxik.scheduler.module.model.JobModel;
import me.xiangxik.webapp.BaseController;

@Controller
@RequestMapping("/job")
public class JobController extends BaseController {

	@Autowired
	private Scheduler scheduler;

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<JobModel> doPage(Pageable pageable) throws SchedulerException {
		Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
		return new PageImpl<>(Lists.newArrayList(jobKeys), pageable, jobKeys.size()).map(jobKey -> {
			JobModel jobModel = new JobModel();
			try {
				JobDetail jobDetail = scheduler.getJobDetail(jobKey);
				jobModel.setJobName(jobDetail.getKey().getName());
				jobModel.setJobGroup(jobDetail.getKey().getGroup());
				jobModel.setJobClass(jobDetail.getJobClass());
				jobModel.setDescription(jobDetail.getDescription());
				jobModel.setDurable(jobDetail.isDurable());
				jobModel.setRequestsRecovery(jobDetail.requestsRecovery());
				// jobModel.setData(jobDetail.getJobDataMap());
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
			return jobModel;
		});
	}

	@RequestMapping(value = { "", "/", "/list" }, method = RequestMethod.GET)
	public String show(Model model) {
		model.addAttribute("jobTypes", JobType.values());
		return "/job/list";
	}

	@RequestMapping(value = { "/add", "/edit" }, method = RequestMethod.GET)
	public String add(Model model) {
		JobModel jobModel = new JobModel();
		JobType jobType = getParameter("type", JobType.class, JobType.no_op);
		jobModel.setJobClass(jobType.getJobClass());
		return edit(jobModel, model);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(JobModel entity, Model model) {
		model.addAttribute("jobType", jobClassToType(entity.getJobClass()));
		model.addAttribute("entity", entity);
		return "/job/edit";
	}

	private JobType jobClassToType(Class<? extends Job> jobClass) {
		for (JobType jobType : JobType.values()) {
			if (jobType.getJobClass().isAssignableFrom(jobClass)) {
				return jobType;
			}
		}

		return null;
	}
}
