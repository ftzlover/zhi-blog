package com.zhi.blog.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @author ftz-lover
 * @version 1.0
 * @date 2023/2/22 13:50
 */

@Data
public class UpdateAlbumDto {

    /**
     * 选中的相片id
     */
    private  Long[] ids;

    /**
     * 更新后的相册id
     */
    @NotNull(message = "相册id不能为空")
    @ApiModelProperty(name = "id", value = "相册id", required = true, dataType = "Integer")
    private Long albumid;




}
