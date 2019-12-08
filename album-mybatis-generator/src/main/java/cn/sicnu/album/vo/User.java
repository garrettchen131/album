package cn.sicnu.album.vo;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    private String name;

    private String password;

    private String mail;

    private String phone;

    private String role;

    private static final long serialVersionUID = 1L;

    public User(Integer id, String name, String password, String mail, String phone, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.phone = phone;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
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
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", mail=").append(mail);
        sb.append(", phone=").append(phone);
        sb.append(", role=").append(role);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}