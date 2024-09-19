/* package com.ehacdev.entities;

import com.ehacdev.interfaces.Identifiable;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable, Identifiable {
    private int id;
    private String title;
    private double price;
    private int quantity;
    private int threshold; // quantité
    private Collection<Dette> dettes;



    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", price=" + price + ", quantity=" + quantity+", threshold="+threshold+"]";
    }
}
 */

package com.ehacdev.entities;

import com.ehacdev.interfaces.Identifiable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Identifiable {
    private int id;
    private String title;
    private double price;
    private int quantity;
    private int threshold = 3;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
