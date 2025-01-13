# Serviço de Validação JWT

Este serviço fornece endpoints para criar e validar JSON Web Tokens (JWTs). Ele é construído usando Spring Boot e Maven.

## Requisitos

- Java 11 ou superior
- Maven 3.6.0 ou superior

## Configuração

Certifique-se de que o arquivo `application.yml` está configurado corretamente com a chave secreta JWT:

```yaml
server:
  port: 8080

jwt:
  secret: 561491AE98362741F722202EED3288E8FF2508B35315ADBF75EEB3195A926B51
```
## Executando o serviço
1. Clone o repositório

```bash
git clone https://github.com/profdinho
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
### Validar JWT
* URL: /api/validate/{jwt}
* Método: POST
* Parâmetro de caminho:
  * jwt: O token JWT a ser validado
* Resposta de sucesso:
```
true
```
* Resposta de erro:
```
false
```
## Testando o serviço
Para executar os testes, execute o seguinte comando:

```bash
mvn test
```
