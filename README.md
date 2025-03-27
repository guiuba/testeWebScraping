# Teste de Web Scraping

Este teste foi realizado utilizando a linguagem **Java**.

## O que o código executa:
1. **Acesso ao site**:
   [https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos](https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos)

2. **Download dos arquivos**:
   - **Anexo I** em formato PDF
   - **Anexo II** em formato PDF

3. **Compactação**:
   - Todos os anexos são compactados em um único arquivo chamado `anexos.zip`.

---

# Detalhes do `build.gradle`

O arquivo `build.gradle` inclui as seguintes dependências e configurações:

- **`jsoup`**: 
  - Utilizado para realizar web scraping e baixar os arquivos PDFs.

- **`commons-io`**:
  - Responsável pela manipulação de arquivos de forma eficiente.

- **`commons-compress`**:
  - Usado para compactar os PDFs em um arquivo ZIP.

- **`fatJar`**:
  - Configuração para gerar um JAR executável que contém todas as dependências necessárias para a aplicação.

---

# Código Java: Funcionalidades

Este código em Java executa as seguintes tarefas:

1. **Web Scraping**:
   - Utiliza a biblioteca **Jsoup** para localizar os links dos arquivos PDF correspondentes aos **Anexos I** e **II**.

2. **Download de Arquivos**:
   - Realiza o download dos arquivos PDF diretamente da página da **ANS**.

3. **Compactação**:
   - Compacta os arquivos PDF em um único arquivo no formato **ZIP**.

4. **Geração de Executável**:
   - Gera um arquivo **JAR** executável utilizando o **Gradle** para facilitar a execução do programa em diferentes ambientes.

---

# Geração e Execução do Executável `.jar`

## Como gerar o executável:
Para criar o arquivo `.jar` com todas as dependências, execute o comando abaixo:

```sh
./gradlew fatJar
```

## Isso criará o arquivo webscraper-1.0.jar na pasta build/libs.

## Como executar o programa:
Para rodar o programa, utilize o seguinte comando:
```sh
java -jar build/libs/webscraper-1.0.jar
```

## O que o programa fará:
Realizará o web scraping.

Fará o download dos PDFs.

Criará um arquivo ZIP contendo os anexos.




