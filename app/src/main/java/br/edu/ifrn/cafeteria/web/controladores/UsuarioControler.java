package br.edu.ifrn.cafeteria.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.edu.ifrn.cafeteria.persistencia.modelo.Usuario;
import br.edu.ifrn.cafeteria.persistencia.repositorio.UsuarioRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuarios") // ⚠️ Importante: Define o prefixo da URL
public class UsuarioControler {

    @Autowired private UsuarioRepository usuarioRepository;

    // Cadastro
    @GetMapping("/novo")
    public String formulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario-usuario";
    }

    // Salvar
    @PostMapping("/salvar")
    public String salvar(Usuario usuario) {
        if (usuario.getTipo() == null) {
            usuario.setTipo("CLIENTE");
        }
        usuarioRepository.save(usuario);
        return "redirect:/login";
    }

    // ✅ PERFIL (Isso faz o link funcionar)
    @GetMapping("/perfil") 
    public String perfil(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/login";
        
        // Atualiza os dados do usuário na tela
        model.addAttribute("usuario", usuario);
        return "usuarios/perfil"; 
    }
}