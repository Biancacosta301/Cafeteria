package br.edu.ifrn.cafeteria.persistencia.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifrn.cafeteria.persistencia.modelo.Venda;
import java.util.List;
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByUsuarioIdOrderByDataHoraDesc(Long usuarioId);
}