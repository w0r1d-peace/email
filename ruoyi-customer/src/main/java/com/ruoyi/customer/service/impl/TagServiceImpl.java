package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.TagTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.TagListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.TagMapper;
import com.ruoyi.customer.domain.Tag;
import com.ruoyi.customer.service.ITagService;

import javax.annotation.Resource;

/**
 * 客户标签Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-17
 */
@Service
public class TagServiceImpl implements ITagService 
{
    @Resource
    private TagMapper tagMapper;

    /**
     * 查询客户标签
     * 
     * @param id 客户标签主键
     * @return 客户标签
     */
    @Override
    public Tag selectTagById(Long id)
    {
        return tagMapper.selectTagById(id);
    }

    /**
     * 查询客户标签列表
     * 
     * @param tag 客户标签
     * @return 客户标签
     */
    @Override
    public List<Tag> selectTagList(Tag tag)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        tag.setCreateId(userId);
        return tagMapper.selectTagList(tag);
    }

    /**
     * 新增客户标签
     * 
     * @param tag 客户标签
     * @return 结果
     */
    @Override
    public int insertTag(Tag tag)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        tag.setCreateId(userId);
        tag.setCreateBy(username);
        tag.setCreateTime(DateUtils.getNowDate());
        tag.setUpdateId(userId);
        tag.setUpdateBy(username);
        tag.setUpdateTime(DateUtils.getNowDate());
        return tagMapper.insertTag(tag);
    }

    /**
     * 修改客户标签
     * 
     * @param tag 客户标签
     * @return 结果
     */
    @Override
    public int updateTag(Tag tag)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        tag.setUpdateId(userId);
        tag.setUpdateBy(username);
        tag.setUpdateTime(DateUtils.getNowDate());
        return tagMapper.updateTag(tag);
    }

    /**
     * 批量删除客户标签
     * 
     * @param ids 需要删除的客户标签主键
     * @return 结果
     */
    @Override
    public int deleteTagByIds(Long[] ids)
    {
        return tagMapper.deleteTagByIds(ids);
    }

    /**
     * 删除客户标签信息
     * 
     * @param id 客户标签主键
     * @return 结果
     */
    @Override
    public int deleteTagById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return tagMapper.deleteTagById(id, userId, username);
    }

    /**
     * 标签列表
     * @param type
     * @return
     */
    @Override
    public List<TagListVO> list(Integer type) {
        Tag tagParam = new Tag();
        tagParam.setType(type);
        tagParam.setCreateId(SecurityUtils.getLoginUser().getUserId());
        tagParam.setDelFlag("0");
        List<Tag> tagList = tagMapper.selectTagList(tagParam);

        List<TagListVO> tagVOList = new ArrayList<>();
        for (Tag tag : tagList) {
            TagListVO tagVO = new TagListVO();
            tagVO.setId(tag.getId());
            tagVO.setName(tag.getName());
            tagVO.setColor(tag.getColor());
            tagVO.setType(tag.getType());
            tagVOList.add(tagVO);
        }

        return tagVOList;
    }

    /**
     * 设为公司标签
     * @param id
     * @return
     */
    @Override
    public boolean setAsCompanyTag(Long id) {
        Tag tag = tagMapper.selectTagById(id);
        if (tag == null) {
            throw new ServiceException("不存在的标签");
        }

        if (tag.getType().intValue() == TagTypeEnum.COMPANY.getType()) {
            throw new ServiceException("已经是公司标签");
        }

        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        tag.setType(TagTypeEnum.COMPANY.getType());
        tag.setUpdateId(userId);
        tag.setUpdateBy(username);
        tag.setUpdateTime(DateUtils.getNowDate());
        tagMapper.updateTag(tag);
        return true;
    }
}
