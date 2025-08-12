package br.edu.ifrn.cafeteria.persistencia.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal; // Para o tipo DECIMAL(10, 2)
import java.time.LocalDateTime; // Para o tipo TIMESTAMP

@Entity
@Table(name = "produtos") // Mapeia para a tabela 'produtos' no banco de dados
@Getter // Gera automaticamente os métodos getters para todos os campos
@Setter // Gera automaticamente os métodos setters para todos os campos
@AllArgsConstructor // Gera um construtor com todos os argumentos
@NoArgsConstructor // Gera um construtor sem argumentos
public class Produto {

    @Id // Marca este campo como a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a geração automática de valores para o ID
    @Column(name = "id") // Mapeia este campo para a coluna 'id' na tabela 'produtos'
    private Integer id; // Tipo Integer para corresponder ao INT do SQL

    @Column(name = "nome", nullable = false, length = 100) // Mapeia para a coluna 'nome', não nula, com 100 caracteres
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT") // Mapeia para a coluna 'descricao' com tipo TEXT
    private String descricao;

    @Column(name = "preco", nullable = false, precision = 10, scale = 2) // Mapeia para 'preco', não nulo, DECIMAL(10, 2)
    private BigDecimal preco; // Usando BigDecimal para precisão monetária

    @Column(name = "categoria", length = 50) // Mapeia para a coluna 'categoria', com 50 caracteres
    private String categoria;

    @Column(name = "ativo", nullable = false) // Mapeia para a coluna 'ativo', não nula
    private Boolean ativo = true; // Indica se o produto está ativo, com valor padrão TRUE

    @Column(name = "data_cadastro") // Mapeia para a coluna 'data_cadastro'
    private LocalDateTime dataCadastro; // Usando LocalDateTime para o timestamp

}
