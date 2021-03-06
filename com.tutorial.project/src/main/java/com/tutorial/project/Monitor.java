/**
 *  Event handling in the ApplicationContext is provided through the ApplicationEvent class and 
 *  ApplicationListener interface. Hence, if a bean implements the ApplicationListener, then every 
 *  time an ApplicationEvent gets published to the ApplicationContext, that bean is notified.
 */

package com.tutorial.project;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Monitor implements ApplicationListener<ApplicationEvent>{

	public void onApplicationEvent(ApplicationEvent event) {
		
		System.out.println("ApplicationEvent Received");
		System.out.println(event.getClass().getSimpleName() + " > " +event.getSource().toString());
	}

}
