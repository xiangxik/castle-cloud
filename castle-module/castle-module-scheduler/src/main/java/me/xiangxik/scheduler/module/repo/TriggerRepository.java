package me.xiangxik.scheduler.module.repo;

import me.xiangxik.data.jpa.EntityRepository;
import me.xiangxik.scheduler.module.entity.TriggerEntity;
import me.xiangxik.scheduler.module.entity.TriggerPrimaryKey;

public interface TriggerRepository extends EntityRepository<TriggerEntity, TriggerPrimaryKey> {

}
