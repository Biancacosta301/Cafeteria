package br.edu.ifrn.cafeteria.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifrn.cafeteria.persistencia.modelo.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> findByNome(String nome);
    
    // Método essencial para o filtro de categorias no cardápio
    List<Produto> findByCategoriaId(Long categoriaId);
}