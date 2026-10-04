# 🌤️ Previsão do Tempo

Aplicativo Android desenvolvido em **Kotlin** para consulta da previsão do tempo de uma cidade, utilizando uma API meteorológica para obtenção dos dados.

O projeto foi desenvolvido com o objetivo de aplicar conceitos de desenvolvimento Android, consumo de APIs REST e organização da aplicação utilizando a arquitetura **MVVM (Model-View-ViewModel)**.

## 📱 Sobre o projeto

O aplicativo permite realizar a busca de informações meteorológicas a partir do nome de uma cidade.

Os dados são obtidos por meio de uma API externa e apresentados na interface do aplicativo.

O projeto utiliza separação de responsabilidades entre as camadas da aplicação, facilitando a organização, manutenção e evolução do código.

## 🚀 Funcionalidades

- Busca da previsão do tempo por cidade;
- Consumo de API REST;
- Exibição das informações meteorológicas;
- Tratamento dos dados recebidos da API;
- Gerenciamento do estado da interface;
- Separação das responsabilidades utilizando MVVM;
- Injeção de dependências.

## 🛠️ Tecnologias utilizadas

- **Kotlin**
- **Android Studio**
- **Jetpack Compose**
- **MVVM**
- **Retrofit**
- **Koin**
- **Coroutines**
- **StateFlow**
- **Gradle**
- **Git**
- **GitHub**

## 🏗️ Arquitetura

O projeto utiliza o padrão arquitetural **MVVM (Model-View-ViewModel)**.

A estrutura da aplicação é organizada em responsabilidades distintas:

### Model

Representa os dados utilizados pela aplicação, incluindo as estruturas responsáveis por mapear as respostas recebidas da API.

### Repository

Responsável pela comunicação entre a fonte de dados e as demais camadas da aplicação.

O repositório realiza as chamadas necessárias à API por meio do Retrofit.

### ViewModel

Responsável pela lógica relacionada à interface e pelo gerenciamento do estado da aplicação.

A comunicação com o repositório ocorre sem que a interface precise acessar diretamente a API.

### View

Desenvolvida utilizando **Jetpack Compose**, é responsável pela apresentação das informações e interação com o usuário.

## 🌐 Consumo da API

A comunicação com o serviço de previsão do tempo é realizada utilizando **Retrofit**.

De forma simplificada, o fluxo de dados da aplicação ocorre da seguinte maneira:

```text
Interface (Compose)
        ↓
     ViewModel
        ↓
    Repository
        ↓
     Retrofit
        ↓
 API de previsão do tempo
```

Após receber os dados, o fluxo retorna até a interface para que as informações sejam apresentadas ao usuário.

## 🔐 Configuração da API Key

Por segurança, a chave da API **não está armazenada no repositório GitHub**.

Para executar o projeto, é necessário configurar sua própria chave no arquivo:

```text
local.properties
```

Adicione a chave utilizando a propriedade definida pelo projeto, por exemplo:

```properties
WEATHER_API_KEY=SUA_CHAVE_AQUI
```

> O arquivo `local.properties` está incluído no `.gitignore` e não deve ser enviado ao GitHub.

## ▶️ Como executar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/Keyde-lab/previsao-tempo.git
```

2. Abra o projeto no **Android Studio**.

3. Aguarde a sincronização das dependências do Gradle.

4. Configure sua API Key no arquivo `local.properties`.

5. Selecione um emulador Android ou dispositivo físico.

6. Execute o aplicativo utilizando **Run ▶** no Android Studio.

## 📂 Estrutura geral

```text
PrevisaoTempo
│
├── app
│   └── src
│       └── main
│           ├── java
│           │   └── com.example.previsaotempo
│           │       ├── data
│           │       ├── di
│           │       ├── repository
│           │       ├── ui
│           │       └── viewmodel
│           │
│           ├── res
│           └── AndroidManifest.xml
│
├── gradle
├── .gitignore
├── build.gradle.kts
├── gradle.properties
└── settings.gradle.kts
```

## 🎯 Objetivos de aprendizagem

O desenvolvimento deste projeto possibilitou a aplicação prática de conceitos relacionados a:

- desenvolvimento de aplicativos Android com Kotlin;
- criação de interfaces com Jetpack Compose;
- consumo de APIs REST;
- utilização do Retrofit;
- arquitetura MVVM;
- Repository Pattern;
- injeção de dependências com Koin;
- programação assíncrona com Coroutines;
- gerenciamento de estado;
- controle de versão utilizando Git e GitHub;
- proteção de informações sensíveis, como chaves de API.

## 👩‍💻 Autores

**Keyde Taisa, Guilherme Pereira, Bartolomeu Gusella**

Projeto desenvolvido como atividade de aprendizagem em desenvolvimento de aplicações Android.

## 📄 Licença

Projeto desenvolvido para fins educacionais.