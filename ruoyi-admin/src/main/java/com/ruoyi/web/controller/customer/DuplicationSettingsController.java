package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.customer.domain.dto.DuplicationSettingsActiveFlagEditDTO;
import com.ruoyi.customer.domain.vo.DuplicationSettingsListVO;
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
import com.ruoyi.customer.service.IDuplicationSettingsService;

/**
 * 客户查重设置Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */

@RestController
@RequestMapping("/customer/duplication/settings")
public class DuplicationSettingsController extends BaseController
{
    @Resource
    private IDuplicationSettingsService duplicationSettingsService;

    /**
     * 查询客户查重设置列表
     */
    @PreAuthorize("@ss.hasPermi('customer:duplication:settings:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<DuplicationSettingsListVO> list = duplicationSettingsService.list();
        return success(list);
    }

    /**
     * 修改客户查重设置标志
     */
    @PreAuthorize("@ss.hasPermi('customer:duplication:settings:edit:activeFlag')")
    @Log(title = "批量修改客户查重设置标志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit/activeFlag")
    public AjaxResult batchEditActiveFlag(@RequestBody List<DuplicationSettingsActiveFlagEditDTO> duplicationSettingsActiveFlagEditDTOList)
    {
        if (duplicationSettingsActiveFlagEditDTOList == null || duplicationSettingsActiveFlagEditDTOList.isEmpty()) {
            throw new ServiceException("参数不能为空");
        }

        return toAjax(duplicationSettingsService.updateActiveFlag(duplicationSettingsActiveFlagEditDTOList));
    }
}
