package me.xiangxik.scheduler.module.repo;

import me.xiangxik.data.jpa.EntityRepository;
import me.xiangxik.scheduler.module.entity.JobEntity;
import me.xiangxik.scheduler.module.entity.JobPrimaryKey;

public interface JobRepository extends EntityRepository<JobEntity, JobPrimaryKey> {

}
