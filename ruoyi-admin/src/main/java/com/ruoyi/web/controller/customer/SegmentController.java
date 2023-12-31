package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.dto.SegmentAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.SegmentListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.Segment;
import com.ruoyi.customer.service.ISegmentService;

/**
 * 客群Controller
 * 
 * @author tangJM.
 * @date 2023-11-01
 */
@RestController
@RequestMapping("/customer/segment")
public class SegmentController extends BaseController
{
    @Resource
    private ISegmentService segmentService;

    /**
     * 查询客群列表
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:list')")
    @GetMapping("/list")
    public AjaxResult list(Segment segment)
    {
        Long createId = segment.getCreateId();
        if (createId == null) {
            throw new ServiceException("创建人ID不能为空");
        }

        List<SegmentListVO> list = segmentService.getSegmentTree(createId);
        return success(list);
    }

    /**
     * 查询客群列表（下拉）
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:simple:list')")
    @GetMapping("/simple/list")
    public AjaxResult simpleList()
    {
        return success(segmentService.simpleList());
    }


    /**
     * 新增客群
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:add')")
    @Log(title = "新增客群", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SegmentAddOrUpdateDTO segmentAddOrUpdateDTO)
    {
        if (StringUtils.isBlank(segmentAddOrUpdateDTO.getName())) {
            throw new ServiceException("客群名称不能为空");
        }

        if (segmentAddOrUpdateDTO.getUsageScope() == null) {
            throw new ServiceException("使用范围不能为空");
        }

        return toAjax(segmentService.insertSegment(segmentAddOrUpdateDTO));
    }

    /**
     * 客群详情
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:detail')")
    @GetMapping("/detail")
    public AjaxResult detail(Long id)
    {
        if (id == null) {
            throw new ServiceException("客群ID不能为空");
        }

        return success(segmentService.detail(id));
    }


    /**
     * 修改客群
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:edit')")
    @Log(title = "修改客群", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SegmentAddOrUpdateDTO segmentAddOrUpdateDTO)
    {
        if (segmentAddOrUpdateDTO.getId() == null) {
            throw new ServiceException("客群ID不能为空");
        }
        if (StringUtils.isBlank(segmentAddOrUpdateDTO.getName())) {
            throw new ServiceException("客群名称不能为空");
        }
        if (segmentAddOrUpdateDTO.getUsageScope() == null) {
            throw new ServiceException("使用范围不能为空");
        }

        return toAjax(segmentService.updateSegment(segmentAddOrUpdateDTO));
    }

    /**
     * 删除客群
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:delete')")
    @Log(title = "删除客群", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody Segment segment)
    {
        if (segment.getId() == null) {
            throw new ServiceException("客群ID不能为空");
        }

        return toAjax(segmentService.deleteSegmentById(segment.getId()));
    }

    /**
     * 获取条件规则字段
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:condition:rule:column')")
    @GetMapping("/get/condition/rule/column")
    public AjaxResult getConditionRuleColumn()
    {
        return success(segmentService.getConditionRuleColumn());
    }

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:user:list')")
    @GetMapping("/user/list")
    public AjaxResult userList()
    {
        return success(segmentService.userList());
    }

    /**
     * 二级分群字段
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:subgroup:column')")
    @GetMapping("/subgroup/column")
    public AjaxResult subgroupColumn()
    {
        return success(segmentService.subgroupColumn());
    }

    /**
     * 二级分群字段列表
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:subgroup:column:list')")
    @GetMapping("/subgroup/column/list")
    public AjaxResult subgroupColumnList(@NotNull(message = "字段名称不能为空") String columnName)
    {
        return success(segmentService.subgroupColumnList(columnName));
    }
}
