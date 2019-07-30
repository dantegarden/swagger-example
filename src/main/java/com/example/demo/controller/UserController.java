package com.example.demo.controller;

import com.example.demo.bean.Result;
import com.example.demo.model.User;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private static List<User> userList = Lists.newArrayList();
    private static Long nextId = 0L;
    private static Random random = new Random();

    static {
        for(int i=1; i<=10; i++){
            nextId = Long.valueOf(i);
            User user = new User();
            user.setId(nextId).setNickname("user"+nextId).setStatus(random.nextInt(2)).setRegisterDate(new Date());
            user.setUsername(user.getNickname());
            userList.add(user);
        }

    }

    @GetMapping("/list")
    public Result<List<User>> list(){
        return Result.ok(userList);
    }

    @GetMapping("/user")
    public Result<User> user(@RequestParam Long userId){
        List<User> collect = userList.stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(collect)){
            User user = collect.get(0);
            return Result.ok(user);
        }
        return Result.fail("用户不存在");
    }

    @PostMapping("/add")
    public Result<Long> add(@RequestBody User formUser){
        nextId += 1;
        User user = new User();
        BeanUtils.copyProperties(formUser, user);
        user.setId(nextId);
        userList.add(user);
        return Result.ok(nextId);
    }

    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody User formUser){
        List<User> collect = userList.stream().filter(user -> user.getId().equals(formUser.getId())).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(collect)){
            User user = collect.get(0);
            userList.remove(user);
            BeanUtils.copyProperties(formUser, user);
            userList.add(user);
            return Result.ok(Boolean.TRUE);
        }
        return Result.fail("用户不存在");
    }

    @DeleteMapping("/remove")
    public Result<Boolean> update(@RequestParam Long userId){
        List<User> collect = userList.stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(collect)){
            User user = collect.get(0);
            userList.remove(user);
            return Result.ok(Boolean.TRUE);
        }
        return Result.fail("用户不存在");
    }
}
