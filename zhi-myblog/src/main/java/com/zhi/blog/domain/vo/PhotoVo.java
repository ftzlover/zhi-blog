package com.zhi.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zhi.common.annotation.ExcelDictFormat;
import com.zhi.common.convert.ExcelDictConvert;
import lombok.Data;


/**
 * 照片管理视图对象 blog_photo
 *
 * @author ftz
 * @date 2023-02-21
 */
@Data
@ExcelIgnoreUnannotated
public class PhotoVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 相册id
     */
    @ExcelProperty(value = "相册id")
    private Long albumId;

    /**
     * 照片名
     */
    @ExcelProperty(value = "照片名")
    private String photoName;

    /**
     * 照片描述
     */
    @ExcelProperty(value = "照片描述")
    private String photoDesc;

    /**
     * 照片地址
     */
    @ExcelProperty(value = "照片地址")
    private String photoSrc;

    /**
     * 是否删除
     */
    @ExcelProperty(value = "是否删除")
    private Integer isDelete;


}
