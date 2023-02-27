package com.jiem.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiem.blog.entity.User;
import com.jiem.blog.service.IUserService;
import com.jiem.blog.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Jiem
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-09-26 21:48:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
implements IUserService {

}
