# Documentação da API de Candidatura

## Obter Candidatura por ID

### Requisição
- **Método:** GET
- **Endpoint:** `/candidatura/obterPorId`
- **Headers:** `sec` (Token de Autenticação)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo os detalhes da candidatura

## Obter Lista de Candidaturas por ID do Candidato

### Requisição
- **Método:** GET
- **Endpoint:** `/candidatura/obterListaPorCandidatoId`
- **Headers:** `sec` (Token de Autenticação)
- **Parâmetros da Consulta:** `candidatoId` (ID do Candidato)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo a lista de candidaturas do candidato

## Obter Lista de Candidaturas por ID da Vaga

### Requisição
- **Método:** GET
- **Endpoint:** `/candidatura/obterListaPorVagaId`
- **Headers:** `sec` (Token de Autenticação)
- **Parâmetros da Consulta:** `vagaId` (ID da Vaga)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo a lista de candidaturas para a vaga

## Manter Candidatura

### Requisição
- **Método:** POST
- **Endpoint:** `/candidatura/manter`
- **Headers:** `sec` (Token de Autenticação)
- **Corpo da Requisição:** JSON contendo detalhes da candidatura a ser mantida

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo os detalhes da candidatura recém-criada ou atualizada

## Excluir Candidatura

### Requisição
- **Método:** POST
- **Endpoint:** `/candidatura/excluir`
- **Headers:** `sec` (Token de Autenticação)
- **Corpo da Requisição:** JSON contendo detalhes da candidatura a ser excluída

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** Nenhum conteúdo no corpo

---

## Modelos JSON

### CandidaturaRecord
```json
{
  "candidatoId": "ID do Candidato",
  "vagaId": "ID da Vaga"
}
```

### MensagemRetorno
```json
{
  "mensagem": "Mensagem de retorno"
}

```
