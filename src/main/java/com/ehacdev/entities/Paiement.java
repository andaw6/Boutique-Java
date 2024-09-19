package com.ehacdev.entities;

import com.ehacdev.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
    public class Paiement implements Serializable,  Identifiable {
    private int id;
    private double montant;
    private String date;
    private int dette_id;
    private int client_id;
    private Client client;
    private Dette dette;
}
