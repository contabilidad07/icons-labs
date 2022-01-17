package com.icons.icons.lab.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity     //la clase ContinenteEntity se convertira en una tabla
@Table(name = "continente")
@Getter
@Setter

public class ContinenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    private String imagen;

    private String denominacion;


}
