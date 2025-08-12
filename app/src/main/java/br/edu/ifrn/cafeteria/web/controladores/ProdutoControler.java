package br.edu.ifrn.cafeteria.web.controladores;

import java.util.List; // Para trabalhar com listas de objetos
import java.time.LocalDateTime; // Para manipular datas e horas
import java.math.BigDecimal; // Para lidar com valores monetários

import org.springframework.beans.factory.annotation.Autowired; // Para injeção de dependências
import org.springframework.stereotype.Controller; // Marca a classe como um controlador Spring MVC
import org.springframework.ui.Model; // Para passar dados do controlador para a view
import org.springframework.validation.BindingResult; // Para capturar erros de validação do formulário
import org.springframework.web.bind.annotation.GetMapping; // Mapeia requisições HTTP GET
import org.springframework.web.bind.annotation.ModelAttribute; // Vincula um parâmetro do método a um atributo do modelo
import org.springframework.web.bind.annotation.PostMapping; // Mapeia requisições HTTP POST
import org.springframework.web.bind.annotation.RequestMapping; // Define o caminho base para todas as URLs deste controlador

import br.edu.ifrn.cafeteria.persistencia.modelo.Produto; // Importa a entidade Produto
import br.edu.ifrn.cafeteria.persistencia.repositorio.ProdutoRepository; // Importa o repositório de Produto
import jakarta.validation.Valid; // Para habilitar a validação de beans (JSR-303)

@Controller
@RequestMapping("/produtos") // Todas as requisições para /produtos serão tratadas por este controlador
public class ProdutoControler {

    @Autowired // Injeta uma instância de ProdutoRepository automaticamente
    private ProdutoRepository produtoRepository;

    // Método para listar todos os produtos
    // Acessível via GET em /produtos
    @GetMapping
    public String listar(Model model) {
        List<Produto> produtos = produtoRepository.findAll(); // Busca todos os produtos no banco de dados
        model.addAttribute("produtos", produtos); // Adiciona a lista de produtos ao modelo para ser exibida na view
        return "produtos/lista-produtos"; // Retorna o nome do template Thymeleaf (ex: src/main/resources/templates/produtos/lista-produtos.html)
    }

    // Método para exibir o formulário de cadastro de um novo produto
    // Acessível via GET em /produtos/novo
    @GetMapping("/novo")
    public String formulario(Model model) {
        model.addAttribute("produto", new Produto()); // Adiciona um novo objeto Produto vazio ao modelo para preencher o formulário
        return "produtos/formulario-produtos"; // Retorna o template do formulário de produto
    }

    // Método para salvar um produto (novo ou existente)
    // Acessível via POST em /produtos
    @PostMapping
    public String salvar(@Valid @ModelAttribute Produto produto, BindingResult result, Model model) {
        // Validação de duplicidade de nome do produto
        // Se o produto não tem ID (é um novo produto)
        if (produto.getId() == null) {
            if (produtoRepository.findByNome(produto.getNome()).isPresent()) {
                // Se um produto com o mesmo nome já existe, adiciona um erro ao campo 'nome'
                result.rejectValue("nome", "erro.duplicado", "Já existe um produto com esse nome.");
            }
        } else { // Se o produto já tem ID (é uma edição de produto existente)
            // Verifica se o nome já existe em outro produto (que não seja o próprio que está sendo editado)
            produtoRepository.findByNome(produto.getNome()).ifPresent(existingProduto -> {
                if (!existingProduto.getId().equals(produto.getId())) {
                    result.rejectValue("nome", "erro.duplicado", "Já existe outro produto com esse nome.");
                }
            });
        }

        // Se houver erros de validação (incluindo o de duplicidade)
        if (result.hasErrors()) {
            return "produtos/formulario-produtos"; // Retorna para o formulário para exibir os erros
        }

        // Define a data de cadastro se for um novo produto
        if (produto.getId() == null) {
            produto.setDataCadastro(LocalDateTime.now()); // Define a data/hora atual
        }

        produtoRepository.save(produto); // Salva o objeto Produto no banco de dados
        return "redirect:/produtos"; // Redireciona o navegador para a URL /produtos, que lista os produtos
    }
}
