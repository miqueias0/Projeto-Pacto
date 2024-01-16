# API de Notificação

## Obter Lista de Notificações por ID da Vaga

### Requisição
- **Método:** GET
- **Endpoint:** `/notificacao/obterListaPorVagaId`
- **Headers:** `sec` (Token de Autenticação)
- **Parâmetros da Consulta:** `vagaId` (ID da Vaga)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo a lista de notificações relacionadas à vaga

## Obter Lista de Notificações por Contratante

### Requisição
- **Método:** GET
- **Endpoint:** `/notificacao/obterListaPorContratante`
- **Headers:** `sec` (Token de Autenticação)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo a lista de notificações não lidas para o contratante

## Obter Lista de Notificações por Candidato

### Requisição
- **Método:** GET
- **Endpoint:** `/notificacao/obterListaPorCandidato`
- **Headers:** `sec` (Token de Autenticação)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo a lista de notificações não lidas para o candidato

## Manter Notificação

### Requisição
- **Método:** POST
- **Endpoint:** `/notificacao/manter`
- **Headers:** `sec` (Token de Autenticação)
- **Corpo da Requisição:** JSON contendo os detalhes da candidatura relacionada à notificação

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo uma mensagem de sucesso

## Alterar Status de Notificação

### Requisição
- **Método:** POST
- **Endpoint:** `/notificacao/alterar`
- **Headers:** `sec` (Token de Autenticação)
- **Corpo da Requisição:** JSON contendo o ID da notificação a ser alterada

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo uma mensagem de sucesso

## Excluir Notificação

### Requisição
- **Método:** POST
- **Endpoint:** `/notificacao/excluir`
- **Headers:** `sec` (Token de Autenticação)
- **Corpo da Requisição:** JSON contendo o ID da notificação a ser excluída

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo uma mensagem de sucesso

---

## Modelos JSON

### NotificacaoRecord
```json
{
  "mensagem": "Mensagem da Notificação",
  "id": "ID da Notificação",
  "vagaId": "ID da Vaga"
}
```

### MensagemRetorno
```json
{
  "mensagem": "Mensagem de retorno"
}
```