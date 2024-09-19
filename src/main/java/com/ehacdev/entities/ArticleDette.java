package com.ehacdev.entities;

import com.ehacdev.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDette implements Identifiable, Serializable {
    private int id;
}
