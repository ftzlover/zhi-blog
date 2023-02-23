package com.zhi.blog.controller;

import java.util.List;
import java.util.Arrays;

import cn.dev33.satoken.annotation.SaIgnore;
import com.zhi.blog.dto.PhotoAlbumDTO;
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
import com.zhi.blog.domain.vo.AlbumVo;
import com.zhi.blog.domain.bo.AlbumBo;
import com.zhi.blog.service.IAlbumService;
import com.zhi.common.core.page.TableDataInfo;

/**
 * 相册管理
 *
 * @author ftz
 * @date 2023-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/album/album")
public class AlbumController extends BaseController {

    private final IAlbumService iAlbumService;


    /**
     * 前台获取相册列表
     *
     * @return {@link R<PhotoAlbumDTO>} 相册列表
     */
    @SaIgnore
    @ApiOperation(value = "获取相册列表")
    @GetMapping("/photos/albums")
    public R<List<PhotoAlbumDTO>> listPhotoAlbums() {
        return R.ok(iAlbumService.listPhotoAlbums());
    }



    /**
     * 查询相册管理列表
     */
    @SaCheckPermission("album:album:list")
    @GetMapping("/list")
    public TableDataInfo<AlbumVo> list(AlbumBo bo, PageQuery pageQuery) {
        return iAlbumService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出相册管理列表
     */
    @SaCheckPermission("album:album:export")
    @Log(title = "相册管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AlbumBo bo, HttpServletResponse response) {
        List<AlbumVo> list = iAlbumService.queryList(bo);
        ExcelUtil.exportExcel(list, "相册管理", AlbumVo.class, response);
    }

    /**
     * 获取相册管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("album:album:query")
    @GetMapping("/{id}")
    public R<AlbumVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iAlbumService.queryById(id));
    }

    /**
     * 新增相册管理
     */
    @SaCheckPermission("album:album:add")
    @Log(title = "相册管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AlbumBo bo) {
        return toAjax(iAlbumService.insertByBo(bo));
    }

    /**
     * 修改相册管理
     */
    @SaCheckPermission("album:album:edit")
    @Log(title = "相册管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AlbumBo bo) {
        return toAjax(iAlbumService.updateByBo(bo));
    }

    /**
     * 删除相册管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("album:album:remove")
    @Log(title = "相册管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iAlbumService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
