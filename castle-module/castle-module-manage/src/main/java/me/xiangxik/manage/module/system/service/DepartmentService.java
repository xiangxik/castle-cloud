package me.xiangxik.manage.module.system.service;

import org.springframework.stereotype.Service;

import me.xiangxik.data.jpa.HierarchicalEntityService;
import me.xiangxik.manage.module.system.entity.Department;

@Service
public class DepartmentService extends HierarchicalEntityService<Department, Long> {

}
