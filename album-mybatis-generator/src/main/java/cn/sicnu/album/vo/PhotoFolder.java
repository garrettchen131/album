package cn.sicnu.album.vo;

import java.io.Serializable;
import java.util.Date;

public class PhotoFolder implements Serializable {
    private Integer id;

    private String title;

    private Integer userId;

    private Integer albumId;

    private Date createTime;

    private String storePath;

    private String desc;

    private String photoList;

    private Integer photoCount;

    private static final long serialVersionUID = 1L;

    public PhotoFolder(Integer id, String title, Integer userId, Integer albumId, Date createTime, String storePath, String desc, String photoList, Integer photoCount) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.albumId = albumId;
        this.createTime = createTime;
        this.storePath = storePath;
        this.desc = desc;
        this.photoList = photoList;
        this.photoCount = photoCount;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getStorePath() {
        return storePath;
    }

    public String getDesc() {
        return desc;
    }

    public String getPhotoList() {
        return photoList;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", userId=").append(userId);
        sb.append(", albumId=").append(albumId);
        sb.append(", createTime=").append(createTime);
        sb.append(", storePath=").append(storePath);
        sb.append(", desc=").append(desc);
        sb.append(", photoList=").append(photoList);
        sb.append(", photoCount=").append(photoCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}