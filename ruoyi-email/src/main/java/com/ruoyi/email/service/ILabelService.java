package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.Label;
import com.ruoyi.email.domain.bo.EmailLabelBO;
import com.ruoyi.email.domain.vo.LabelListVO;

/**
 * 标签Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ILabelService 
{
    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    public Label selectLabelById(Long id);

    /**
     * 查询标签列表
     * 
     * @param label 标签
     * @return 标签集合
     */
    public List<Label> selectLabelList(Label label);

    /**
     * 新增标签
     * 
     * @param label 标签
     * @return 结果
     */
    public int insertLabel(Label label);

    /**
     * 修改标签
     * 
     * @param label 标签
     * @return 结果
     */
    public int updateLabel(Label label);

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的标签主键集合
     * @return 结果
     */
    public int deleteLabelByIds(Long[] ids);

    /**
     * 删除标签信息
     * 
     * @param id 标签主键
     * @return 结果
     */
    public int deleteLabelById(Long id);

    /**
     * 标签列表
     * @return
     */
    List<LabelListVO> list();

    /**
     * 修改标签颜色
     * @param id
     * @param color
     * @return
     */
    boolean editColor(Long id, String color);

    /**
     * 修改标签名称
     * @param id
     * @param name
     * @return
     */
    boolean editName(Long id, String name);

    /**
     * 删除标签
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据邮件id查询标签
     * @param emailIds
     * @return
     */
    List<EmailLabelBO> listByEmailIds(List<Long> emailIds);
}
