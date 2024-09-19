package com.ehacdev.views;

import com.ehacdev.views.interfaces.IView;
import org.springframework.stereotype.Component;

import java.util.Scanner;

public abstract class View implements IView {

    protected final Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

}
