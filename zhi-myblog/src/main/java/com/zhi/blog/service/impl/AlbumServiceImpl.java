package com.zhi.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zhi.blog.dto.PhotoAlbumDTO;
import com.zhi.common.core.page.TableDataInfo;
import com.zhi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhi.common.utils.BeanCopyUtils;
import com.zhi.common.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.zhi.blog.domain.bo.AlbumBo;
import com.zhi.blog.domain.vo.AlbumVo;
import com.zhi.blog.domain.Album;
import com.zhi.blog.mapper.AlbumMapper;
import com.zhi.blog.service.IAlbumService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

import static com.zhi.common.constant.blog.CommonConst.FALSE;
import static com.zhi.common.enums.blog.ArticleStatusEnum.PUBLIC;

/**
 * 相册管理Service业务层处理
 *
 * @author ftz
 * @date 2023-02-20
 */
@RequiredArgsConstructor
@Service
public class AlbumServiceImpl implements IAlbumService {

    private final AlbumMapper baseMapper;


    @Override
    public List<PhotoAlbumDTO> listPhotoAlbums() {
        // 查询相册列表
        List<Album> photoAlbumList = baseMapper.selectList(new LambdaQueryWrapper<Album>()
            .eq(Album::getStatus, PUBLIC.getStatus())
            .eq(Album::getIsDelete, FALSE)
            .orderByDesc(Album::getId));
        return BeanCopyUtils.copyList(photoAlbumList, PhotoAlbumDTO.class);
    }


    /**
     * 查询相册管理
     */
    @Override
    public AlbumVo queryById(Long id){
        AlbumVo albumVo = baseMapper.selectVoById(id);
        albumVo.setPhotoCount(baseMapper.PhotoCount(id));
        return albumVo ;
    }

    /**
     * 查询相册管理列表
     */
    @Override
    public TableDataInfo<AlbumVo> queryPageList(AlbumBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Album> lqw = buildQueryWrapper(bo);
        Page<AlbumVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询相册管理列表
     */
    @Override
    public List<AlbumVo> queryList(AlbumBo bo) {
        LambdaQueryWrapper<Album> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Album> buildQueryWrapper(AlbumBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Album> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getAlbumName()), Album::getAlbumName, bo.getAlbumName());
        lqw.eq(StringUtils.isNotBlank(bo.getAlbumDesc()), Album::getAlbumDesc, bo.getAlbumDesc());
        lqw.eq(StringUtils.isNotBlank(bo.getAlbumCover()), Album::getAlbumCover, bo.getAlbumCover());
        lqw.eq(bo.getIsDelete() != null, Album::getIsDelete, bo.getIsDelete());
        lqw.eq(bo.getStatus() != null, Album::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增相册管理
     */
    @Override
    public Boolean insertByBo(AlbumBo bo) {
        Album add = BeanUtil.toBean(bo, Album.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改相册管理
     */
    @Override
    public Boolean updateByBo(AlbumBo bo) {
        Album update = BeanUtil.toBean(bo, Album.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Album entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除相册管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
