package br.edu.ifrn.cafeteria.persistencia.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100) 
    private String nome;

    @Column(columnDefinition = "TEXT") 
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2) 
    private BigDecimal preco;
    
    // TEXT permite URLs longas
    @Column(columnDefinition = "TEXT")
    private String imagem;

    @Column(name = "quantidade_estoque") 
    private Integer quantidadeEstoque = 0;

    @ManyToOne 
    @JoinColumn(name = "categoria_id") 
    private Categoria categoria;

    private Boolean ativo = true;
    private LocalDateTime dataCadastro = LocalDateTime.now();
}