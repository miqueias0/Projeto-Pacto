# Serviço de Autenticação

## Descrição
Este serviço é responsável por realizar operações relacionadas à autenticação, como validar tokens, criar novos tokens e atualizar tokens existentes.

## Métodos

### `validarToken()`
Valida o token de autenticação.

#### Parâmetros
- Nenhum.

#### Retorno
- Objeto do tipo `AutenticacaoRecord` contendo informações sobre a autenticação.

#### Exceções
- `Exception` em caso de falha na validação do token.

### `criarToken(String id, Integer tempoToken)`
Cria um novo token de autenticação.

#### Parâmetros
- `id`: ID do usuário para o qual o token está sendo criado.
- `tempoToken`: Tempo de vida do token em segundos.

#### Retorno
- Objeto do tipo `TokenRecord` contendo o novo token.

#### Exceções
- `Exception` em caso de falha na criação do token.

### `atualizarToken()`
Atualiza o token de autenticação existente.

#### Parâmetros
- Nenhum.

#### Retorno
- Objeto do tipo `TokenRecord` contendo o token atualizado.

#### Exceções
- `Exception` em caso de falha na atualização do token.

## Configuração
O serviço utiliza um arquivo de propriedades chamado `comum.properties` para carregar informações de configuração, como o caminho da API de segurança.

### Propriedades
- `seguranca`: Caminho da API de segurança.

## Interceptador
O serviço utiliza um interceptador chamado `Inteceptor` para incluir informações de segurança nas requisições.

## Observações
- Certifique-se de que o arquivo `comum.properties` esteja devidamente configurado.
- Todas as operações lidam com exceções, as quais podem fornecer informações detalhadas sobre os erros.


# Serviço de Candidatura

## Descrição
Este serviço é responsável por realizar operações relacionadas a candidaturas, como obtenção por ID, listagem por candidato ou vaga, manutenção e exclusão.

## Métodos

### `obterPorId()`
Obtém uma candidatura pelo ID.

#### Parâmetros
- Nenhum.

#### Retorno
- Objeto do tipo `CandidaturaRecord` contendo informações sobre a candidatura.

#### Exceções
- `Exception` em caso de falha na obtenção da candidatura.

### `obterListaPorCandidatoId(String id)`
Obtém uma lista de candidaturas associadas a um candidato.

#### Parâmetros
- `id`: ID do candidato.

#### Retorno
- Lista de objetos do tipo `CandidaturaRecord` contendo informações sobre as candidaturas.

#### Exceções
- `Exception` em caso de falha na obtenção da lista de candidaturas.

### `obterListaPorVagaId(String id)`
Obtém uma lista de candidaturas associadas a uma vaga.

#### Parâmetros
- `id`: ID da vaga.

#### Retorno
- Lista de objetos do tipo `CandidaturaRecord` contendo informações sobre as candidaturas.

#### Exceções
- `Exception` em caso de falha na obtenção da lista de candidaturas.

### `manter(CandidaturaRecord candidaturaRecord)`
Realiza a manutenção de uma candidatura, seja criando uma nova ou atualizando uma existente.

#### Parâmetros
- `candidaturaRecord`: Objeto do tipo `CandidaturaRecord` contendo as informações da candidatura.

#### Retorno
- Objeto do tipo `CandidaturaRecord` contendo as informações atualizadas ou criadas.

#### Exceções
- `Exception` em caso de falha na manutenção da candidatura.

### `excluir(CandidaturaRecord candidaturaRecord)`
Exclui uma candidatura.

#### Parâmetros
- `candidaturaRecord`: Objeto do tipo `CandidaturaRecord` contendo as informações da candidatura a ser excluída.

#### Retorno
- Objeto do tipo `MensagemRetorno` indicando o sucesso da exclusão.

#### Exceções
- `Exception` em caso de falha na exclusão da candidatura.

## Configuração
O serviço utiliza um arquivo de propriedades chamado `comum.properties` para carregar informações de configuração, como o caminho da API de candidaturas.

### Propriedades
- `candidatura`: Caminho da API de candidaturas.

## Interceptador
O serviço utiliza um interceptador chamado `Inteceptor` para incluir informações de segurança nas requisições.

## Observações
- Certifique-se de que o arquivo `comum.properties` esteja devidamente configurado.
- Todas as operações lidam com exceções, as quais podem fornecer informações detalhadas sobre os erros.


# Serviço de Notificação

## Descrição
Este serviço é responsável por realizar operações relacionadas a notificações, como obtenção de uma lista por ID de vaga, manutenção e exclusão.

## Métodos

### `obterListaPorVagaId(String vagaId)`
Obtém uma lista de notificações associadas a uma vaga.

#### Parâmetros
- `vagaId`: ID da vaga.

#### Retorno
- Lista de objetos do tipo `NotificacaoRecord` contendo informações sobre as notificações.

#### Exceções
- Nenhuma.

### `manter(CandidaturaRecord candidaturaRecord)`
Realiza a manutenção de uma notificação, seja criando uma nova ou atualizando uma existente.

#### Parâmetros
- `candidaturaRecord`: Objeto do tipo `CandidaturaRecord` contendo as informações da notificação.

#### Retorno
- Mensagem de retorno indicando o sucesso da operação.

#### Exceções
- `Exception` em caso de falha na manutenção da notificação.

### `excluir(NotificacaoRecord notificacaoRecord)`
Exclui uma notificação.

#### Parâmetros
- `notificacaoRecord`: Objeto do tipo `NotificacaoRecord` contendo as informações da notificação a ser excluída.

#### Retorno
- Mensagem de retorno indicando o sucesso da operação.

#### Exceções
- `Exception` em caso de falha na exclusão da notificação.

## Configuração
O serviço utiliza um arquivo de propriedades chamado `comum.properties` para carregar informações de configuração, como o caminho da API de notificações.

### Propriedades
- `notificacao`: Caminho da API de notificações.

## Interceptador
O serviço utiliza um interceptador chamado `Inteceptor` para incluir informações de segurança nas requisições.

## Observações
- Certifique-se de que o arquivo `comum.properties` esteja devidamente configurado.
- Todas as operações lidam com exceções, as quais podem fornecer informações detalhadas sobre os erros.

# Serviço de Usuário

## Descrição
Este serviço é responsável por realizar operações relacionadas a usuários, como obtenção de informações por ID.

## Métodos

### `obterPorId()`
Obtém informações sobre o usuário autenticado.

#### Retorno
- Objeto do tipo `UsuarioRecord` contendo as informações do usuário.

#### Exceções
- `Exception` em caso de falha ao obter as informações do usuário.

### `obterPorId(String id)`
Obtém informações sobre um usuário específico por meio do seu ID.

#### Parâmetros
- `id`: ID do usuário a ser obtido.

#### Retorno
- Objeto do tipo `UsuarioRecord` contendo as informações do usuário.

#### Exceções
- `Exception` em caso de falha ao obter as informações do usuário.

## Configuração
O serviço utiliza um arquivo de propriedades chamado `comum.properties` para carregar informações de configuração, como o caminho da API de usuários.

### Propriedades
- `usuario`: Caminho da API de usuários.

## Interceptador
O serviço utiliza um interceptador chamado `Inteceptor` para incluir informações de segurança nas requisições.

## Observações
- Certifique-se de que o arquivo `comum.properties` esteja devidamente configurado
- Todas as operações lidam com exceções, as quais podem fornecer informações detalhadas sobre os erros.


# Serviço de Vaga

## Descrição
Este serviço é responsável por realizar operações relacionadas a vagas, como obtenção de informações por ID.

## Métodos

### `obterPorId(String id)`
Obtém informações sobre uma vaga com base no ID fornecido.

#### Parâmetros
- `id`: ID da vaga.

#### Retorno
- Objeto do tipo `VagaRecord` contendo informações sobre a vaga.

#### Exceções
- `Exception` em caso de falha na obtenção das informações da vaga.

## Configuração
O serviço utiliza um arquivo de propriedades chamado `comum.properties` para carregar informações de configuração, como o caminho da API de vagas.

### Propriedades
- `vaga`: Caminho da API de vagas.

## Interceptador
O serviço utiliza um interceptador chamado `Inteceptor` para incluir informações de segurança nas requisições.

## Observações
- Certifique-se de que o arquivo `comum.properties` esteja devidamente configurado.
- Todas as operações lidam com exceções, as quais podem fornecer informações detalhadas sobre os erros.
