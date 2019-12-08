package cn.sicnu.album.vo;

import java.io.Serializable;
import java.util.Date;

public class Album implements Serializable {
    private Integer id;

    private String title;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Album(Integer id, String title, Date createTime) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
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
        sb.append(", title=").append(title);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}