package com.example.teste.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {

    //@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String login;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_roles",joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();

}
