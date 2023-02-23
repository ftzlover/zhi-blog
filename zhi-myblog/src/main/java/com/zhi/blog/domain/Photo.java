package com.zhi.blog.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.zhi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 照片管理对象 blog_photo
 *
 * @author ftz
 * @date 2023-02-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_photo")
public class Photo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 相册id
     */
    private Long albumId;
    /**
     * 照片名
     */
    private String photoName;
    /**
     * 照片描述
     */
    private String photoDesc;
    /**
     * 照片地址
     */
    private String photoSrc;
    /**
     * 是否删除
     */
    private Integer isDelete;

}
