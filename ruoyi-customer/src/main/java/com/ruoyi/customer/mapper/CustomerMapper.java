package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.Customer;
import com.ruoyi.customer.domain.vo.CustomerSimpleListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 客户详情Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface CustomerMapper 
{
    /**
     * 查询客户详情
     * 
     * @param id 客户详情主键
     * @return 客户详情
     */
    public Customer selectCustomerById(Long id);

    /**
     * 查询客户详情列表
     * 
     * @param customer 客户详情
     * @return 客户详情集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增客户详情
     * 
     * @param customer 客户详情
     * @return 结果
     */
    public long insertCustomer(Customer customer);

    /**
     * 修改客户详情
     * 
     * @param customer 客户详情
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 删除客户详情
     * 
     * @param id 客户详情主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);

    /**
     * 批量删除客户详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(Long[] ids);

    /**
     * 统计客户数量
     * @param seaType
     * @return
     */
    int count(@Param("seaType") Integer seaType);

    /**
     *
     * @param segmentId
     * @param seaType
     * @param offset
     * @param limit
     * @return
     */
    List<CustomerSimpleListVO> selectCustomerPage(@Param("segmentId") Long segmentId, @Param("seaType") Integer seaType, @Param("offset") int offset, @Param("limit") int limit);
}