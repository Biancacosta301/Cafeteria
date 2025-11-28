package br.edu.ifrn.cafeteria.persistencia.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String nome;
    @Column(nullable = false, unique = true) private String email;
    @Column(nullable = false) private String senha;
    private String cpf;
    private String telefone;
    private String tipo; // "VENDEDOR" ou "CLIENTE"
}