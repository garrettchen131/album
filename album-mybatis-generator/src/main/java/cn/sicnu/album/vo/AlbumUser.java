package cn.sicnu.album.vo;

import java.io.Serializable;

public class AlbumUser implements Serializable {
    private Integer albumId;

    private Integer userId;

    private String role;

    private static final long serialVersionUID = 1L;

    public AlbumUser(Integer albumId, Integer userId, String role) {
        this.albumId = albumId;
        this.userId = userId;
        this.role = role;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", albumId=").append(albumId);
        sb.append(", userId=").append(userId);
        sb.append(", role=").append(role);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}