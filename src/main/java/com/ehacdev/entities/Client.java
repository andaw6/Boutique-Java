package com.ehacdev.entities;

import com.ehacdev.interfaces.Identifiable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Optional;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable, Identifiable {

    private int id;
    private String surname;
    private String address;
    private String qrcode;
    private String telephone;
    private int max_montant;
    private Optional<Integer> user_id;
    private int categorie_id;
    private Categorie categorie;

}
