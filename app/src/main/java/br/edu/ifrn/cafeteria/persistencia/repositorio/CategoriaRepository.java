package br.edu.ifrn.cafeteria.persistencia.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifrn.cafeteria.persistencia.modelo.Categoria;
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}