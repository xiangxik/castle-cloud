package me.xiangxik.manage.module.system.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import me.xiangxik.data.jpa.EntityRepository;
import me.xiangxik.manage.module.system.entity.QUser;
import me.xiangxik.manage.module.system.entity.User;

public interface UserRepository extends EntityRepository<User, Long>, QuerydslBinderCustomizer<QUser> {

	User findByUsername(String username);

	@Override
	default void customize(QuerydslBindings bindings, QUser root) {
		bindings.bind(root.username, root.name).first((path, value) -> path.contains(value));
		bindings.excluding(root.password);
	}
}
