package br.edu.ifrn.cafeteria.persistencia.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "estoque")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Estoque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    private Integer quantidade;
    private String unidade;
    private LocalDateTime dataEntrada = LocalDateTime.now();
}