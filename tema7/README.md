# tema 7 - Microservices

##  Observação: 
> para este projeto foram utilizadas as seguintes configurações: ubuntu 20.04 e docker 19.03, caso você precise de uma versão diferente do docker ou possua um sistema operacional diferente você poderá seguir a documentação oficial através deste link: https://docs.docker.com/get-docker/


## O primeiro passo para garantir que tudo ocorrerá dentro do previsto é, caso você não possua, instalar o docker. Para isso você pode seguir este passo a passo: 
-   *https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04-pt*
#

# Agora devemos gerar as chaves de acesso para que possamos acessar as APIs do twitter e do github sem problemas

## Chaves de acesso do twitter:
-   Para isso você deve acessar *https://developer.twitter.com/en/portal/dashboard*, logar com sua conta (caso não tenha, ou deseje uma nova, você podera criar), criar um projeto e adicionar um novo app.

-   Feito isso você deve ir até a aba *Projects & Apps* no menu lateral e selecionar o app que você criou.

-   Após isso você verá a aba *Keys and Tokens* logo abaixo do nome do seu app, selecione esta aba, selecione *view keys* e você verá suas chaves de acesso

-   Antes de copiarmos as chaves você deve localizar o arquivo *application.properties* que está localizado em *./tema7/twitter/src/main/resources/*. Nele iremos informar as chaves

-   A chave *API Key* deve ser copiada para o arquivo onde está informada a chave *"spring.social.twitter.appId"*

-   A chave *API key secret* deve ser copiada para o arquivo onde está informada a chave *"spring.social.twitter.appSecret"*

-   A chave *API key secret* deve ser copiada para o arquivo onde está informada a chave *"spring.social.twitter.appSecret"*

-   Agora você deve encontrar a sessão "Access Token & Secret" um pouco mais abaixo na pagina e selecionar *"Generate"*

-   Feito isso você verá duas chaves: *Access token* e *Access token secret*

-   A chave *Acecss token* deve ser copiada para o arquivo onde está informada a chave *"spring.social.twitter.token"*

-   A chave *Acecss token* deve ser copiada para o arquivo onde está informada a chave *"spring.social.twitter.token.secret"*

-   Com isso a configuração para accesso à API do twitter está finalizada

## Chaves de acesso do Github:
-   Para isso você deve acessar *https://github.com/*, logar com sua conta (caso não tenha, ou deseje uma nova, você podera criar), criar um projeto e adicionar um novo app.

-   Feito isso você deve ir ir até a aba *settings*, que pode ser acessada pela foto de perfil no canto superior direito

-   Após isso você deve selecionar *developer settings* no menu lateral e logo após o carregamento da pagina, você irá selecionar a opção *Personal access tokens* tambem no menu lateral

-   Na pagina que será carregada você verá a opção *Generate new token*, clique nela

-   Você precisará informar uma descrição para a chave de acesso, aconselho a utillizar *"tema7"*, e selecionar o escopo da chave,  que deve ser a opção *repo*. 

-   Feito isso, no fim da pagina você deve clicar em *generate token*

-   Você verá uma chave criada, copie ela e cole na chave *spring.social.github.token* no arquivo application.properties que está localizado em *./tema7/github/src/main/resources/

-   Você também deverá informar o seu usuário na chave *spring.social.github.login* que está, também  no arquivo *application.resources*

-   Com isso a configuração para accesso à API do twitter está finalizada

## Com o docker instalado e as configurações de acesso definidas você deverá ir, pelo terminal, até o diretório da aplicação, *./tema7*, e rodar o script para criar e iniciar os containers, para isso basta executar o seguinte comando:
    sudo bash startup.sh

## Pronto, os servições estarão rodando, se desejar encerrar o processo você pode executar, dentro do mesmo diretório, o comando
    sudo bash stop.sh

# Utilizando as APIs

## Para testarmos o projeto será necessário utilizar uma ferramente de testes para APIs. Neste desenvolvimento foi utilizado o Postman, sendo assim, utilizarei o mesmo para exemplos.
-   Caso tenha interesse em utilizar o Postman, a documentação para a instalação da ferramenta, no ubunut 20.04, está neste link https://linuxize.com/post/how-to-install-postman-on-ubuntu-20-04/

## Utilizando o postman
-   Para todos os teste você deve, com a aplicação rodando, criar um request no postman (caso ainda não possua) através do menu File>New

## Twitter Service
-   Após ter o request criado você irá informar o método GET e a seguinte URL:
    -   http://localhost:8081/rest/tweetsTotal/{username}
        - Altere {username} para o usuario que voce deseja visualizar a quantidade de tweets

## Github Service
-   Após ter o request criado você irá informar o método GET e a seguinte URL:
    -   http://localhost:8082/rest/reposTotal/{username}
        - Altere {username} para o usuario que você deseja visualizar a quantidade de repositérios públicos

## Twitter and Github Service
-   Após ter o request criado você irá informar o método GET e a seguinte URL:
    -   http://localhost:8080/rest/twitterAndGithub?usergithub={userGit}&usertwitter={userTwitter}
        - Altere {userGit} para o usuário que voce deseja visualizar a quantidade de repositorios publicos
        - Altere {userTwitter} para o usuário que você deseja visualizar a quantidade de tweets
    
    -   Você tambem  pode acessar a visualizacao individual atraves das seguintes urls
        -   http://localhost:8080/rest/tweets/{username}
        -   http://localhost:8080/rest/github/{username}
            - Sempre lembrando que você deve alterar a variavel {username} para o usuário que desejar