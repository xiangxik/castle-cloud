package me.xiangxik.scheduler.module.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.querydsl.core.types.Predicate;

import me.xiangxik.scheduler.module.entity.JobEntity;
import me.xiangxik.scheduler.module.entity.JobPrimaryKey;
import me.xiangxik.webapp.EntityController;

@Controller
@RequestMapping("/job")
public class JobController extends EntityController<JobEntity, JobPrimaryKey> {

	@Override
	public Page<JobEntity> doPage(Predicate predicate, Pageable pageable) {
		return super.doInternalPage(predicate, pageable);
	}

}
