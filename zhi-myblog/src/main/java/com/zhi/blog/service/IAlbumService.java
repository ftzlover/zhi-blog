package com.zhi.blog.service;

import com.zhi.blog.domain.Album;
import com.zhi.blog.domain.vo.AlbumVo;
import com.zhi.blog.domain.bo.AlbumBo;
import com.zhi.blog.dto.PhotoAlbumDTO;
import com.zhi.common.core.page.TableDataInfo;
import com.zhi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 相册管理Service接口
 *
 * @author ftz
 * @date 2023-02-20
 */
public interface IAlbumService {

    /**
     * 前台获取相册列表
     *
     * @return {@link List<PhotoAlbumDTO>}相册列表
     */
    List<PhotoAlbumDTO> listPhotoAlbums();


    /**
     * 查询相册管理
     */
    AlbumVo queryById(Long id);

    /**
     * 查询相册管理列表
     */
    TableDataInfo<AlbumVo> queryPageList(AlbumBo bo, PageQuery pageQuery);

    /**
     * 查询相册管理列表
     */
    List<AlbumVo> queryList(AlbumBo bo);

    /**
     * 新增相册管理
     */
    Boolean insertByBo(AlbumBo bo);

    /**
     * 修改相册管理
     */
    Boolean updateByBo(AlbumBo bo);

    /**
     * 校验并批量删除相册管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
