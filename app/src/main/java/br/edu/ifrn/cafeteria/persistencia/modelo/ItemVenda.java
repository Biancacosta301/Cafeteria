package br.edu.ifrn.cafeteria.persistencia.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_venda")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ItemVenda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "venda_id") private Venda venda;
    @ManyToOne @JoinColumn(name = "produto_id") private Produto produto;

    private Integer quantidade;
    private BigDecimal precoUnitario;

    public BigDecimal getValorTotalItem() {
        return precoUnitario.multiply(new BigDecimal(quantidade));
    }
}