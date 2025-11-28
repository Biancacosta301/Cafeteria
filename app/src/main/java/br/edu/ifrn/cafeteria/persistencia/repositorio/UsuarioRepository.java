package br.edu.ifrn.cafeteria.persistencia.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifrn.cafeteria.persistencia.modelo.Usuario;
import java.util.Optional;
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}