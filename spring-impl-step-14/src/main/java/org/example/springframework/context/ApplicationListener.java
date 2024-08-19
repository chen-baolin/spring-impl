package org.example.springframework.context;

import java.util.EventListener;

public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * handle an application event
     * @param event
     */
    void onApplicationEvent(E event);
}
