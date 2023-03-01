# Microservices
> For this project we are using the following: ubuntu 20.04 and docker 19.03

## Installing docker: 
-   *https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04-pt*

# Generating the API Keys

## Twitter API key:
-   Access *https://developer.twitter.com/en/portal/dashboard*, log in to your account, create a project, and add a new app.
-   After that you should go to *Projects & Apps* and select the app you just create
-   Accessing *Keys and Tokens*, then go to the specific key clicking in *view keys* under *Apps* section

-   You will add the keys in the *application.properties* file that is located in *./tema7/twitter/src/main/resources/*
-   it will look like this:

    spring.social.twitter.appId=INSIRA_AQUI_SUA_API_KEY
    spring.social.twitter.appSecret=INSIRA_AQUI_SUA_API_KEY_SECRET

-   Now, go to "Access Token & Secret" and you will see two keys: *Access token* and *Access token secret*
-   Copy the values and add them to your properties file as before:

    twitter.access.token=INSIRA_AQUI_SEU_TOKEN
    twitter.access.token.secret=INSIRA_AQUI_SEU_TOKEN_SECRET


## Github API Keys:
-   Access  *https://github.com/*, log in to your account, create a project and add a new app.

-   Then you should go to the *Settings* page, and then go to *developer settings* and select the *Personal access tokens* option
-   where you will see *Generate new token*, click on it

-   You will be asked to inform a description for your key

-   Then, click on *generate token*

-   Copy the key and paste it on application.properties located in *./tema7/github/src/main/resources/
-   You also need to inform your github username
-   The configurations will look like this:

    spring.social.github.login=INSIRA_AQUI_SEU_USUARIO
    spring.social.github.token=INSIRA_AQUI_SEU_TOKEN

## Using the terminal, go to the application's folder *./tema7*, and run the following command, that will create and run the containers

    sudo bash startup.sh

## Done, the services are up! In case you want to stop them just run the following command:

    sudo bash stop.sh

# Testing the APIs

## For this tests we were using Postman, feel free to test it as you wish.
-   This is the steps to install postman in case you want it:

    https://linuxize.com/post/how-to-install-postman-on-ubuntu-20-04/

## Postman
-   For all tests you should create a request using File > New

## Twitter Service
-   After creating the postman request you should inform the GET method and insert the following URL:
    -   http://localhost:8081/rest/tweetsTotal/{username}

## Github Service
-   After creating the postman request you should inform the GET method and insert the following URL:
    -   http://localhost:8082/rest/reposTotal/{username}

## Twitter and Github Service
    -   http://localhost:8080/rest/twitterAndGithub?usergithub={userGit}&usertwitter={userTwitter}
        - Change {userGit} to the one you want to see the ammount of public repos
        - Change {userTwitter} to the one you want to see the ammount of tweets
    
    -   You can also do individual requests
        -   http://localhost:8080/rest/tweets/{username}
        -   http://localhost:8080/rest/github/{username}
