package br.edu.ifrn.cafeteria.persistencia.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Venda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora = LocalDateTime.now();
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    public void adicionarItem(ItemVenda item) {
        item.setVenda(this);
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getValorTotalItem());
    }
}