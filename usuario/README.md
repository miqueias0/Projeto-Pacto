# API de Usuário

## Obter Usuário por ID

### Requisição
- **Método:** GET
- **Endpoint:** `/api/usuario/obterPorId`
- **Headers:** `sec` (Token de Autenticação)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo os detalhes do usuário

## Obter Usuário por ID (com parâmetro)

### Requisição
- **Método:** GET
- **Endpoint:** `/api/usuario/obterPorIdParam`
- **Headers:** `sec` (Token de Autenticação)
- **Parâmetros da Consulta:** `id` (ID do Usuário)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo os detalhes do usuário

## Manter Usuário (Registrar/Atualizar)

### Requisição
- **Método:** POST
- **Endpoint:** `/api/usuario/manter`
- **Headers:** `sec` (Token de Autenticação)
- **Corpo da Requisição:** JSON contendo os detalhes do usuário a ser registrado ou atualizado

### Resposta
- **Status de Sucesso:** 201 Created
- **Corpo da Resposta:** JSON contendo o token de autenticação para o usuário recém-criado ou atualizado

## Alterar Usuário

### Requisição
- **Método:** POST
- **Endpoint:** `/api/usuario/alterarUsuario`
- **Headers:** `sec` (Token de Autenticação)
- **Corpo da Requisição:** JSON contendo os detalhes do usuário a ser alterado

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo os detalhes do usuário após a alteração

## Login

### Requisição
- **Método:** POST
- **Endpoint:** `/api/usuario/login`
- **Headers:** `sec` (Token de Autenticação)
- **Corpo da Requisição:** JSON contendo os detalhes do login

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo o token de autenticação para o usuário logado

## Login com Token

### Requisição
- **Método:** GET
- **Endpoint:** `/api/usuario/loginComToken`
- **Headers:** `sec` (Token de Autenticação)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo o token de autenticação atualizado

---

## Modelos JSON

### UsuarioRecord
```json
{
  "nomeUsuario": "Nome do Usuário",
  "email": "Endereço de Email",
  "telefone": "Número de Telefone",
  "senha": "Senha do Usuário",
  "tipoUsuario": "Tipo do Usuário"
}
```

### TokenRecord
```json
{
  "token": "Token de Autenticação"
}
```

### UsuarioRecord
```json
{
  "usuario": {
    "email": "Endereço de Email",
    "senha": "Senha do Usuário"
  },
  "manterLogado": "Boolean para manter o token por mais tempo"
}
```