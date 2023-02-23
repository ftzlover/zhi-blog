package com.zhi.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zhi.blog.domain.Article;
import com.zhi.blog.domain.Photo;
import com.zhi.blog.domain.vo.PhotoVo;
import com.zhi.blog.dto.UpdateAlbumDto;
import com.zhi.common.annotation.DataColumn;
import com.zhi.common.annotation.DataPermission;
import com.zhi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 照片管理Mapper接口
 *
 * @author ftz
 * @date 2023-02-21
 */
public interface PhotoMapper extends BaseMapperPlus<PhotoMapper, Photo, PhotoVo> {

    @Override
    <P extends IPage<Photo>> P selectPage(P page, @Param(Constants.WRAPPER) Wrapper<Photo> queryWrapper);



    /**
     * 修改照片所属相册
     */
    @Update("update blog_photo bp set  bp.album_id = #{newAlbumId} where bp.album_id = #{albumId}")
    Integer updatePhotoAlbum(UpdateAlbumDto updateAlbumDto);


    /**
     * 查询照片url列表
     */
    @Select("select blog_photo.photo_src from blog_photo where album_id = #{albumId}  LIMIT #{pageNum} , #{pageSize}")
    List<String> PhotoUrl(@Param("albumId") Long albumId,@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);


    /**
     * 根据相册id查询照片id集合
     */
    @Select("select id from blog_photo bp where bp.album_id = #{id}")
    List<Long> PhotoIdList(Long id);


}
