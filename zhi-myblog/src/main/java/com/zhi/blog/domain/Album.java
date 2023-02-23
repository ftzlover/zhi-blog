package com.zhi.blog.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.zhi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 相册管理对象 blog_photo_album
 *
 * @author ftz
 * @date 2023-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_photo_album")
public class Album extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 相册名
     */
    private String albumName;
    /**
     * 相册描述
     */
    private String albumDesc;
    /**
     * 相册封面
     */
    private String albumCover;
    /**
     * 是否删除
     */
    private Integer isDelete;
    /**
     * 状态值 1公开 2私密
     */
    private Integer status;

}
