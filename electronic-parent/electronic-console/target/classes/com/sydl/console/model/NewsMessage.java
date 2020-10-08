package com.sydl.console.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author codebaobao
 * @since 2020-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NewsMessage extends Model<NewsMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer frameCateId;

    /**
     * 消息类型 1:置顶 0:最新
     */
    private Integer newsType;

    /**
     * 消息分类 链路、缓存等
     */
    private Integer newsCategory;

    /**
     * 消息标题
     */
    private String newsTitle;

    /**
     * 消息内容
     */
    private String newsContent;

    /**
     * 状态
     */
    @TableField("newsStatus")
    private Integer newsStatus;

    /**
     * 创建时间
     */
    private LocalDateTime newsCreatetime;

    /**
     * 修改时间
     */
    private LocalDateTime newsUpdatetime;

    /**
     * 创建者
     */
    private Integer createUsr;

    /**
     * 逻辑删除
     */
    @TableField("isDel")
    private Integer isDel;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
