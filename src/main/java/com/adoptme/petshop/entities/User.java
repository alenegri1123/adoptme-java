package com.adoptme.petshop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Table(name = "users")  //funcion de la base de datos
@NoArgsConstructor //constructor
@ToString
@EqualsAndHashCode
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //fetch?
    @Getter @Setter private List<Pet> pets;




}
