package com.zhi.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zhi.common.annotation.ExcelDictFormat;
import com.zhi.common.convert.ExcelDictConvert;
import lombok.Data;


/**
 * 相册管理视图对象 blog_photo_album
 *
 * @author ftz
 * @date 2023-02-20
 */
@Data
@ExcelIgnoreUnannotated
public class AlbumVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 相册名
     */
    @ExcelProperty(value = "相册名")
    private String albumName;

    /**
     * 相册描述
     */
    @ExcelProperty(value = "相册描述")
    private String albumDesc;

    /**
     * 相册封面
     */
    @ExcelProperty(value = "相册封面")
    private String albumCover;

    /**
     * 相册内照片数量
     */
    @ExcelProperty(value = "相册照片量")
    private Integer photoCount;

    /**
     * 是否删除
     */
    @ExcelProperty(value = "是否删除", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private Integer isDelete;

    /**
     * 状态值 1公开 2私密
     */
    @ExcelProperty(value = "状态值 1公开 2私密")
    private Integer status;


}
