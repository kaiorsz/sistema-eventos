# Projeto de Gerenciamento de Vendas

Este projeto consiste em um sistema de gerenciamento de vendas, desenvolvido em PostgreSQL, que inclui as tabelas de `produto`, `venda` e `venda_produto`.

## Como Rodar o Projeto

1. **Configuração do Banco de Dados:**
    - Certifique-se de ter o PostgreSQL instalado em seu ambiente.
    - Execute as seguintes consultas SQL em seu banco de dados para criar as tabelas:

```sql
CREATE TABLE evento (
                       id SERIAL PRIMARY KEY,
                       evento VARCHAR(255) NOT NULL CHECK (evento <> ''),
                       dataInicial DATE NOT NULL,
                       dataFinal DATE NOT NULL,
                       valoringresso DOUBLE PRECISION NOT NULL,
                       quantidadedisponivel INTEGER NOT NULL
);
CREATE TABLE usuario (
                       id SERIAL PRIMARY KEY,
                       cpf VARCHAR(11),
                       nome VARCHAR(255) NOT NULL CHECK (nome <> ''),
                       email VARCHAR(255) NOT NULL CHECK (email <> ''),
                       datanascimento DATE NOT NULL
);
CREATE TABLE ingresso (
                       id SERIAL PRIMARY KEY,
                       evento INTEGER NOT NULL REFERENCES evento(id),
                       usuario INTEGER NOT NULL REFERENCES usuario(id)
);
CREATE TABLE endereco (
                        id SERIAL PRIMARY KEY,
                        rua VARCHAR(255) NOT NULL CHECK (rua <> ''),
                        numero INTEGER NOT NULL,
                        bairro VARCHAR(255) NOT NULL CHECK (bairro <> ''),
                        cidade VARCHAR(255) NOT NULL CHECK (cidade <> ''),
                        estado VARCHAR(255) NOT NULL CHECK (estado <> ''),
                        cep VARCHAR(8) NOT NULL CHECK (cep <> ''),
                        usuario INTEGER REFERENCES usuario(id),
                        evento INTEGER REFERENCES evento(id)
    );  
)
```

2. **Configuração do Projeto:**
    - Clone este repositório em seu ambiente.
    - Abra o arquivo `application.properties` e insira as credenciais do seu banco de dados.

3. **Execução do Projeto:**
    - Certifique-se de que todas as dependências do projeto estão instaladas.
    - Inicie o servidor.

4. **Acesso ao Swagger(Documentação da API):**
    - URL_DO_SEU_PROJETO/swagger-ui/index.html
    - URL padrão: http://localhost:8080/swagger-ui/index.html
