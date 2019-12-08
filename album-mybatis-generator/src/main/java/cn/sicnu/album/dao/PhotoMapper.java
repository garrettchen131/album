package cn.sicnu.album.dao;

import cn.sicnu.album.vo.Photo;
import cn.sicnu.album.vo.PhotoExample;
import java.util.List;

public interface PhotoMapper {
    long countByExample(PhotoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Photo record);

    int insertSelective(Photo record);

    List<Photo> selectByExample(PhotoExample example);

    Photo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);
}