package com.icons.icons.lab.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity   //defino clase PaisEntity como tabla
@Table(name = "pais")    //cambio nombre tabla a "pais"
@Getter
@Setter

public class PaisEntity {

    @Id                                                          //defino llave primaria
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //como voy a generar los Id) defino una estrategia que es secuence
    //especifica que se genere el id automáticamente

    private Long id;  //cuando el atributo id, se llama igual que la columna de la base de datos, no hace falta @Column

    private String imagen;

    private String denominacion;

    @Column(name = "cant_habitantes")   //para base datos nombre columna // Declara el nombre de la columna de la tabla (la renombra)
    private Long cantidadHabitantes;   //atributo en java

    private Long superficie;   //m2


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)  //defino relacion manyToOne (muchos paises estan en
    //un continente, cada continente tiene muchos paises,estoy parado en clase Pais
    //defino un fetchType Eager (la inicializacion es tipo temprana) cuando pida dato tipo pais, viene con sus continentes
    //CascadeType.ALL) que todas las operaciones sean consecuentes con el continente, si hago delete, aplica sobre continente tambien

    @JoinColumn(name = "continente_id", insertable = false, updatable = false)  //La columna de unión se declara con la
    // anotación @JoinColumn que se parece a la anotación @Column pero para relaciones, contiene la llave primaria externa
    //cambia el nombre a la columna ContinenteEntity de la otra clase por continente_id para la base de datos
    //combina las columnas entre una o más tablas en una base de datos, lo hace atraves del atributo continente_id
    //insertable = false, updatable = false porque se usa solo para obtener informacion
    //la responsabilidad de crear o actualizar continente_id no esta  en esta entidad
     private ContinenteEntity continente;    //creo atributo de la clase ContinenteEntity llamado continente
    // es solo para buscar informacion, va a traer un objeto de tipo continente, no existe para crear paisees, por el Eager

      //define la columna continenteId en la base de datos
      //para guardar y actualizar donde tengo el id
        @Column(name = "continente_id", nullable = false) //mi columna continente_id en base de datos no puede ser nula
    private Long continenteId;   //va a tener un identificador de continente
    //cuando quiera crear un pais, creo un id de continente y se crea
    //hago un join con la tabla continente para obtener el continente entero. es para guardar y actualizar


    //relacion n a n entre paises e iconos
    //en relacion ManyToMany se anota en ambas entidades
    //en esta entidad (objeto mas grande pais que icono) se engloba a icono,
    //aplico la definicion many to many de este lado, porque es mas grande pais que icono geografico
    //al crear un pais creo un pais le puedo pasar una lista de iconosy crea iconos
    // y se crea la relacion manytomany, pero
    //cuando creo un icono no le puedo pasar una lista de paises para que los cree,
    //tiene mas sentido crear un pais con sus iconos, que al crear eico
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,  //para insertar un nuevo objeto en la base de datos
                    CascadeType.MERGE// para actualizar un objeto existente en la base de datos
            })
    @JoinTable(     // se usa para anotar la tabla asociada, para relaciones manytomany, crea tabla intermedia
            name = "icon_pais",  //la tabla intermedia creada se lama icon_pais
            joinColumns = @JoinColumn(name = "pais_id"),// clave externa de la relacion, como sellama la columna con el id, desde PaisEntity
            inverseJoinColumns = @JoinColumn(name = "icon_id")) //otra columna de la clave externa, desde iconEntity se llamara icon_id

    private Set<IconEntity> icons= new HashSet<>(); ; // en colecciones HashSet no permite objetos repetidos

    // <nombre de la clase> nombre objeto, creo lista tipo HashSet, no repite y no esta ordenada
    //importo Set de la clase IconEntity, creo objeto icons con constructor
    //defino un set llamado icons y creo un nuevo HashSet
    //

    @Override  //lo vemos mas adelante, redifinimos relacion de paises
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PaisEntity other = (PaisEntity) obj;
        return other.id == this.id;
    }
}






