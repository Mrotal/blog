package com.jiem.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiem.blog.entity.User;
import com.jiem.blog.service.IUserService;
import com.jiem.blog.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping(value = "login")
    public Result<User> login(@RequestBody User user){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername())
                .eq("password", user.getPassword());
        User user1 = iUserService.getOne(queryWrapper);
        if (user1 != null) {
            Map map = new HashMap<>();
            map.put("avatar", user1.getAvatar());
            map.put("introduction", user1.getIntroduction());
            map.put("username", user1.getUsername());
            return Result.success(map);
        }
        else {
            return Result.fail("未查询到用户信息");
        }
    }


    @GetMapping("info")
    public Result<User> info(){

        Map map = new HashMap<>();
        List<String> roleList = new ArrayList<>();
        roleList.add("admin");
        map.put("roles", roleList);
        map.put("introduction", "I am a super administrator");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "Super Admin");

        return Result.success(map);
    }


    @PostMapping("logout")
    public Result<User> logout(){

        Map map = new HashMap<>();
        map.put("roles", "['admin']");
        map.put("introduction", "I am a super administrator");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "Super Admin");

        return Result.success(map);
    }
}
