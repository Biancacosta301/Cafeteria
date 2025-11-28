package br.edu.ifrn.cafeteria.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import br.edu.ifrn.cafeteria.persistencia.repositorio.ProdutoRepository;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired private ProdutoRepository produtoRepository;

    @GetMapping("/")
    public String index(Model model) {
        // Pega apenas os 4 primeiros produtos para serem os "Destaques"
        model.addAttribute("destaques", produtoRepository.findAll().stream().limit(4).collect(Collectors.toList()));
        return "index";
    }
}