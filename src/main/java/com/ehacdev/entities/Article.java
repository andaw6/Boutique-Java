package com.ehacdev.entities;

import com.ehacdev.interfaces.Identifiable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article implements Identifiable {

    private int id;

    private String title;

    private double price;

    private int quantity;

    private int threshold; // quantity


    @Override
    public int getId() {
        return id;
    }

}
