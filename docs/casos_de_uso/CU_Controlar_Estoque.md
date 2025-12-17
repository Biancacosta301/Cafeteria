# Caso de Uso: Controlar Estoque

| Atributo | Descrição |
| :--- | :--- |
| **Nome** | Controlar Estoque (Entrada/Saída de Insumos) |
| **ID** | CU.002 |
| **Objetivo** | Permitir que o Estoquista registre a movimentação (entrada por compra ou saída por uso/perda) de insumos e produtos. |
| **Ator Principal** | Estoquista |
| **Pré-condição** | O Estoquista deve estar logado no sistema. O insumo/produto deve estar previamente cadastrado. |
| **Pós-condição** | O saldo do item no estoque é atualizado e um registro de movimentação é criado. |
| **Gatilho** | Chegada de mercadoria (Entrada) ou uso/descarte de insumo (Saída). |

## Fluxo Principal (Entrada de Estoque)

| Passo | Ação do Ator | Resposta do Sistema |
| :--- | :--- | :--- |
| 1 | O Estoquista acessa a tela de "Movimentação de Estoque" e seleciona "Entrada". | O Sistema exibe o formulário de entrada. |
| 2 | O Estoquista informa o insumo/produto (por nome ou código), a quantidade recebida e o preço de custo unitário. | |
| 3 | O Estoquista clica em "Registrar Entrada". | O Sistema valida os dados. |
| 4 | | O Sistema: a) Adiciona a quantidade ao saldo atual do item. b) Cria um registro de movimentação com data, hora, tipo (Entrada), quantidade e custo. c) **(Regra de Negócio)** Atualiza o Custo Médio Ponderado do item, se aplicável. |
| 5 | | O Sistema exibe uma mensagem de sucesso ("Entrada de estoque registrada com sucesso"). |

## Fluxo Alternativo (Saída de Estoque)

| Passo | Ação do Ator | Resposta do Sistema |
| :--- | :--- | :--- |
| 1 | O Estoquista acessa a tela de "Movimentação de Estoque" e seleciona "Saída". | O Sistema exibe o formulário de saída. |
| 2 | O Estoquista informa o insumo/produto, a quantidade que está sendo retirada e o motivo (ex: uso na produção, perda/descarte). | |
| 3 | O Estoquista clica em "Registrar Saída". | O Sistema valida os dados. |
| 4 | | O Sistema: a) Subtrai a quantidade do saldo atual do item. b) Cria um registro de movimentação com data, hora, tipo (Saída), quantidade e motivo. |
| 5 | | O Sistema exibe uma mensagem de sucesso ("Saída de estoque registrada com sucesso"). |

## Fluxo de Exceção

### E1: Saldo Insuficiente (Passo 3 - Saída)

| Passo | Ação do Ator | Resposta do Sistema |
| :--- | :--- | :--- |
| 3a | | O Sistema detecta que a quantidade informada para saída é maior que o saldo atual em estoque. |
| 3b | | O Sistema exibe uma mensagem de erro ("Erro: Saldo insuficiente. Saldo atual: [X]"). |
| 3c | O Estoquista corrige a quantidade (retorna ao Passo 2 do Fluxo Alternativo) ou cancela a operação. | |
