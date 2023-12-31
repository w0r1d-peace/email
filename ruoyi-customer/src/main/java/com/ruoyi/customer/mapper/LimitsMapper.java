package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.Limits;
import com.ruoyi.customer.domain.vo.LimitsListVO;

/**
 * 客户上限Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface LimitsMapper 
{
    /**
     * 查询客户上限
     * 
     * @param id 客户上限主键
     * @return 客户上限
     */
    public Limits selectLimitsById(Long id);

    /**
     * 查询客户上限列表
     * 
     * @param limits 客户上限
     * @return 客户上限集合
     */
    public List<Limits> selectLimitsList(Limits limits);

    /**
     * 新增客户上限
     * 
     * @param limits 客户上限
     * @return 结果
     */
    public int insertLimits(Limits limits);

    /**
     * 修改客户上限
     * 
     * @param limits 客户上限
     * @return 结果
     */
    public int updateLimits(Limits limits);

    /**
     * 删除客户上限
     * 
     * @param id 客户上限主键
     * @return 结果
     */
    public int deleteLimitsById(Long id);

    /**
     * 批量删除客户上限
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLimitsByIds(Long[] ids);

    /**
     * 客户上限列表
     * @return
     */
    List<LimitsListVO> list();
}
