# Sistema de Gestão de Usuários

## Descrição
Este projeto é um sistema de microserviços desenvolvido em Java, que permite o cadastro e a alteração de senha de usuários. Além disso, registra em um log as ações executadas pelo usuário (criação ou atualização de senha). O sistema é composto por dois microserviços interligados via Kafka e usa o MySQL como banco de dados para armazenar informações dos usuários de maneira segura, com criptografia de senha.

## Tecnologias Usadas
- **Java 17**
- **Spring Boot**
- **Kafka:** Utilizado para enviar mensagens entre os microserviços.
- **Docker:** Para a criação de containers dos microserviços e banco de dados.
- **MySQL:** Banco de dados relacional para armazenamento dos dados dos usuários.

## Microserviço de Usuários
### Este serviço permite:
- **Criar usuário:** Registra um novo usuário no sistema, salvando seu nome, senha criptografada, e-mail e endereço.
- **Alterar senha do usuário:** Permite a alteração segura da senha de um usuário.

### Endpoints
#### Criar Usuário: ```/api/users/register ```
- Exemplo de requisição:
```
{
  "name": "string",
  "password": "string",
  "email": "string",
  "cep": "string"
}
```

#### Alterar Senha do Usuário: ```/api/users/update-password ```
- Exemplo de requisição:
```
{
  "name": "string",
  "oldPassword": "string",
  "newPassword": "string"
}
```

## Microserviço Notify
### Este serviço recebe mensagens do Kafka e registra em seu console as ações realizadas pelos usuários:
- **Ações registradas:** Criação ou atualização da senha de um usuário.

## Requisitos do Sistema
Certifique-se de ter as seguintes ferramentas instaladas:
- Docker
- Docker Compose

## Instalação e Execução
Siga os passos abaixo para configurar o projeto e executá-lo no seu ambiente:
1. **Clone o repositório**
   ```
   git clone https://github.com/GuilhermeFrnc/User_System.git
   ```

2. **Navegue até o diretório do projeto**
   ```
   cd User_System
   ```

3. **Execute o Docker Compose**
Este comando irá compilar e iniciar todos os containers, incluindo os microserviços, Kafka e MySQL.
  ```
     docker-compose up --build
  ```
4. **Aguarde a inicialização dos serviços**
Após a execução do comando anterior, todos os containers estarão em execução. Verifique o console para confirmar que os serviços foram iniciados corretamente.

5. **Acesse os serviços** Agora, você pode testar os endpoints conforme descrito acima.

6. **Verifique os logs do serviço Notify** Para visualizar as mensagens registradas pelo serviço Notify, abra um novo terminal na mesma pasta e execute o seguinte comando:
  ```
    docker logs -f apiuser-notify-1
  ```

7. **Para parar o sistema** Pressione CTRL+C no terminal ou use o comando:
  ```
    docker-compose down
  ```
