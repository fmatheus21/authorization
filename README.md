<h1 align="center">Autenticação e Autorização</h1>

<br/>

>## <center>  🚧 Em Construção 🚧  </center>

<br/>

## Conteúdos


* [Sobre](#sobre)
* [Pré-requisitos](#pré-requisitos)
* [Como usar](#como-usar-authorization-server)
  * [Authorization-Server](#como-usar-authorization-server)
  * [User-Service](#como-usar-user-service)
* [Observações](#observações)
* [Tecnologias](#tecnologias)


<br/>

## Sobre
Este projeto foi desenvolvido para fins de estudo e é composto por dois módulos: <code>***authorization-server***</code> e <code>***user-service***</code>.
- **authorization-server:** Responsável pela autenticação e geração de tokens de acesso, garantindo a segurança das requisições ao **user-service**.
- **user-service:** Sistema de gerenciamento de usuários, permitindo operações como listagem, busca, criação, atualização de dados e permissões. Além disso, apenas o próprio usuário pode alterar sua senha. Este projeto tem implementação de <code>***Arquitetura Hexagonal***</code>.

<br/>

## Pré-requisitos
> - Mysql 8
> - JDK 17
> - Postman v9.31.0
> - Docker
> - Intellij

<br/>

<a id="como-usar-authorization-server"></a>
## Como Usar Authorization-Server
No diretório <code>***/authorization-server***</code>, edite o arquivo <code>***application.yml***</code>,  e informe as seguintes variáveis de ambiente:
- <code>***${SPRING_DATASOURCE_USERNAME}***</code>Usuário do seu banco de dados
- <code>***${SPRING_DATASOURCE_PASSWORD}***</code>Senha do seu banco de dados
- <code>***${RABBITMQ_USERNAME}***</code> - Informe 'admin'.
- <code>***${RABBITMQ_PASSWORD}***</code> - Informe 'admin'.

Execute o Docker na sua máquina. Após o Docker estar em execução, no seu console, entre no diretório <code>***/authoriozation/docker***</code> e execute o seguinte comando:

- <code>***docker-compose up -d***</code>

Isso irá subir o container do RabbitMQ. Para verificar se o RabbitMQ subiu, entre na url <code>***http://localhost:15672***</code>
 
Agora entre na classe <code>***AuthorizationServerApplication***</code> e execute o método <code>***main***</code>. Quando o projeto iniciar, o banco e 
as tabelas serão criadas através de migrations. 
 
O projeto roda na url <code>***http://loccalhost:9000/api/v1***</code>
 
Para gerar um token de acesso, use o curl abaixo no <code>***Postman***</code>. O curl já tem todas as informações necessárias. O tipo de autenticação é <code>***Basic***</code>.
> - <code>***username:***</code> authorization_code
> - <code>***password:***</code> 17686e8b-34d2-11ee-8422-581122c7752d

#### CURL
 ```
 curl --location 'http://localhost:9000/api/v1/oauth2/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic YXV0aG9yaXphdGlvbl9jb2RlOjE3Njg2ZThiLTM0ZDItMTFlZS04NDIyLTU4MTEyMmM3NzUyZA==' \
--data-urlencode 'grant_type=custom_password' \
--data-urlencode 'username=67780886050' \
--data-urlencode 'password=12345678' \
--data-urlencode 'uuid_system=50b56e7d-2c5e-11ee-a204-581122c7752d' \
--data-urlencode 'zip_code=23045-805'
 ```

<br/>

## Como Usar User-Service
No diretório <code>***/user-service***</code>, edite o arquivo <code>***application.yml***</code>,  e informe as seguintes variáveis de ambiente:
- <code>***${SPRING_DATASOURCE_USERNAME}***</code>Usuário do seu banco de dados
- <code>***${SPRING_DATASOURCE_PASSWORD}***</code>Senha do seu banco de dados

Agora entre na classe <code>***UserServiceApplication***</code> e execute o método <code>***main***</code>.

O projeto roda na url <code>***http://localhost:9100/api/v1***</code>.
O projeto está documentado com o <code>***Swagger***</code> e você pode acessar pela url <code>***http://localhost:9100/api/v1/swagger-ui/index.html***</code>

No <code>***Postman***</code>, utilize o curl abaixo para listar usuários. Substitua o `<token>` pelo token gerado no authorization-server.
#### CURL
 ```
 curl --location 'http://localhost:9100/api/v1/users?document=67780886050' \
--header 'Authorization: Bearer <token>'
 ```

<br/>

## Observações
>O Projeto ainda está em fase de <code>***desenvolvimento***</code> e logo receberá atualizações.
> Estou desenvolvendo o <code>***Front-End***</code> em <code>***Angular***</code> para este projeto. Você pode baixá-lo <code>***[AQUI](https://github.com/fmatheus21/user-management)***</code> no meu repositório.

## Tecnologias

![Java](https://img.shields.io/static/v1?label=Java&message=17&color=green)
![Spring Boot](https://img.shields.io/static/v1?label=spring-boot&message=3.2.2&color=green)
![MySql](https://img.shields.io/static/v1?label=mysql&message=8&color=green)
![OpenFeign](https://img.shields.io/static/v1?label=open-feign&message=12.5&color=green)
![FlywayDB](https://img.shields.io/static/v1?label=flyway-db&message=9.22.3&color=green)
![Oauth2](https://img.shields.io/static/v1?label=oauth2&message=3.2.2&color=green)
![RabbitMQ](https://img.shields.io/static/v1?label=rabbitmq&message=3.2.2&color=green)
![modelmapper](https://img.shields.io/static/v1?label=modelmapper&message=3.1.1&color=green)
![SpringDoc](https://img.shields.io/static/v1?label=springdoc-openapi&message=2.1.0&color=green)
 

![Lombok](https://img.shields.io/static/v1?label=lombok&message=1.18.4&color=green)
![ModelMapper](https://img.shields.io/static/v1?label=model-mapper&message=3.1.0&color=green)
![Commons](https://img.shields.io/static/v1?label=commons-lang3&message=3.12.0&color=green)
![Flyway](https://img.shields.io/static/v1?label=flywaydb&message=7.7.3&color=green)
![jUnit](https://img.shields.io/static/v1?label=junit&message=5.8.2&color=green)