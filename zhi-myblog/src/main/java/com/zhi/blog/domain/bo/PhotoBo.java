package com.zhi.blog.domain.bo;

import com.zhi.common.core.domain.BaseEntity;
import com.zhi.common.core.validate.AddGroup;
import com.zhi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;


/**
 * 照片管理业务对象 blog_photo
 *
 * @author ftz
 * @date 2023-02-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PhotoBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 相册id
     */
    @NotNull(message = "相册id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long albumId;

    /**
     * 照片名
     */
    @NotBlank(message = "照片名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String photoName;

    /**
     * 照片描述
     */
    @NotBlank(message = "照片描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String photoDesc;

    /**
     * 照片地址
     */
    @NotBlank(message = "照片地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String photoSrc;

    /**
     * 是否删除
     */
    @NotNull(message = "是否删除不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer isDelete;


}
