package br.edu.ifrn.cafeteria.persistencia.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifrn.cafeteria.persistencia.modelo.Estoque;
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {}