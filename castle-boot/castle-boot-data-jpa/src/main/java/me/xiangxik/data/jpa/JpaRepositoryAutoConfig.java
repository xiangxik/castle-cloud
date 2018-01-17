package me.xiangxik.data.jpa;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = { "me.xiangxik" }, includeFilters = {
		@Filter(value = EntityRepository.class, type = FilterType.ASSIGNABLE_TYPE) }, repositoryImplementationPostfix = "Impl", repositoryFactoryBeanClass = EntityRepositoryFactoryBean.class)
@EnableJpaAuditing
public class JpaRepositoryAutoConfig {

}
