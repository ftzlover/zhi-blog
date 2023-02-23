package com.zhi.blog.controller;

import java.util.List;
import java.util.Arrays;

import cn.dev33.satoken.annotation.SaIgnore;
import com.zhi.blog.dto.FrontPhotoDto;
import com.zhi.blog.dto.PhotoDto;
import com.zhi.blog.dto.UpdateAlbumDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.zhi.common.annotation.RepeatSubmit;
import com.zhi.common.annotation.Log;
import com.zhi.common.core.controller.BaseController;
import com.zhi.common.core.domain.PageQuery;
import com.zhi.common.core.domain.R;
import com.zhi.common.core.validate.AddGroup;
import com.zhi.common.core.validate.EditGroup;
import com.zhi.common.enums.BusinessType;
import com.zhi.common.utils.poi.ExcelUtil;
import com.zhi.blog.domain.vo.PhotoVo;
import com.zhi.blog.domain.bo.PhotoBo;
import com.zhi.blog.service.IPhotoService;
import com.zhi.common.core.page.TableDataInfo;

/**
 * 照片管理
 *
 * @author ftz
 * @date 2023-02-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/photo/photo")
public class PhotoController extends BaseController {

    private final IPhotoService iPhotoService;


    /**
     * 前台根据相册id查看照片列表
     *
     * @param albumId 相册id
     * @return {@link R<FrontPhotoDto>} 照片列表
     */
    @SaIgnore
    @ApiOperation(value = "根据相册id查看照片列表")
    @GetMapping("/albums/{albumId}/photos")
    public R<FrontPhotoDto> listPhotosByAlbumId(@PathVariable("albumId") Long albumId, PageQuery pageQuery) {
        return R.ok(iPhotoService.listPhotosByAlbumId(albumId, pageQuery));
    }

    /**
     * 查询照片管理列表
     */
    @SaCheckPermission("photo:photo:list")
    @GetMapping("/list")
    public TableDataInfo<PhotoVo> list(PhotoBo bo, PageQuery pageQuery) {
        return iPhotoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出照片管理列表
     */
    @SaCheckPermission("photo:photo:export")
    @Log(title = "照片管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PhotoBo bo, HttpServletResponse response) {
        List<PhotoVo> list = iPhotoService.queryList(bo);
        ExcelUtil.exportExcel(list, "照片管理", PhotoVo.class, response);
    }

    /**
     * 获取照片管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("photo:photo:query")
    @GetMapping("/{id}")
    public R<PhotoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iPhotoService.queryById(id));
    }

    /**
     * 新增照片管理
     */
    @SaCheckPermission("photo:photo:add")
    @Log(title = "照片管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PhotoDto bo) {
        return toAjax(iPhotoService.insertByBo(bo));
    }

    /**
     * 修改照片管理
     */
    @SaCheckPermission("photo:photo:edit")
    @Log(title = "照片管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PhotoBo bo) {
        return toAjax(iPhotoService.updateByBo(bo));
    }

    /**
     * 修改照片所属相册·
     */
    @SaCheckPermission("photo:photo:edit")
    @Log(title = "照片管理", businessType = BusinessType.UPDATE)
    @PutMapping("/album")
    public R<Void> editPhotoAlbum(@RequestBody UpdateAlbumDto updateAlbumDto) {
        return toAjax(iPhotoService.updateByBo(updateAlbumDto));
    }

    /**
     * 删除照片管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("photo:photo:remove")
    @Log(title = "照片管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iPhotoService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
