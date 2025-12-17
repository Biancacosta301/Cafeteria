# Caso de Uso: Cadastrar Produto

| Atributo | Descrição |
| :--- | :--- |
| **Nome** | Cadastrar Produto |
| **ID** | CU.001 |
| **Objetivo** | Permitir que o Gerente adicione um novo item ao cardápio da cafeteria ou atualize um item existente. |
| **Ator Principal** | Gerente |
| **Pré-condição** | O Gerente deve estar logado no sistema. |
| **Pós-condição** | Um novo produto é registrado no banco de dados ou um produto existente é atualizado. |
| **Gatilho** | O Gerente acessa a seção de "Gestão de Produtos" e clica em "Novo Produto" ou "Editar". |

## Fluxo Principal

| Passo | Ação do Ator | Resposta do Sistema |
| :--- | :--- | :--- |
| 1 | O Gerente acessa a tela de cadastro/edição de produtos. | O Sistema exibe o formulário de produto (vazio para novo, preenchido para edição). |
| 2 | O Gerente preenche os campos obrigatórios: Nome, Descrição, Preço de Venda e Categoria. | |
| 3 | O Gerente anexa uma imagem do produto (opcional). | |
| 4 | O Gerente clica em "Salvar". | O Sistema valida os dados. |
| 5 | | O Sistema registra/atualiza o produto no banco de dados. |
| 6 | | O Sistema exibe uma mensagem de sucesso ("Produto salvo com sucesso") e retorna à lista de produtos. |

## Fluxos Alternativos

### A1: Dados Inválidos (Passo 4)

| Passo | Ação do Ator | Resposta do Sistema |
| :--- | :--- | :--- |
| 4a | | O Sistema detecta que um campo obrigatório está vazio ou o preço é inválido (ex: negativo). |
| 4b | | O Sistema exibe uma mensagem de erro ao lado do campo inválido e mantém o Gerente na tela de cadastro. |
| 4c | O Gerente corrige os dados e clica em "Salvar" (retorna ao Passo 4 do Fluxo Principal). | |

### A2: Produto Duplicado (Passo 5)

| Passo | Ação do Ator | Resposta do Sistema |
| :--- | :--- | :--- |
| 5a | | O Sistema detecta que o nome do produto já existe e não é uma edição. |
| 5b | | O Sistema exibe uma mensagem de erro ("Já existe um produto com este nome. Deseja editar o existente?"). |
| 5c | O Gerente escolhe editar o existente (retorna ao Passo 1, carregando os dados do produto duplicado) ou altera o nome (retorna ao Passo 4 do Fluxo Principal). | |
