package me.xiangxik.manage.module.system.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import me.xiangxik.data.jpa.HierarchicalEntityRepository;
import me.xiangxik.manage.module.system.entity.Department;
import me.xiangxik.manage.module.system.entity.QDepartment;

public interface DepartmentRepository
		extends HierarchicalEntityRepository<Department, Long>, QuerydslBinderCustomizer<QDepartment> {

	@Override
	default void customize(QuerydslBindings bindings, QDepartment root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
	}
}
