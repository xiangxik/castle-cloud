package me.xiangxik.scheduler.module.service;

import org.springframework.stereotype.Service;

import me.xiangxik.data.jpa.EntityService;
import me.xiangxik.scheduler.module.entity.TriggerEntity;
import me.xiangxik.scheduler.module.entity.TriggerPrimaryKey;

@Service
public class TriggerService extends EntityService<TriggerEntity, TriggerPrimaryKey> {

}
