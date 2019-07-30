package com.example.demo.controller;

import com.example.demo.bean.Result;
import com.example.demo.model.Address;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Api(tags = "地址相关的接口")
@RestController
@RequestMapping("/address")
public class AddressController {
    private static List<Address> addressList = Lists.newArrayList();
    private static Long nextId = 0L;
    private static Random random = new Random();

    static {
        for(int i=1; i<=10; i++){
            nextId = Long.valueOf(i);
            Address address = new Address();
            address.setId(nextId).setUserId(Long.valueOf(random.nextInt(10))).setLiveAddress("北京市丰台区第"+i+"大街");
            addressList.add(address);
        }
    }

    @ApiOperation("获取单个地址信息")
    @ApiImplicitParam(name="addressId", value="地址id", dataType = "long", example = "1")
    @GetMapping("/address")
    public Result<Address> user(@RequestParam Long addressId){
        List<Address> collect = addressList.stream().filter(add -> add.getId().equals(addressId)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(collect)){
            Address address = collect.get(0);
            return Result.ok(address);
        }
        return Result.fail("地址不存在");
    }

    @ApiOperation("添加新地址")
    @PostMapping("/add")
    public Result<Long> add(@RequestBody Address formAddress){
        nextId += 1;
        Address address = new Address();
        BeanUtils.copyProperties(formAddress, address);
        address.setId(nextId);
        addressList.add(address);
        return Result.ok(nextId);
    }
}
