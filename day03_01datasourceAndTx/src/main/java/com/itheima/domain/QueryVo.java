package com.itheima.domain;

import java.io.Serializable;

public class QueryVo implements Serializable {

    private User user1;

    public User getUser() {
        return user1;
    }

    public void setUser(User user) {
        this.user1 = user;
    }
}
