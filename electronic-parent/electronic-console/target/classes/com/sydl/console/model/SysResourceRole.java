package com.sydl.console.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author codebaobao
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysResourceRole extends Model<SysResourceRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer resId;

    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
