package com.zhi.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ftz-lover
 * @version 1.0
 * @date 2023/2/22 22:00
 * 前台查看查册内图片的封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrontPhotoDto {



    /**
     * 相册封面
     */
    private String photoAlbumCover;

    /**
     * 相册名
     */
    private String photoAlbumName;

    /**
     * 照片列表
     */
    private List<String> photoList;
}
