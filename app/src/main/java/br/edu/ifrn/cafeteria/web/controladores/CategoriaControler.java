package br.edu.ifrn.cafeteria.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.edu.ifrn.cafeteria.persistencia.modelo.Categoria;
import br.edu.ifrn.cafeteria.persistencia.repositorio.CategoriaRepository;

@Controller
@RequestMapping("/categorias")
public class CategoriaControler {

    @Autowired private CategoriaRepository categoriaRepository;

    // 1. LISTAR (Abre a página com a tabela)
    @GetMapping
    public String listar(Model model) {
        // Busca todas as categorias no banco e manda para o HTML
        model.addAttribute("categorias", categoriaRepository.findAll());
        
        // Retorna o nome do arquivo HTML (tem que estar em templates/categorias/lista-categorias.html)
        return "categorias/lista-categorias";
    }

    // 2. NOVA (Abre o formulário vazio)
    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/formulario-categoria";
    }

    // 3. EDITAR (Abre o formulário com dados)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        // Busca a categoria pelo ID e manda para o formulário
        model.addAttribute("categoria", categoriaRepository.findById(id).get());
        return "categorias/formulario-categoria";
    }

    // 4. SALVAR (Recebe os dados do formulário e grava no banco)
    @PostMapping
    public String salvar(Categoria categoria) {
        categoriaRepository.save(categoria);
        return "redirect:/categorias"; // Volta para a lista
    }

    // 5. EXCLUIR
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        try {
            categoriaRepository.deleteById(id);
        } catch (Exception e) {
            // Se der erro (ex: tem produtos vinculados), volta com aviso
            return "redirect:/categorias?erro=tem-produtos";
        }
        return "redirect:/categorias";
    }
}