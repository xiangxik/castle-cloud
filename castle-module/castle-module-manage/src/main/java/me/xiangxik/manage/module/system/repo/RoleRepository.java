package me.xiangxik.manage.module.system.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import me.xiangxik.data.jpa.EntityRepository;
import me.xiangxik.manage.module.system.entity.QRole;
import me.xiangxik.manage.module.system.entity.Role;

public interface RoleRepository extends EntityRepository<Role, Long>, QuerydslBinderCustomizer<QRole> {

	@Override
	default void customize(QuerydslBindings bindings, QRole root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
	}
}
