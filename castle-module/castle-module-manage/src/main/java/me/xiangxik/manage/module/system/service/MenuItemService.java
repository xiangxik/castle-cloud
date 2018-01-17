package me.xiangxik.manage.module.system.service;

import org.springframework.stereotype.Service;

import me.xiangxik.data.jpa.HierarchicalEntityService;
import me.xiangxik.manage.module.system.entity.MenuItem;

@Service
public class MenuItemService extends HierarchicalEntityService<MenuItem, Long> {

}
