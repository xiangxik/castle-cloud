package me.xiangxik.data.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import me.xiangxik.data.DomainLifecycle;

@NoRepositoryBean
public interface EntityRepository<T, I extends Serializable>
		extends JpaRepository<T, I>, QueryDslPredicateExecutor<T>, DomainLifecycle<T> {

}
