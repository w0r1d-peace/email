package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.dto.PublicleadsRulesAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.PublicleadsRulesListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.PublicleadsRules;
import com.ruoyi.customer.service.IPublicleadsRulesService;

/**
 * 移入公海规则Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */

@RestController
@RequestMapping("/customer/public/leads/rules")
public class PublicleadsRulesController extends BaseController
{
    @Resource
    private IPublicleadsRulesService publicleadsRulesService;

    /**
     * 查询移入公海规则列表
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:rules:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<PublicleadsRulesListVO> list = publicleadsRulesService.list();
        return success(list);
    }

    /**
     * 新增移入公海规则
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:rules:add')")
    @Log(title = "新增移入公海规则", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PublicleadsRulesAddOrUpdateDTO publicleadsRulesAddOrUpdateDTO)
    {
        checkParam(publicleadsRulesAddOrUpdateDTO);
        return toAjax(publicleadsRulesService.insertPublicleadsRules(publicleadsRulesAddOrUpdateDTO));
    }

    /**
     * 修改移入公海规则
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:rules:edit')")
    @Log(title = "修改移入公海规则", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PublicleadsRulesAddOrUpdateDTO publicleadsRulesAddOrUpdateDTO)
    {
        if (publicleadsRulesAddOrUpdateDTO.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        checkParam(publicleadsRulesAddOrUpdateDTO);

        return toAjax(publicleadsRulesService.updatePublicleadsRules(publicleadsRulesAddOrUpdateDTO));
    }

    /**
     * 校验参数
     * @param publicleadsRulesAddOrUpdateDTO
     */
    private static void checkParam(PublicleadsRulesAddOrUpdateDTO publicleadsRulesAddOrUpdateDTO) {
        if (StringUtils.isBlank(publicleadsRulesAddOrUpdateDTO.getName())) {
            throw new ServiceException("规则名称不能为空");
        }
        if (publicleadsRulesAddOrUpdateDTO.getSegmentIdList() == null || publicleadsRulesAddOrUpdateDTO.getSegmentIdList().isEmpty()) {
            throw new ServiceException("请选择客群");
        }
        if (publicleadsRulesAddOrUpdateDTO.getDays() == null) {
            throw new ServiceException("规则天数不能为空");
        }
        if (publicleadsRulesAddOrUpdateDTO.getDays() < 0) {
            throw new ServiceException("规则天数不能小于0");
        }
        if (publicleadsRulesAddOrUpdateDTO.getType() == null) {
            throw new ServiceException("规则类型不能为空");
        }
        if (publicleadsRulesAddOrUpdateDTO.getStartTime() == null) {
            throw new ServiceException("开始时间不能为空");
        }
    }

    /**
     * 删除移入公海规则
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:rules:delete')")
    @Log(title = "删除移入公海规则", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody PublicleadsRules publicleadsRules)
    {
        if (publicleadsRules.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(publicleadsRulesService.deletePublicleadsRulesById(publicleadsRules.getId()));
    }
}
