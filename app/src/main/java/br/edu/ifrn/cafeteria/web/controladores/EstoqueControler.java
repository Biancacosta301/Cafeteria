package br.edu.ifrn.cafeteria.web.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.edu.ifrn.cafeteria.persistencia.modelo.*;
import br.edu.ifrn.cafeteria.persistencia.repositorio.*;

@Controller @RequestMapping("/estoque")
public class EstoqueControler {
    @Autowired private EstoqueRepository estoqueRepository;
    @Autowired private ProdutoRepository produtoRepository;

    @GetMapping
    public String listar(Model model) { model.addAttribute("itensEstoque", estoqueRepository.findAll()); return "estoque/lista-estoque"; }

    @GetMapping("/novo")
    public String formulario(Model model) {
        model.addAttribute("estoque", new Estoque());
        model.addAttribute("produtos", produtoRepository.findAll());
        return "estoque/formulario-estoque";
    }

    @PostMapping
    public String salvar(Estoque estoque) {
        // Ao registrar entrada no estoque, aumenta a quantidade disponível no Produto
        Produto p = estoque.getProduto();
        p.setQuantidadeEstoque(p.getQuantidadeEstoque() + estoque.getQuantidade());
        produtoRepository.save(p); // Atualiza produto
        estoqueRepository.save(estoque); // Salva log
        return "redirect:/estoque";
    }
    
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) { estoqueRepository.deleteById(id); return "redirect:/estoque"; }
}