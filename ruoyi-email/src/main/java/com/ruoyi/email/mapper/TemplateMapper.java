package com.ruoyi.email.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.email.domain.Template;
import com.ruoyi.email.domain.vo.TemplateListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 模板Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface TemplateMapper 
{
    /**
     * 查询模板
     * 
     * @param id 模板主键
     * @return 模板
     */
    public Template selectTemplateById(Long id);

    /**
     * 查询模板列表
     * 
     * @param template 模板
     * @return 模板集合
     */
    public List<Template> selectTemplateList(Template template);

    /**
     * 新增模板
     * 
     * @param template 模板
     * @return 结果
     */
    public int insertTemplate(Template template);

    /**
     * 修改模板
     * 
     * @param template 模板
     * @return 结果
     */
    public int updateTemplate(Template template);

    /**
     * 删除模板
     * 
     * @param id 模板主键
     * @return 结果
     */
    public int deleteTemplateById(Long id);

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTemplateByIds(Long[] ids);

    /**
     * 模板列表
     * @param userId
     * @return
     */
    List<TemplateListVO> getByCreateId(@Param("createId") Long userId);

    /**
     * 根据id删除模板
     * @param id
     * @param userId
     * @param nowDate
     */
    void deleteById(@Param("id") Long id, @Param("createId") Long userId, @Param("updateTime") Date nowDate);
}
