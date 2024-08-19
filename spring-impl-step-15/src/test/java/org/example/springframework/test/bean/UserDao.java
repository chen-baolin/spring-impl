package org.example.springframework.test.bean;


import org.example.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "zoro，北京，亦庄");
        hashMap.put("10002", "luffy，上海，尖沙咀");
        hashMap.put("10003", "sabo，天津，东丽区");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
