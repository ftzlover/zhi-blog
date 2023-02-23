package com.zhi.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhi.blog.domain.Album;
import com.zhi.blog.dto.FrontPhotoDto;
import com.zhi.blog.dto.PhotoDto;
import com.zhi.blog.dto.UpdateAlbumDto;
import com.zhi.blog.mapper.AlbumMapper;
import com.zhi.common.core.page.TableDataInfo;
import com.zhi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhi.common.exception.base.BaseException;
import com.zhi.common.utils.StringUtils;
import com.zhi.common.utils.blog.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.zhi.blog.domain.bo.PhotoBo;
import com.zhi.blog.domain.vo.PhotoVo;
import com.zhi.blog.domain.Photo;
import com.zhi.blog.mapper.PhotoMapper;
import com.zhi.blog.service.IPhotoService;
import java.util.*;
import java.util.stream.Collectors;

import static com.zhi.common.constant.blog.CommonConst.FALSE;
import static com.zhi.common.enums.blog.ArticleStatusEnum.PUBLIC;

/**
 * 照片管理Service业务层处理
 *
 * @author ftz
 * @date 2023-02-21
 */
@RequiredArgsConstructor
@Service
public class PhotoServiceImpl implements IPhotoService {

    private final PhotoMapper baseMapper;


    private final AlbumMapper albumMapper;


    @Override
    public FrontPhotoDto listPhotosByAlbumId(Long albumId,PageQuery pageQuery) {
        // 查询相册信息
        Album photoAlbum = albumMapper.selectOne(new LambdaQueryWrapper<Album>()
            .eq(Album::getId, albumId)
            .eq(Album::getIsDelete, FALSE)
            .eq(Album::getStatus, PUBLIC.getStatus()));
        if (Objects.isNull(photoAlbum)) {
            throw new BaseException("相册不存在");
        }
        List<String> photolist = baseMapper.PhotoUrl(albumId, pageQuery.getPageNum(), pageQuery.getPageSize());
        FrontPhotoDto frontPhotoDto =  FrontPhotoDto.builder()
            .photoAlbumCover(photoAlbum.getAlbumCover())
            .photoAlbumName(photoAlbum.getAlbumName())
            .photoList(photolist)
            .build();
        return frontPhotoDto;

    }

    /**
     * 查询照片管理
     */
    @Override
    public PhotoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询照片管理列表
     */
    @Override
    public TableDataInfo<PhotoVo> queryPageList(PhotoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Photo> lqw = buildQueryWrapper(bo);
        Page<PhotoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询照片管理列表
     */
    @Override
    public List<PhotoVo> queryList(PhotoBo bo) {
        LambdaQueryWrapper<Photo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Photo> buildQueryWrapper(PhotoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Photo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAlbumId() != null, Photo::getAlbumId, bo.getAlbumId());
        lqw.like(StringUtils.isNotBlank(bo.getPhotoName()), Photo::getPhotoName, bo.getPhotoName());
        lqw.eq(StringUtils.isNotBlank(bo.getPhotoDesc()), Photo::getPhotoDesc, bo.getPhotoDesc());
        lqw.eq(StringUtils.isNotBlank(bo.getPhotoSrc()), Photo::getPhotoSrc, bo.getPhotoSrc());
        lqw.eq(bo.getIsDelete() != null, Photo::getIsDelete, bo.getIsDelete());
        return lqw;
    }

    /**
     * 新增照片管理
     */
    @Override
    public Boolean insertByBo(PhotoDto bo) {
        Boolean flag = true;
        for (int i =0;i<bo.getPhotoUrlList().size();i++){
            Photo photo = new Photo();
            photo.setAlbumId(bo.getAlbumid());
            photo.setPhotoSrc(bo.getPhotoUrlList().get(i));
            photo.setPhotoName(bo.getPhotoNameList().get(i));
            flag = baseMapper.insert(photo) > 0;
        }
        return flag;
    }

    /**
     * 修改照片管理
     */
    @Override
    public Boolean updateByBo(PhotoBo bo) {

        Photo update = BeanUtil.toBean(bo, Photo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }


    /**
     * 修改照片相册·
     */
    @Override
    public Boolean updateByBo(UpdateAlbumDto updateAlbumDto) {
        Boolean flag = true;
        for (Long id :updateAlbumDto.getIds()){
            Photo photo = new Photo();
            photo.setId(id);
            photo.setAlbumId(updateAlbumDto.getAlbumid());
            flag =  baseMapper.updateById(photo)>0;
        }
        return flag;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Photo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除照片管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
