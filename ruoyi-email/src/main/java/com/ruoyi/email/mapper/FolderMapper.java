package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.Folder;
import com.ruoyi.email.domain.vo.folder.FolderListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 文件夹Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface FolderMapper 
{
    /**
     * 查询文件夹
     * 
     * @param id 文件夹主键
     * @return 文件夹
     */
    public Folder selectFolderById(Long id);

    /**
     * 查询文件夹列表
     * 
     * @param folder 文件夹
     * @return 文件夹集合
     */
    public List<Folder> selectFolderList(Folder folder);

    /**
     * 新增文件夹
     * 
     * @param folder 文件夹
     * @return 结果
     */
    public int insertFolder(Folder folder);

    /**
     * 修改文件夹
     * 
     * @param folder 文件夹
     * @return 结果
     */
    public int updateFolder(Folder folder);

    /**
     * 删除文件夹
     * 
     * @param id 文件夹主键
     * @return 结果
     */
    public int deleteFolderById(Long id);

    /**
     * 批量删除文件夹
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFolderByIds(Long[] ids);

    /**
     * 获取文件夹列表
     * @param userId
     * @return
     */
    List<FolderListVO> getFolderList(@Param("createId") Long userId);
}
