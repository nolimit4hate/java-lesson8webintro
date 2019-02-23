package model;

import java.io.Serializable;


public class UserBean implements Serializable {
    private static final long serialVersionUID = 10L;

    private int id;
    private String name;
    private String email;
    private UserRolePool userRolePool;
    private String password;
    private String fio;
    private CountryPool country;
    private String crDate;

    public UserBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public CountryPool getCountry() {
        return country;
    }

    public void setCountry(CountryPool country) {
        this.country = country;
    }

    public String getCrDate() {
        return crDate;
    }

    public void setCrDate(String crDate) {
        this.crDate = crDate;
    }

    public UserRolePool getUserRolePool() { return userRolePool; }

    public void setUserRolePool(UserRolePool userRolePool) { this.userRolePool = userRolePool; }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userRolePool=" + userRolePool +
                ", password='" + password + '\'' +
                ", fio='" + fio + '\'' +
                ", country=" + country +
                ", crDate='" + crDate + '\'' +
                '}';
    }
}
