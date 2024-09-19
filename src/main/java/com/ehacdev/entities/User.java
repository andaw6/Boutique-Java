package com.ehacdev.entities;

import com.ehacdev.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable , Identifiable {
    private int id;
    private String prenom;
    private String nom;
    private String email;
    private String password;
    private int role_id;
    private Role role;

}
