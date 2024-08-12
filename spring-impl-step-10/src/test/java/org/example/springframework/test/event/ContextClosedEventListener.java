package org.example.springframework.test.event;

import org.example.springframework.context.ApplicationListener;
import org.example.springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("容器关闭事件：" + this.getClass().getName());
    }
}
