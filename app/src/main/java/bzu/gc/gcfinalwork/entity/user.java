package bzu.gc.gcfinalwork.entity;

public class user {
    public Integer id;
    public String username;
    public String password;
    public Integer allnum;

    public Integer getAllnum() {
        return allnum;
    }

    public void setAllnum(Integer allnum) {
        this.allnum = allnum;
    }

    public Integer aimnum;
    public Integer errornum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAimnum() {
        return aimnum;
    }

    public void setAimnum(Integer aimnum) {
        this.aimnum = aimnum;
    }

    public Integer getErrornum() {
        return errornum;
    }

    public void setErrornum(Integer errornum) {
        this.errornum = errornum;
    }

    public user(Integer id, String username, String password, Integer allnum, Integer aimnum, Integer errornum) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.allnum = allnum;
        this.aimnum = aimnum;
        this.errornum = errornum;
    }
}
