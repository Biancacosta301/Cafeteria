package br.edu.ifrn.cafeteria.persistencia.repositorio;

import java.util.Optional; // Importa Optional para métodos de busca que podem não retornar um resultado

import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface base do Spring Data JPA
import br.edu.ifrn.cafeteria.persistencia.modelo.Produto; // Importa a entidade Produto

// Interface que oferece as operações CRUD (Create, Read, Update, Delete) para a entidade Produto
// JpaRepository<Entidade, TipoDoId>
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    // Método para buscar um produto pelo nome.
    // Usado para validação de duplicidade, garantindo que não haja produtos com o mesmo nome.
    Optional<Produto> findByNome(String nome);
}
