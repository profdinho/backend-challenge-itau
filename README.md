# Serviço de Validação JWT

Este serviço fornece endpoints para criar e validar JSON Web Tokens (JWTs). Ele é construído usando Spring Boot e Maven.

## Requisitos

- Java 11 ou superior
- Maven 3.6.0 ou superior

## Executando o serviço
1. Clone o repositório

```bash
git clone https://github.com/profdinho/backend-challenge-itau.git
```
2. Compile e execute o serviço usando Maven

```bash
mvn clean install
mvn spring-boot:run
```
3. O serviço estará disponível em `http://localhost:8080`

## Endpoints
### Criar JWT
* URL: /api/create
* Método: POST
* Corpo da requisição:
```json
{
    "role": "Admin",
    "seed": 7,
    "name": "ValidName"
}
```
* Resposta:
```
eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjo3LCJOYW1lIjoiVmFsaWROYW1lIn0.xgY6QnXNhfRoerBh4W35gBF_GOTXF-QNfB1YZkYSzPA
```
O método createJWT() da classe JwtValidator é responsável por criar o token JWT.
Ele recebe um objeto com os claims role, seed e name no corpo e retorna o token JWT.\
Esse método foi criado apenas para testar a validação do token JWT.

### Validar JWT
* URL: /api/validate/{jwt}
* Método: POST
* Parâmetro de caminho:
  * `jwt`: O token JWT a ser validado
* Resposta de sucesso:
```
true
```
* Resposta de erro:
```
false
```
O método validateJWT() da classe JwtValidator é responsável por validar o token JWT.
Ele recebe o token JWT no parâmetro de caminho e retorna *true* se o token for válido 
e *false* se não for.

## Testando o serviço
Para executar os testes, execute o seguinte comando:

```bash
mvn test
```
## Containerização da aplicação
Para executar um container Docker com a aplicação, execute os seguintes comandos:

```bash
docker build -t challenge .
docker run -p 8080:8080 challenge
```
O serviço estará disponível em `http://localhost:8080`
