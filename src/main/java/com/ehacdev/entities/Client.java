package com.ehacdev.entities;

import com.ehacdev.interfaces.Identifiable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable, Identifiable {
    private int id;
    private String surname;
    private String adresse;
    private String telephone;
    private String qrcode;
    private int maxMontant = 0;
    private User user;
    private Categorie categorie;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
