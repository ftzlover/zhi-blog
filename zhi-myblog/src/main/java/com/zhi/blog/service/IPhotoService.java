package com.zhi.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhi.blog.domain.Photo;
import com.zhi.blog.domain.vo.PhotoVo;
import com.zhi.blog.domain.bo.PhotoBo;
import com.zhi.blog.dto.FrontPhotoDto;
import com.zhi.blog.dto.PhotoDto;
import com.zhi.blog.dto.UpdateAlbumDto;
import com.zhi.common.core.page.TableDataInfo;
import com.zhi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 照片管理Service接口
 *
 * @author ftz
 * @date 2023-02-21
 */
public interface IPhotoService{

    /**
     * 前台根据相册id查看照片列表
     *
     * @param albumId 相册id
     * @return {@link List<FrontPhotoDto>} 照片列表
     */
    FrontPhotoDto listPhotosByAlbumId(Long albumId, PageQuery pageQuery);

    /**
     * 查询照片管理
     */
    PhotoVo queryById(Long id);

    /**
     * 查询照片管理列表
     */
    TableDataInfo<PhotoVo> queryPageList(PhotoBo bo, PageQuery pageQuery);

    /**
     * 查询照片管理列表
     */
    List<PhotoVo> queryList(PhotoBo bo);

    /**
     * 新增照片管理
     */
    Boolean insertByBo(PhotoDto bo);

    /**
     * 修改照片管理
     */
    Boolean updateByBo(PhotoBo bo);

    /**
     * 修改照片相册
     */
    Boolean updateByBo(UpdateAlbumDto updateAlbumDto);

    /**
     * 校验并批量删除照片管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
