package com.ehacdev.entities;

import com.ehacdev.interfaces.Identifiable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dette implements Serializable, Identifiable {
    private int id;
    private int montant;
    private LocalDate date;
    private LocalDate echeance;
    private Client client;
    private Collection<Article> articles;
    private Collection<Paiement> paiements;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
