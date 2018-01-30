package me.xiangxik.scheduler.module.service;

import org.springframework.stereotype.Service;

import me.xiangxik.data.jpa.EntityService;
import me.xiangxik.scheduler.module.entity.JobEntity;
import me.xiangxik.scheduler.module.entity.JobPrimaryKey;

@Service
public class JobService extends EntityService<JobEntity, JobPrimaryKey> {

}
