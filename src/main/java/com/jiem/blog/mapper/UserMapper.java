package com.jiem.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiem.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Jiem
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-09-26 21:48:38
* @Entity com.jiem.blog.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
