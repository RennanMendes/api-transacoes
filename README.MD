<div align="center">    
    <h1>Api Transações</h1>
</div>

API REST para o gerenciamento de transações financeiras, desenvolvida de acordo com os requisitos do desafio técnico do Itaú.  
Link para o repositório do projeto com as orientações do desafio. 
[desafio-itau-vaga-99-junior](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior).

## Visão Geral
Esta API oferece funcionalidades para recebimento, processamento e análise de transações financeiras.

## Principais Funcionalidades
- Receber Transações
- Limpar Transações
- Calcular Estatísticas

## Tecnologias utilizadas

| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-plain.svg" alt="Java Icon" width="40" height="40" /> | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" alt="Spring Icon" width="48" height="48" /> | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-plain-wordmark.svg" alt="Docker Icon" width="40" height="40" /> |
|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------:|
|                                                            Java                                                             |                                                                    Spring                                                                     |                                                                      Docker                                                                       |

## Pré-requisitos
- [Java 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/products/docker-desktop/) (opcional)

## Instalação Local
Siga estas etapas para instalar e executar o projeto:

1. Clone o repositório:
```bash
git clone https://github.com/RennanMendes/api-transacoes.git
```

2. Compile o projeto usando o Maven:
```bash
mvn clean install
```

3. Execute o projeto:
```bash 
   java -jar target/api-transacoes-0.0.1-SNAPSHOT.jar
```

## Execução com Docker
1. Construa a imagem:
```bash
docker build -t api-transacoes .
```
2. Execute o container:
```bash
docker run -p 8080:8080 api-transacoes
```

## Endpoints Disponíveis

### POST /transacao
Este é o endpoint que irá receber as Transações. Cada transação consiste de um valor e uma dataHora de quando ela aconteceu:
```json
{
  "valor": 123.45,
  "dataHora": "2025-02-01T20:47:26.003338300-03:00"
}
```

### DELETE /transacao
Este endpoint simplesmente apaga todos os dados de transações que estejam armazenados.

### GET /estatistica
Este endpoint deve retornar estatísticas das transações que aconteceram nos últimos 60 segundos (1 minuto).
```json
{
    "count": 10,
    "sum": 1234.56,
    "avg": 123.456,
    "min": 12.34,
    "max": 123.56
}
```