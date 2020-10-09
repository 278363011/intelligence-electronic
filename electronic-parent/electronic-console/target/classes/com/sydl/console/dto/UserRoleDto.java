package com.sydl.console.dto;

import com.sydl.console.model.SysRole;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleDto {
    private Integer id;
    private String userName;
    private String password;
    private List<SysRole> roleList;

}
