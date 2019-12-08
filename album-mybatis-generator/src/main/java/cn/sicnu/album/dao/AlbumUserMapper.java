package cn.sicnu.album.dao;

import cn.sicnu.album.vo.AlbumUser;
import cn.sicnu.album.vo.AlbumUserExample;
import java.util.List;

public interface AlbumUserMapper {
    long countByExample(AlbumUserExample example);

    int insert(AlbumUser record);

    int insertSelective(AlbumUser record);

    List<AlbumUser> selectByExample(AlbumUserExample example);
}