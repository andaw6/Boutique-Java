package com.ehacdev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.ehacdev.config.AppConfig;
import com.ehacdev.views.MenuView;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.ehacdev")
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MenuView menuView = context.getBean(MenuView.class);
        menuView.showMenu();

    }
}