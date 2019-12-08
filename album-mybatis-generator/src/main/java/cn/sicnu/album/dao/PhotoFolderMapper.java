package cn.sicnu.album.dao;

import cn.sicnu.album.vo.PhotoFolder;
import cn.sicnu.album.vo.PhotoFolderExample;
import java.util.List;

public interface PhotoFolderMapper {
    long countByExample(PhotoFolderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhotoFolder record);

    int insertSelective(PhotoFolder record);

    List<PhotoFolder> selectByExample(PhotoFolderExample example);

    PhotoFolder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhotoFolder record);

    int updateByPrimaryKey(PhotoFolder record);
}