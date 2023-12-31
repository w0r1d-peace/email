package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.CustomerFollowUpPersonnel;

/**
 * 客户跟进人Service接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface ICustomerFollowUpPersonnelService 
{
    /**
     * 查询客户跟进人
     * 
     * @param id 客户跟进人主键
     * @return 客户跟进人
     */
    public CustomerFollowUpPersonnel selectCustomerFollowUpPersonnelById(Long id);

    /**
     * 查询客户跟进人列表
     * 
     * @param customerFollowUpPersonnel 客户跟进人
     * @return 客户跟进人集合
     */
    public List<CustomerFollowUpPersonnel> selectCustomerFollowUpPersonnelList(CustomerFollowUpPersonnel customerFollowUpPersonnel);

    /**
     * 新增客户跟进人
     * 
     * @param customerFollowUpPersonnel 客户跟进人
     * @return 结果
     */
    public int insertCustomerFollowUpPersonnel(CustomerFollowUpPersonnel customerFollowUpPersonnel);

    /**
     * 修改客户跟进人
     * 
     * @param customerFollowUpPersonnel 客户跟进人
     * @return 结果
     */
    public int updateCustomerFollowUpPersonnel(CustomerFollowUpPersonnel customerFollowUpPersonnel);

    /**
     * 批量删除客户跟进人
     * 
     * @param ids 需要删除的客户跟进人主键集合
     * @return 结果
     */
    public int deleteCustomerFollowUpPersonnelByIds(Long[] ids);

    /**
     * 删除客户跟进人信息
     * 
     * @param id 客户跟进人主键
     * @return 结果
     */
    public int deleteCustomerFollowUpPersonnelById(Long id);
}
