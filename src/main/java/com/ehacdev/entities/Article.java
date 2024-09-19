package com.ehacdev.entities;

import com.ehacdev.interfaces.Identifiable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", price=" + price + ", quantity=" + quantity+", threshold="+threshold+"]";
    }
}
