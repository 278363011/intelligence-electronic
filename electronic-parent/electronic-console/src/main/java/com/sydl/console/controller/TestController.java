package com.sydl.console.controller;

import com.sydl.console.dto.UserRoleDto;
import com.sydl.console.mapper.SysUserMapper;
import com.sydl.console.mapper.SysUserRoleMapper;
import com.sydl.console.model.SysResource;
import com.sydl.console.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
public class TestController {

@Autowired
SysUserMapper sysUserMapper;

    @RequestMapping("/hello")
    public Result<List<SysResource>> hello(){
//        UserRoleDto admin = sysUserMapper.getUserRoleByUsername("admin");
        List<SysResource> admin = sysUserMapper.getResourceByRoleName("admin");
        log.error("我是错误信息");
        log.info("我是正常信息");
        return Result.success(admin);
    }




}
