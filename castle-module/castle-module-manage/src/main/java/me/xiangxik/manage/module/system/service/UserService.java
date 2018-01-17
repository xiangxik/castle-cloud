package me.xiangxik.manage.module.system.service;

import org.springframework.stereotype.Service;

import me.xiangxik.data.jpa.EntityService;
import me.xiangxik.manage.module.system.entity.User;

@Service
public class UserService extends EntityService<User, Long> {

}
