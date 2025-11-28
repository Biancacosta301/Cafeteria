package br.edu.ifrn.cafeteria.web.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import br.edu.ifrn.cafeteria.persistencia.modelo.*;
import br.edu.ifrn.cafeteria.persistencia.repositorio.*;

@Controller
// Nota: Sem @RequestMapping aqui para os links ficarem na raiz
public class VendasControler {

    @Autowired private ProdutoRepository produtoRepository;
    @Autowired private VendaRepository vendaRepository;

    // Carrinho
    @GetMapping("/carrinho")
    public String carrinho(Model model, HttpSession session) {
        Venda venda = (Venda) session.getAttribute("vendaAtual");
        if (venda == null) {
            venda = new Venda();
            session.setAttribute("vendaAtual", venda);
        }
        model.addAttribute("venda", venda);
        return "vendas/carrinho";
    }

    // Adicionar Item
    @PostMapping("/carrinho/adicionar")
    public String adicionar(@RequestParam Integer produtoId, @RequestParam(defaultValue="1") Integer quantidade, HttpSession session, RedirectAttributes redirect) {
        Venda venda = (Venda) session.getAttribute("vendaAtual");
        if (venda == null) { venda = new Venda(); session.setAttribute("vendaAtual", venda); }

        Optional<Produto> prodOpt = produtoRepository.findById(produtoId);
        if (prodOpt.isPresent()) {
            Produto p = prodOpt.get();
            ItemVenda item = new ItemVenda();
            item.setProduto(p); 
            item.setQuantidade(quantidade); 
            item.setPrecoUnitario(p.getPreco());
            
            venda.adicionarItem(item);
            redirect.addFlashAttribute("sucesso", "Adicionado com sucesso!");
        }
        return "redirect:/carrinho";
    }

    // Finalizar Compra
    @PostMapping("/carrinho/finalizar")
    public String finalizar(HttpSession session, RedirectAttributes redirect) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/login";

        Venda venda = (Venda) session.getAttribute("vendaAtual");
        if (venda != null && !venda.getItens().isEmpty()) {
            venda.setUsuario(usuario);
            vendaRepository.save(venda);
            
            // Baixa no Estoque
            for (ItemVenda item : venda.getItens()) {
                Produto p = item.getProduto();
                if (p.getQuantidadeEstoque() == null) p.setQuantidadeEstoque(0);
                p.setQuantidadeEstoque(p.getQuantidadeEstoque() - item.getQuantidade());
                produtoRepository.save(p);
            }
            
            session.removeAttribute("vendaAtual");
            return "redirect:/meus-pedidos";
        }
        return "redirect:/carrinho";
    }

    // ✅ MEUS PEDIDOS (Isso faz o link funcionar)
    @GetMapping("/meus-pedidos")
    public String meusPedidos(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/login";

        // Busca histórico de compras
        model.addAttribute("vendas", vendaRepository.findByUsuarioIdOrderByDataHoraDesc(usuario.getId()));
        return "usuarios/meus-pedidos";
    }
}