package com.iss.ua.lark.controller.material;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.iss.ua.lark.common.core.domain.AjaxResult;
import com.iss.ua.lark.common.core.page.TableDataInfo;
import com.iss.ua.lark.common.utils.poi.ExcelUtil;
import com.iss.ua.lark.system.controller.BaseController;
import com.iss.ua.lark.system.domain.material.SoMaterial;
import com.iss.ua.lark.system.service.ISoMaterialService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 物料Controller
 * 
 * @author times
 * @date 2023-06-08
 */
@RestController
@RequestMapping("/admin/material")
public class SoMaterialController extends BaseController
{
    @Autowired
    private ISoMaterialService soMaterialService;

    /**
     * 查询物料列表
     */
    @PreAuthorize("@ss.hasPermi('system:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(SoMaterial soMaterial)
    {
        startPage();
        List<SoMaterial> list = soMaterialService.selectSoMaterialList(soMaterial);
        return getDataTable(list);
    }

    /**
     * 导出物料列表
     */
    @PreAuthorize("@ss.hasPermi('system:material:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SoMaterial soMaterial)
    {
        List<SoMaterial> list = soMaterialService.selectSoMaterialList(soMaterial);
        ExcelUtil<SoMaterial> util = new ExcelUtil<SoMaterial>(SoMaterial.class);
        util.exportExcel(response, list, "物料数据");
    }

    /**
     * 获取物料详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:material:query')")
    @GetMapping(value = "/{mid}")
    public AjaxResult getInfo(@PathVariable("mid") Long mid)
    {
        return success(soMaterialService.selectSoMaterialByMid(mid));
    }

    /**
     * 新增物料
     */
    @PreAuthorize("@ss.hasPermi('system:material:add')")
    @PostMapping
    public AjaxResult add(@RequestBody SoMaterial soMaterial)
    {
        return toAjax(soMaterialService.insertSoMaterial(soMaterial));
    }

    /**
     * 修改物料
     */
    @PreAuthorize("@ss.hasPermi('system:material:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody SoMaterial soMaterial)
    {
        return toAjax(soMaterialService.updateSoMaterial(soMaterial));
    }

    /**
     * 删除物料
     */
    @PreAuthorize("@ss.hasPermi('system:material:remove')")
	@DeleteMapping("/{mids}")
    public AjaxResult remove(@PathVariable Long[] mids)
    {
        return toAjax(soMaterialService.deleteSoMaterialByMids(mids));
    }
}
