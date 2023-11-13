package com.ruoyi.customer.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.*;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.customer.domain.dto.SegmentAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.SegmentListVO;
import com.ruoyi.customer.domain.vo.SegmentUserListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.SegmentMapper;
import com.ruoyi.customer.domain.Segment;
import com.ruoyi.customer.service.ISegmentService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 客群Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-01
 */
@Service
public class SegmentServiceImpl implements ISegmentService 
{
    @Resource
    private SegmentMapper segmentMapper;

    /**
     * 查询客群
     * 
     * @param id 客群主键
     * @return 客群
     */
    @Override
    public Segment selectSegmentById(Long id)
    {
        return segmentMapper.selectSegmentById(id);
    }

    /**
     * 查询客群列表
     * 
     * @param segment 客群
     * @return 客群
     */
    @Override
    public List<Segment> selectSegmentList(Segment segment)
    {
        return segmentMapper.selectSegmentList(segment);
    }

    /**
     * 新增客群
     * 
     * @param segmentAddOrUpdateDTO 客群
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertSegment(SegmentAddOrUpdateDTO segmentAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Segment segment = new Segment();
        BeanUtils.copyProperties(segmentAddOrUpdateDTO, segment);
        segment.setCreateId(userId);
        segment.setCreateBy(username);
        segment.setCreateTime(DateUtils.getNowDate());
        segment.setUpdateId(userId);
        segment.setUpdateBy(username);
        segment.setUpdateTime(DateUtils.getNowDate());

        segmentMapper.insertSegment(segment);
        Long id = segment.getId();
        List<SegmentAddOrUpdateDTO> subGroupList = segmentAddOrUpdateDTO.getChildren();
        if (subGroupList != null && !subGroupList.isEmpty()) {
            List<Segment> segmentList = new ArrayList<>();
            subGroupList.stream().forEach(subGroup -> {
                Segment subSegment = new Segment();
                BeanUtils.copyProperties(subGroup, subSegment);
                subSegment.setParentId(id);
                subSegment.setDelFlag("0");
                subSegment.setCreateId(userId);
                subSegment.setCreateBy(username);
                subSegment.setCreateTime(DateUtils.getNowDate());
                subSegment.setUpdateId(userId);
                subSegment.setUpdateBy(username);
                subSegment.setUpdateTime(DateUtils.getNowDate());
                segmentList.add(subSegment);
            });

            // 批量新增子客群
            segmentMapper.batchInsertSegment(segmentList);
        }

        return true;
    }

    /**
     * 修改客群
     * 
     * @param segmentAddOrUpdateDTO 客群
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSegment(SegmentAddOrUpdateDTO segmentAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Segment segment = new Segment();
        BeanUtils.copyProperties(segmentAddOrUpdateDTO, segment);
        segment.setUpdateId(userId);
        segment.setUpdateBy(username);
        segment.setUpdateTime(DateUtils.getNowDate());
        segmentMapper.updateSegment(segment);

        // 删除子客群
        segmentMapper.deleteSegmentByParentId(segment.getId());

        List<SegmentAddOrUpdateDTO> subGroupList = segmentAddOrUpdateDTO.getChildren();
        if (subGroupList != null && !subGroupList.isEmpty()) {
            List<Segment> segmentList = new ArrayList<>();
            subGroupList.stream().forEach(subGroup -> {
                Segment subSegment = new Segment();
                BeanUtils.copyProperties(subGroup, subSegment);
                subSegment.setParentId(segmentAddOrUpdateDTO.getId());
                subSegment.setDelFlag("0");
                subSegment.setCreateId(userId);
                subSegment.setCreateBy(username);
                subSegment.setCreateTime(DateUtils.getNowDate());
                subSegment.setUpdateId(userId);
                subSegment.setUpdateBy(username);
                subSegment.setUpdateTime(DateUtils.getNowDate());
                segmentList.add(subSegment);
            });

            // 批量新增子客群
            segmentMapper.batchInsertSegment(segmentList);
        }

        return true;
    }

    /**
     * 批量删除客群
     * 
     * @param ids 需要删除的客群主键
     * @return 结果
     */
    @Override
    public int deleteSegmentByIds(Long[] ids)
    {
        return segmentMapper.deleteSegmentByIds(ids);
    }

    /**
     * 删除客群信息
     * 
     * @param id 客群主键
     * @return 结果
     */
    @Override
    public boolean deleteSegmentById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        segmentMapper.deleteSegmentById(id, userId, username);
        segmentMapper.deleteSegmentByParentId(id);
        return true;
    }

    /**
     * 客群树
     * @param createId
     * @return
     */
    @Override
    public List<SegmentListVO> getSegmentTree(Long createId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        // 生成基础客群
        List<SegmentListVO> allSegmentVOList = new ArrayList<>();
        allSegmentVOList.addAll(initBasicSegment(userId));

        List<SegmentListVO> segmentVOList = segmentMapper.list(userId, createId);
        allSegmentVOList.addAll(buildTree(segmentVOList, -1L));

        return allSegmentVOList;
    }

    /**
     * 获取条件规则字段
     * @return
     */
    @Override
    public List<Map<String, Object>> getConditionRuleColumn() {
        Map<CustomerColumnCategoryEnum, List<CustomerColumnEnum>> customerColumnCategoryMap = Arrays.stream(CustomerColumnEnum.values()).collect(Collectors.groupingBy(CustomerColumnEnum::getCustomerColumnCategoryEnum));

        List<CustomerColumnEnum> companyColumnEnumList = customerColumnCategoryMap.get(CustomerColumnCategoryEnum.COMPANY_INFO);
        List<CustomerColumnEnum> contactColumnEnumList = customerColumnCategoryMap.get(CustomerColumnCategoryEnum.CONTACT_INFO);
        List<CustomerColumnEnum> dateTimeColumnEnumList = customerColumnCategoryMap.get(CustomerColumnCategoryEnum.DATE_TIME);

        Map<String, Object> companyInfoMap = new HashMap<>();
        companyInfoMap.put("name", "公司名称");
        List<Map<String, String>> companyColumnMapList = new ArrayList<>();
        for (CustomerColumnEnum companyColumnEnum : companyColumnEnumList) {
            Map<String, String> companyColumnMap = new HashMap<>();
            companyColumnMap.put("columnName", companyColumnEnum.getColumnName());
            companyColumnMap.put("nickName", companyColumnEnum.getNickName());
            companyColumnMapList.add(companyColumnMap);
        }
        companyInfoMap.put("children", companyColumnMapList);

        Map<String, Object> contactInfoMap = new HashMap<>();
        contactInfoMap.put("name", "联系人信息");
        List<Map<String, String>> contackColumnMapList = new ArrayList<>();
        for (CustomerColumnEnum contactColumnEnum : contactColumnEnumList) {
            Map<String, String> contactColumnMap = new HashMap<>();
            contactColumnMap.put("columnName", contactColumnEnum.getColumnName());
            contactColumnMap.put("nickName", contactColumnEnum.getNickName());
            contackColumnMapList.add(contactColumnMap);
        }
        contactInfoMap.put("children", contackColumnMapList);

        Map<String, Object> dateTimeMap = new HashMap<>();
        dateTimeMap.put("name", "日期时间");
        List<Map<String, String>> dateTimeColumnMapList = new ArrayList<>();
        for (CustomerColumnEnum dateTimeColumnEnum : dateTimeColumnEnumList) {
            Map<String, String> dateTimeColumnMap = new HashMap<>();
            dateTimeColumnMap.put("columnName", dateTimeColumnEnum.getColumnName());
            dateTimeColumnMap.put("nickName", dateTimeColumnEnum.getNickName());
            dateTimeColumnMapList.add(dateTimeColumnMap);
        }
        dateTimeMap.put("children", dateTimeColumnMapList);

        List<Map<String, Object>> result = new ArrayList<>();
        result.add(companyInfoMap);
        result.add(contactInfoMap);
        result.add(dateTimeMap);
        return result;
    }

    @Override
    public List<SegmentUserListVO> userList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        List<SegmentUserListVO> segmentUserList = segmentMapper.userList();
        SegmentUserListVO currentUser = new SegmentUserListVO();
        Iterator<SegmentUserListVO> iterator = segmentUserList.iterator();
        while (iterator.hasNext()) {
            SegmentUserListVO segmentUserVO = iterator.next();
            if (segmentUserVO.getUserId().longValue() == userId.longValue()) {
                currentUser = segmentUserVO;
                iterator.remove();
                break;
            }
        }

        currentUser.setNickName("我的");
        segmentUserList.add(0, currentUser);
        return segmentUserList;
    }

    /**
     * 生成基础客群
     * @return
     */
    private List<SegmentListVO> initBasicSegment(Long userId) {
        List<SegmentListVO> basicSegment = new ArrayList<>();

        SegmentListVO allSegment = new SegmentListVO();
        allSegment.setId(-2L);
        allSegment.setParentId(null);
        allSegment.setName("全部客户");
        allSegment.setUsageScope(2);
        // 查询客户数量
        Integer allCustomerCount = segmentMapper.countCustomerCount(userId, null);
        allSegment.setCustomerCount(allCustomerCount);
        basicSegment.add(allSegment);

        SegmentListVO followSegment = new SegmentListVO();
        followSegment.setId(-1L);
        followSegment.setParentId(null);
        followSegment.setName("我的关注");
        followSegment.setUsageScope(2);
        // 查询客户数量
        Integer focusCustomerCount = segmentMapper.countCustomerCount(userId, true);
        followSegment.setCustomerCount(focusCustomerCount);
        basicSegment.add(followSegment);

        return basicSegment;
    }

    private List<SegmentListVO> buildTree(List<SegmentListVO> segmentVOList, Long parentId) {
        List<SegmentListVO> children = new ArrayList<>();

        for (SegmentListVO segmentVO : segmentVOList) {
            if ((parentId == null && segmentVO.getParentId() == null)
                    || (parentId != null && parentId.longValue() == segmentVO.getParentId().longValue())) {
                List<SegmentListVO> childSegments = buildTree(segmentVOList, segmentVO.getId());
                segmentVO.setChildren(childSegments);
                children.add(segmentVO);
            }
        }

        return children;
    }
}
