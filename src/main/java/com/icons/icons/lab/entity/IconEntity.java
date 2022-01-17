package com.icons.icons.lab.entity;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

import java.time.LocalDate;


    @Entity
    @Table(name = "icon") //tala IconEntity la renombrro
    @Getter
    @Setter

    public class IconEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        //como voy a generar los Id) defino una estrategia que es secuence especifica que se genere el id automáticamente
        private Long id;  // atributo llave primaria
     //cuando el atributo id, se llama igual que la columna de la base de datos, no hace falta @Column
        private String imagen;

        private String denominacion;

        @Column(name = "fecha_creacion", nullable = false)
        @DateTimeFormat(pattern = "yyyy/MM/dd")
        private LocalDate fechaCreacion;

        private Long altura;

        private String historia;

        private boolean deleted = Boolean.FALSE;
        //no hago relacion ManyToOne
        // @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        // @JoinColumn(name = "pais_id")
        // private PaisEntity pais;

        //en esta entidad (objeto mas grande pais que icono) se engloba a icono,
              //al crear un pais creo un pais le puedo pasar una lista de iconos y se crea la relacion manytomany, pero
        //cuando creo un icono no le puedo pasar una lista de paises para que los cree,
        //tiene mas sentido crear un pais con sus iconos, que al crear iconos
        //el mappedBy le dice a Hibernate dónde encontrar la configuración para JoinColumn.
        @ManyToMany(mappedBy = "icons", cascade = CascadeType.ALL)//la definicion esta el objeto en icons de la otra entidad PaisEntity
        private List<PaisEntity> paises = new ArrayList<>();//importo la lista de paises y creo objeto paises de PaisEntity
        //importo lista de PaisEntity y creo objeto paises de tipo de datos PaisEntity con arraylist
        // Add and remove paises
        //metodos para agregar y remover paises, por ahora ignorar

        public void addPais(PaisEntity pais) {
            this.paises.add(pais);
        }

        public void removePais(PaisEntity pais) {
            this.paises.remove(pais);
        }
    }



