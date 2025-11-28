package br.edu.ifrn.cafeteria.web.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.edu.ifrn.cafeteria.persistencia.modelo.Usuario;
import br.edu.ifrn.cafeteria.persistencia.repositorio.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired private UsuarioRepository usuarioRepository;

    @GetMapping("/login") public String loginPage() { return "login"; }

    @PostMapping("/login")
    public String logar(String email, String senha, HttpSession session) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(senha)) {
            session.setAttribute("usuarioLogado", usuarioOpt.get());
            return "redirect:/";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); return "redirect:/";
    }
}