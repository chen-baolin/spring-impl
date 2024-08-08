package org.example.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "zoro");
        hashMap.put("10002", "luffy");
        hashMap.put("10003", "asce");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
