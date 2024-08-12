package org.example.springframework.test.event;

import org.example.springframework.context.ApplicationListener;
import org.example.springframework.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("容器刷新事件：" + this.getClass().getName());
    }
}
