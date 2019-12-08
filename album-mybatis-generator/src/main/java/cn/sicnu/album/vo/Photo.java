package cn.sicnu.album.vo;

import java.io.Serializable;
import java.util.Date;

public class Photo implements Serializable {
    private Long id;

    private String guid;

    private Integer photoFolderId;

    private String storePath;

    private String realName;

    private String suffix;

    private String name;

    private String thumb;

    private String desc;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Photo(Long id, String guid, Integer photoFolderId, String storePath, String realName, String suffix, String name, String thumb, String desc, Date createTime) {
        this.id = id;
        this.guid = guid;
        this.photoFolderId = photoFolderId;
        this.storePath = storePath;
        this.realName = realName;
        this.suffix = suffix;
        this.name = name;
        this.thumb = thumb;
        this.desc = desc;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public Integer getPhotoFolderId() {
        return photoFolderId;
    }

    public String getStorePath() {
        return storePath;
    }

    public String getRealName() {
        return realName;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getName() {
        return name;
    }

    public String getThumb() {
        return thumb;
    }

    public String getDesc() {
        return desc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", guid=").append(guid);
        sb.append(", photoFolderId=").append(photoFolderId);
        sb.append(", storePath=").append(storePath);
        sb.append(", realName=").append(realName);
        sb.append(", suffix=").append(suffix);
        sb.append(", name=").append(name);
        sb.append(", thumb=").append(thumb);
        sb.append(", desc=").append(desc);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}