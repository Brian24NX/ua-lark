package com.iss.ua.lark.controller.material;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.iss.ua.lark.common.core.domain.AjaxResult;
import com.iss.ua.lark.common.core.domain.R;
import com.iss.ua.lark.common.core.domain.entity.SysDept;
import com.iss.ua.lark.common.core.page.TableDataInfo;
import com.iss.ua.lark.common.utils.poi.ExcelUtil;
import com.iss.ua.lark.system.controller.BaseController;
import com.iss.ua.lark.common.core.domain.entity.SoMaterialCategory;
import com.iss.ua.lark.system.domain.material.SoMaterialCategoryParam;
import com.iss.ua.lark.system.domain.material.SoMaterialCategoryReturn;
import com.iss.ua.lark.system.service.ISoMaterialCategoryService;
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
 * 物料类别Controller
 * 
 * @author times
 * @date 2023-06-08
 */
@RestController
@RequestMapping("/admin/category")
public class SoMaterialCategoryController extends BaseController
{
    @Autowired
    private ISoMaterialCategoryService soMaterialCategoryService;

    /**
     * 查询物料类别列表
     */
//    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/categoryTree")
    public AjaxResult categoryTree(SoMaterialCategory param) {
        return success(soMaterialCategoryService.selectCategoryTreeList(param));
    }

    /**
     * 查询物料类别列表
     */
//    @PreAuthorize("@ss.hasPermi('system:category:list')")
//    @GetMapping("/list")
    public TableDataInfo list(SoMaterialCategory soMaterialCategory)
    {
        startPage();
        List<SoMaterialCategory> list = soMaterialCategoryService.selectSoMaterialCategoryList(soMaterialCategory);
        return getDataTable(list);
    }

    /**
     * 导出物料类别列表
     */
//    @PreAuthorize("@ss.hasPermi('system:category:export')")
//    @PostMapping("/export")
    public void export(HttpServletResponse response, SoMaterialCategory soMaterialCategory)
    {
        List<SoMaterialCategory> list = soMaterialCategoryService.selectSoMaterialCategoryList(soMaterialCategory);
        ExcelUtil<SoMaterialCategory> util = new ExcelUtil<SoMaterialCategory>(SoMaterialCategory.class);
        util.exportExcel(response, list, "物料类别数据");
    }

    /**
     * 获取物料类别详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:category:query')")
//    @GetMapping(value = "/{cid}")
    public AjaxResult getInfo(@PathVariable("cid") Long cid)
    {
        return success(soMaterialCategoryService.selectSoMaterialCategoryByCid(cid));
    }

    /**
     * 新增物料类别
     */
//    @PreAuthorize("@ss.hasPermi('system:category:add')")
//    @PostMapping
    public AjaxResult add(@RequestBody SoMaterialCategory soMaterialCategory)
    {
        return toAjax(soMaterialCategoryService.insertSoMaterialCategory(soMaterialCategory));
    }

    /**
     * 修改物料类别
     */
//    @PreAuthorize("@ss.hasPermi('system:category:edit')")
//    @PutMapping
    public AjaxResult edit(@RequestBody SoMaterialCategory soMaterialCategory)
    {
        return toAjax(soMaterialCategoryService.updateSoMaterialCategory(soMaterialCategory));
    }

    /**
     * 删除物料类别
     */
//    @PreAuthorize("@ss.hasPermi('system:category:remove')")
//	@DeleteMapping("/{cids}")
    public AjaxResult remove(@PathVariable Long[] cids)
    {
        return toAjax(soMaterialCategoryService.deleteSoMaterialCategoryByCids(cids));
    }
}
