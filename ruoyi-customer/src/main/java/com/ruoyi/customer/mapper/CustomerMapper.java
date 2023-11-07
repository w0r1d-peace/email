package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.Customer;
import com.ruoyi.customer.domain.bo.PublicleadsGroupsListBO;
import com.ruoyi.customer.domain.vo.CustomerDetailVO;
import com.ruoyi.customer.domain.vo.CustomerSimpleListVO;
import com.ruoyi.customer.domain.vo.PublicleadsGroupsListVO;
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

    /**
     * 移动客户到分组
     * @param id
     * @param packetId
     */
    void moveCustomerToPacket(@Param("id") Long id, @Param("packetId") Long packetId);

    /**
     * 修改重点关注
     * @param id
     * @param updateId
     * @param updateBy
     */
    void updateFocusFlag(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 变更公海分组
     * @param id
     * @param publicleadsGroupsId
     * @param updateId
     * @param updateBy
     */
    void changePublicleadsGroups(@Param("id") Long id, @Param("publicleadsGroupsId") Long publicleadsGroupsId, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 查询公海分组列表
     * @param customerId
     * @param userId
     * @return
     */
    List<PublicleadsGroupsListVO> publicleadsGroupsList(Long customerId, Long userId);

    /**
     * 将客户移入私海
     * @param id
     */
    void moveToPrivateleads(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);
}
