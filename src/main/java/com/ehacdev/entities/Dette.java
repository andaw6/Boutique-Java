package com.ehacdev.entities;

import com.ehacdev.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dette implements Serializable,Identifiable {
    private int id;
    private String montant;
    private String date;
    private String echeance;
    private int client_id;
    private Collection<Paiement> paiements;
    private Collection<Article> articles;
    private Client client;
}
