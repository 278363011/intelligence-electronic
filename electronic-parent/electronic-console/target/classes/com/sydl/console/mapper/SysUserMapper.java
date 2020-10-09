package com.sydl.console.mapper;

import com.sydl.console.dto.UserRoleDto;
import com.sydl.console.model.SysResource;
import com.sydl.console.model.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author codebaobao
 * @since 2020-10-09
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名查找所有角色
     * @param username
     * @return
     */
    UserRoleDto getUserRoleByUsername(String username);

    /**
     * 根据角色名查找所有权限
     * @param rolename
     * @return
     */
    List<SysResource> getResourceByRoleName(String rolename);

}
