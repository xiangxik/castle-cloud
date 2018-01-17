package me.xiangxik.manage.module.system.repo;

import me.xiangxik.data.jpa.HierarchicalEntityRepository;
import me.xiangxik.manage.module.system.entity.MenuItem;

public interface MenuItemRepository extends HierarchicalEntityRepository<MenuItem, Long> {

}
