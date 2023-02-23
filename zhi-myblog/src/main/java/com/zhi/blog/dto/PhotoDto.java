package com.zhi.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ftz-lover
 * @version 1.0
 * @date 2023/2/21 21:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "照片")
public class PhotoDto {

    /**
     * 相册id
     */
    @NotNull(message = "相册id不能为空")
    @ApiModelProperty(name = "id", value = "相册id", required = true, dataType = "Integer")
    private Long albumid;

    /**
     * 照片url列表
     */
    @ApiModelProperty(name = "photoUrlList", value = "照片列表", required = true, dataType = "List<String>")
    private List<String> photoUrlList;


    /**
     * 照片名称列表
     */
    @ApiModelProperty(name = "photoIdList", value = "照片id列表", required = true, dataType = "List<String>")
    private List<String> photoNameList;



}
