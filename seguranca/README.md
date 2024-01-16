# API de Segurança

## Validar Token

### Requisição
- **Método:** GET
- **Endpoint:** `/seguranca/validarToken`
- **Headers:** `sec` (Token de Autenticação)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo os detalhes da autenticação

## Criar Token

### Requisição
- **Método:** GET
- **Endpoint:** `/seguranca/criarToken`
- **Parâmetros da Consulta:**
    - `id` (Identificador do Usuário)
    - `tempoToken` (Tempo de Vida do Token em Minutos)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo o token criado

## Atualizar Token

### Requisição
- **Método:** GET
- **Endpoint:** `/seguranca/atualizarToken`
- **Headers:** `sec` (Token de Autenticação)

### Resposta
- **Status de Sucesso:** 200 OK
- **Corpo da Resposta:** JSON contendo o novo token atualizado

---

## Modelos JSON

### AutenticacaoRecord
```json
{
  "identificador": "Identificador do Usuário",
  "token": "Token Gerado"
}
```

### TokenRecord
```json
{
  "token": "Token Gerado"
}
```

# Documentação da classe Seguranca

## Visão geral

A classe Seguranca é responsável por criar, validar e gerenciar tokens JWT (JSON Web Tokens) para autenticação e autorização de usuários. Ela utiliza a biblioteca JOSE4J para realizar as operações de assinatura, criptografia e descriptografia dos tokens.

## Métodos principais

criarToken(Autenticacao autenticacao, Integer tempo_expiracao_minutos): Cria um token JWT a partir de um objeto Autenticacao. O token é assinado com a chave privada e criptografado com a chave pública. O tempo de expiração do token pode ser opcionalmente especificado em minutos.
validarToken(String token): Valida um token JWT recebido. Verifica a assinatura, a criptografia, o emissor, o sujeito e o tempo de expiração do token. Retorna um objeto Autenticacao contendo as informações do token se a validação for bem-sucedida.
## Outros métodos

gerarChave(String nomeArquivo): Gera uma chave aleatória e a armazena em um arquivo.
absolutePath(String nomeArquivo): Obtém o caminho absoluto de um arquivo.
gerarValor(Integer limit): Gera um valor aleatório dentro de um limite especificado.
escolherCatactere(String caractere): Escolhe um caractere aleatório de uma string de caracteres.
gerarChaveRecursivo(Integer maxBit, Integer minBit, String chave, String chaveFinal): Gera uma chave aleatória com um tamanho específico em bits.
## Observações

A classe utiliza chaves privadas e públicas para assinatura e criptografia dos tokens. É importante manter essas chaves seguras e protegidas.
Os tokens podem ser configurados para expirar após um determinado tempo.
A classe possui métodos auxiliares para gerar chaves aleatórias e obter caminhos absolutos de arquivos.