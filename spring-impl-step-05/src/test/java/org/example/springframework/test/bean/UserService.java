package org.example.springframework.test.bean;

public class UserService {

    private String uId;

    private String name;

    private UserDao userDao;

    public UserService() {}

    public UserService(String name) {
        this.name = name;
    }

    public String queryUserInfo() {
        return userDao.queryUserName(uId);
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuId() {
        return this.uId;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return this.userDao;
    }
}
