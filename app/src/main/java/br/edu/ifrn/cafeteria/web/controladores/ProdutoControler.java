package br.edu.ifrn.cafeteria.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.edu.ifrn.cafeteria.persistencia.modelo.Produto;
import br.edu.ifrn.cafeteria.persistencia.repositorio.ProdutoRepository;
import br.edu.ifrn.cafeteria.persistencia.repositorio.CategoriaRepository;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoControler {

    @Autowired private ProdutoRepository produtoRepository;
    @Autowired private CategoriaRepository categoriaRepository;

    // --- MÉTODOS PÚBLICOS ---
    @GetMapping
    public String cardapio(@RequestParam(required = false) Long categoriaId, Model model) {
        List<Produto> produtos = (categoriaId != null) ? 
            produtoRepository.findByCategoriaId(categoriaId) : produtoRepository.findAll();
        
        model.addAttribute("produtos", produtos);
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("categoriaAtiva", categoriaId);
        return "produtos/cardapio";
    }

    // --- MÉTODOS ADMIN ---
    @GetMapping("/admin")
    public String gerenciar(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "produtos/lista-produtos";
    }

    @GetMapping("/novo")
    public String formulario(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "produtos/formulario-produtos";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto inválido"));
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "produtos/formulario-produtos";
    }

    // SALVAR (Simplificado: O próprio Spring pega o link do formulário)
    @PostMapping
    public String salvar(Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos/admin";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id) {
        produtoRepository.deleteById(id);
        return "redirect:/produtos/admin";
    }
}