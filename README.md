# BioOffset 🍃

Este é um projeto que foi feito durante atividades acadêmicas para o centro universitário FIAP. Nele, eu tive a oportunidade de botar em prática muitos conhecimentos que eu adquiri recentemente. 

Ele é uma plataforma que 
conecta desenvolvedores de projetos ambientais (proprietários de terras que preservam 
florestas nativas ou plantam árvores) a empresas que precisam compensar suas emissões 
inevitáveis de gases de efeito estufa.  

A API permite que as empresas comprem e publiquem seus projetos na plataforma, também 
disponibilizando os créditos de carbono para compra.

# Como executar localmente com Docker

1. **Construir a imagem Docker:**
   ```bash
   docker build -t biooffset .
   ```

2. **Executar o container:**
   ```bash
   docker run -p 8080:8080 biooffset
   ```


> **Atenção:** A aplicação falhará ao iniciar caso as credenciais do banco de dados não sejam fornecidas. Por questões de segurança, os dados de conexão com o banco de dados Oracle (`URL`, `USER` e `SENHA`) não estão presentes nos arquivos do repositório. Os dados são fornecidos quando a integração do código é feita via GitHub e deployment pelo Azure.

# Pipeline CI/CD

O fluxo de integração e entrega contínua é automatizado através de workflows do **GitHub Actions**, dividido em duas etapas principais:

### Ferramentas Utilizadas
- GitHub Actions, usado para orquestração e automação dos fluxos de CI e CD.
- Docker Hub, que serve como container registry para versionamento e armazenamento das imagens Docker.
- Azure Web App for Containers, que é um serviço em nuvem responsável por hospedar e executar a aplicação em container.
- GitHub Secrets para armazenamento seguro de credenciais (credenciais do Docker Hub, perfil de publicação da Azure e variáveis de banco/JWT).

---

### Etapas e Funcionamento

1. **Continuous Integration (CI) — `continuous-integration.yaml`**
   - **Gatilho:** Disparado a cada *Pull Request* direcionado para a branch `main`.
   - **Fluxo:**
     - Checkout do repositório (`actions/checkout@v4`).
     - Configuração da SDK do Java 21 Temurin (`actions/setup-java@v4`).
     - Execução da suíte de testes unitários (`mvn test`) utilizando variáveis de ambiente injetadas via Secrets.
     - Garante que código quebrado ou sem testes válidos não seja integrado à branch principal.

2. **Continuous Deployment (CD) — `continuous-deployment.yaml`**
   - **Gatilho:** Disparado a cada *Push* ou merge aprovado na branch `main`.
   - **Fluxo:**
     - **Build & Push (`build-and-push`):** Realiza checkout, autentica no Docker Hub (`docker/login-action@v3`), compila e envia a imagem gerada com as tags `latest` e a hash do commit (`${{ github.sha }}`) via `docker/build-push-action@v5`.
     - **Deploy na Azure (`deploy-to-azure`):** Após o envio da imagem, utiliza a action `azure/webapps-deploy@v3` com o perfil de publicação configurado para atualizar a instância no **Azure Web App for Containers** apontando para a tag recém-gerada.

# Containerização

A aplicação é empacotada em container através de um `Dockerfile` otimizado:

```dockerfile
# Etapa de build: usa uma imagem com o Maven e Java 21 para compilar o projeto
FROM maven:4.0.0-rc-5-eclipse-temurin-21 AS build

RUN mkdir /opt/app

COPY . /opt/app

WORKDIR /opt/app

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

RUN mkdir /opt/app

COPY --from=build  /opt/app/target/app.jar /opt/app/app.jar

WORKDIR /opt/app

ENV PROFILE=prd

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=$PROFILE -jar app.jar"]
```

### Estratégias Adotadas

- **Multi-Stage Build:** Separação do processo em duas etapas:
  1. *Build Stage:* Utiliza a imagem `maven:4.0.0-rc-5-eclipse-temurin-21` com todas as dependências e ferramentas necessárias para compilar o código e gerar o `.jar`.
  2. *Runtime Stage:* Utiliza apenas o JRE (`eclipse-temurin:21-jre-alpine`) copiando apenas o artefato final (`app.jar`), deixando de fora o Maven, código-fonte e caches temporários.
- **Redução de Imagem e Segurança (Alpine Linux):** Adoção de uma base minimalista baseada em Alpine JRE, reduzindo consideravelmente o tamanho final da imagem, o consumo de banda/armazenamento e a superfície de possíveis vulnerabilidades.
- **Flexibilidade de Configuração:** Uso da variável de ambiente `PROFILE=prd` passada dinamicamente na inicialização (`-Dspring.profiles.active=$PROFILE`), permitindo alternar profiles do Spring Boot sem necessidade de recriar a imagem.
- **Isolamento e Portabilidade:** Padronização do diretório de trabalho (`/opt/app`) e exposição explícita da porta da API (`EXPOSE 8080`).

# Prints do funcionamento

Os prints estarão anexados ao PDF da documentação técnica do projeto.

# Tecnologias usadas

- Java 21
- Oracle Database 
- Spring Boot para criação do projeto
- Spring JPA / Hibernate para manipulação e consulta de dados
- Flyway para criação das tabelas
- Spring web
- Lombok para evitar código boilerplate
- Java JWT Token para fazer, criptografar e verificar os tokens
- Spring Security
- Bean Validation para validar campos de requisições em  JSON
- Docker
- GitHub Actions 
- Azure Web App
- GitHub Repository Secrets
