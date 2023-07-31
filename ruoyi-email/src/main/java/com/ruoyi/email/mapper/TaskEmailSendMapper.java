package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.TaskEmailSend;

/**
 * 发送邮件Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface TaskEmailSendMapper 
{
    /**
     * 查询发送邮件
     * 
     * @param id 发送邮件主键
     * @return 发送邮件
     */
    public TaskEmailSend selectTaskEmailSendById(Long id);

    /**
     * 查询发送邮件列表
     * 
     * @param taskEmailSend 发送邮件
     * @return 发送邮件集合
     */
    public List<TaskEmailSend> selectTaskEmailSendList(TaskEmailSend taskEmailSend);

    /**
     * 新增发送邮件
     * 
     * @param taskEmailSend 发送邮件
     * @return 结果
     */
    public int insertTaskEmailSend(TaskEmailSend taskEmailSend);

    /**
     * 修改发送邮件
     * 
     * @param taskEmailSend 发送邮件
     * @return 结果
     */
    public int updateTaskEmailSend(TaskEmailSend taskEmailSend);

    /**
     * 删除发送邮件
     * 
     * @param id 发送邮件主键
     * @return 结果
     */
    public int deleteTaskEmailSendById(Long id);

    /**
     * 批量删除发送邮件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskEmailSendByIds(Long[] ids);
}
