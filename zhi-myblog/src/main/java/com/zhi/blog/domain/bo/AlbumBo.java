package com.zhi.blog.domain.bo;

import com.zhi.common.core.domain.BaseEntity;
import com.zhi.common.core.validate.AddGroup;
import com.zhi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;


/**
 * 相册管理业务对象 blog_photo_album
 *
 * @author ftz
 * @date 2023-02-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AlbumBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 相册名
     */
    @NotBlank(message = "相册名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String albumName;

    /**
     * 相册描述
     */
    @NotBlank(message = "相册描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String albumDesc;

    /**
     * 相册封面
     */
    @NotBlank(message = "相册封面不能为空", groups = { AddGroup.class, EditGroup.class })
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
