package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.Task;
import com.ruoyi.email.domain.vo.TaskListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 邮箱任务Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface TaskMapper 
{
    /**
     * 查询邮箱任务
     * 
     * @param id 邮箱任务主键
     * @return 邮箱任务
     */
    public Task selectTaskById(Long id);

    /**
     * 查询邮箱任务列表
     * 
     * @param task 邮箱任务
     * @return 邮箱任务集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增邮箱任务
     * 
     * @param task 邮箱任务
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改邮箱任务
     * 
     * @param task 邮箱任务
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 删除邮箱任务
     * 
     * @param id 邮箱任务主键
     * @return 结果
     */
    public int deleteTaskById(Long id);

    /**
     * 批量删除邮箱任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskByIds(Long[] ids);

    /**
     * 查询当前邮箱是否已经存在
     * @param account
     * @param userId
     * @return
     */
    int countAccount(@Param("account") String account, @Param("userId") Long userId);

    /**
     * 根据任务id和创建人查询任务
     * @param id
     * @param userId
     * @return
     */
    Task getTaskById(@Param("id") Long id, @Param("createId") Long userId);

    /**
     * 列表
     * @param userId
     * @return
     */
    List<TaskListVO> listTask(@Param("createId")Long userId);

    /**
     * 解绑
     * @param id
     * @param userId
     */
    int unbindTaskById(@Param("id") Long id, @Param("createId") Long userId);

    /**
     * 根据任务id和创建人查询任务
     * @param id
     * @param createId
     * @return
     */
    int countById(@Param("id") Long id, @Param("createId") Long createId);

    /**
     * 根据创建人查询任务id
     * @param userId
     * @return
     */
    List<Long> listIdByUserId(@Param("createId") Long userId);

    /**
     * 查询当前用户所有任务id
     * @param createId
     * @return
     */
    List<Long> selectAllTaskIds(@Param("createId") Long createId);
}
