# FÃ CLUBE - API

Projeto Aplicado III - 4ª Fase de Análise e Desenvolvimento de Sistemas SENAI


## Gerenciamento de carteirinhas para fã-clube

### Descrição Resumida

Desenvolver um sistema para que os fãs possam se inscrever em um fã-clube e permitir que os responsáveis pelo fã-clube gerem a carteirinha de fã e a enviem para os solicitantes.

### Benefícios Esperados

Com esse projeto, objetiva-se reduzir o tempo da geração de carteirinhas do fã-clube, processo esse que hoje é feito de forma manual, e diminuir custos com sua impressão, já que será gerada de forma digital.

### Detalhamento

O fã-clube (ocultar informação) está em fase de crescimento, com novos membros toda semana, e o gerenciamento do processo para gerar as carteirinhas de fã está crescendo em complexidade. Por isso, a criadora do fã-clube deseja que seja criado um sistema web para apoiar esse gerenciamento. O sistema é composto de duas partes: site e área de administração. 

O site, que possui o intuito de divulgar o fã-clube e facilitar a adesão de novos fãs, deve ter 3 páginas: a página inicial (para divulgação do fã-clube), uma página de conteúdo (para curiosidades sobre o artista) e outra com formulário para inscrição no fã-clube.

A área de administração deve ter acesso aos registros de inscrição no fã-clube, para permitir o controle da criação das carteirinhas (feitas externamente em um software de edição de imagens), para que se possa indicar as carteirinhas a solicitar, as que foram solicitadas, as que foram produzidas, e as que foram enviadas ao solicitante.

### Restrições

Não poderá utilizar recursos de alto custo na aplicação e infraestrutura.

## Preparação do ambiente
* JDK 17
* Maven
* PostgresSQL
* Após clonar o repositório, executar o comando `mvn clean install` para instalar todas as dependências necessárias.
* Configurar os dados de conexão com o banco de dados.
* Executar `mvn spring-boot:run` para rodar a api em ambiente local.

## Documentaçao da api

Após rodar a aplicação em ambiente local é possível consultar a documentação com todos os endpoints no endereço abaixo.

`http://localhost:8080/swagger-ui/index.html`