# Caso de Uso: Registrar Pedido

| Atributo | Descrição |
| :--- | :--- |
| **Nome** | Registrar Pedido de Venda |
| **ID** | CU.003 |
| **Objetivo** | Permitir que o Atendente registre de forma rápida e precisa os itens solicitados pelo cliente, calculando o total da venda. |
| **Ator Principal** | Atendente |
| **Pré-condição** | O Atendente deve estar logado no sistema. O cardápio deve estar cadastrado. |
| **Pós-condição** | Uma nova Venda é registrada no sistema. O estoque dos insumos/produtos vendidos é atualizado. |
| **Gatilho** | O cliente faz um pedido no balcão. |

## Fluxo Principal

| Passo | Ação do Ator | Resposta do Sistema |
| :--- | :--- | :--- |
| 1 | O Atendente acessa a tela de "Ponto de Venda (PDV)". | O Sistema exibe o cardápio e uma área de carrinho/pedido vazia. |
| 2 | O Atendente seleciona os itens e as quantidades solicitadas pelo cliente. | O Sistema adiciona os itens ao carrinho e recalcula o subtotal em tempo real. |
| 3 | O Atendente finaliza a seleção e clica em "Finalizar Pedido". | O Sistema exibe a tela de pagamento, mostrando o total a pagar. |
| 4 | O Atendente informa o método de pagamento (dinheiro, cartão, etc.) e o valor recebido. | |
| 5 | O Atendente clica em "Confirmar Venda". | O Sistema valida o pagamento. |
| 6 | | O Sistema: a) Registra a Venda no banco de dados. b) **(Regra de Negócio)** Dá baixa automática no estoque dos insumos/produtos vendidos (CU.002). c) **(Regra de Negócio)** Calcula e registra os pontos de fidelidade para o cliente (se identificado). |
| 7 | | O Sistema exibe uma mensagem de sucesso, imprime o comprovante e limpa o carrinho para o próximo pedido. |

## Fluxos Alternativos

### A1: Aplicação de Desconto/Cupom (Passo 3)

| Passo | Ação do Ator | Resposta do Sistema |
| :--- | :--- | :--- |
| 3a | O Atendente insere um código de cupom ou aplica um desconto manual. | O Sistema valida o cupom/desconto. |
| 3b | | O Sistema recalcula o total da venda e prossegue para o Passo 4 do Fluxo Principal. |

### A2: Identificação do Cliente (Passo 3)

| Passo | Ação do Ator | Resposta do Sistema |
| :--- | :--- | :--- |
| 3c | O Atendente busca e seleciona o cliente (por nome, telefone ou fidelidade). | O Sistema associa o cliente à venda e prossegue para o Passo 4 do Fluxo Principal. |

## Fluxo de Exceção

### E1: Falta de Estoque (Passo 6)

| Passo | Ação do Ator | Resposta do Sistema |
| :--- | :--- | :--- |
| 6a | | O Sistema detecta que um ou mais insumos necessários para o preparo de um item do pedido estão com saldo insuficiente (CU.002). |
| 6b | | O Sistema cancela a baixa de estoque e exibe uma mensagem de erro ao Atendente ("Erro: Estoque insuficiente para [Nome do Item]. Venda não finalizada."). |
| 6c | O Atendente informa o cliente sobre a indisponibilidade e remove o item do pedido (retorna ao Passo 2 do Fluxo Principal). | |
