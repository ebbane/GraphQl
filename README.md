<h1 align="center">Web Service ReST</h1>

  <div align="center">
    <strong>M2 Informatique</strong>
    <br />
    <a href="https://github.com/ebbane/GraphQl"><strong>Explore le repo</strong></a>
    <br />
    <br />
    ·
    <a href="https://www.java.com/fr/">Java 17</a>
  </div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table des matières</summary>
  <ol>
    <li>
      <a href="#a-propos-du-projet">A propos du projet</a>
      <ul>
        <li><a href="#construit-avec">Construit avec</a></li>
      </ul>
    </li>
    <li>
      <a href="#commencer">Commencer</a>
      <ul>
        <li><a href="#prérequis">Prérequis</a></li>
        <li><a href="#installations">Installations</a></li>
      </ul>
    </li>
    <li>
      <a href="#commandes-utiles">Commandes utiles</a>
    </li>
    <li>
      <a href="#contact">Contact</a>
    </li>
  </ol>
</details>

## A propos du projet

L’objectif est la création d’un WebService de type Api GraphQl permettant la gestion d’une liste de
jeux vidéo

### Construit avec

Ce projet est construit avec les logiciels, et applications suivantes :

* [Docker](https://www.docker.com/)
* [Java](https://www.java.com/fr/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [PostgreSQL](https://www.postgresql.org/)
* [Github](https://github.com/)
* [IntelliJ IDEA](https://www.jetbrains.com/fr-fr/idea/)
* [GraphQL](https://graphql.org/)

<p align="right">(<a href="#top">Retour en haut</a>)</p>

## Commencer

Mise en place du projet en local pour les utilisateurs

### Prérequis

Ce projet nécéssite quelques installations de pré-lancement.

* [docker desktop](https://docs.docker.com/desktop/)

ou

* [Java 17](https://www.java.com/fr/download/)
* [Maven](https://maven.apache.org/install.html)

### Installations

Instructions à suivre (conseillé) pour le lancement du projet en local.

#### Avec docker

1. Lancer docker sur son environnement
2. Cloner le repo (ou dézipper le dossier)
   ```sh
   git clone git@github.com:ebbane/GraphQl.git
   ```
3. Lancer la démo (via docker) :
   ```sh
   cd GraphQl
   docker-compose up -d
   ```

#### Avec maven

1. Lancer localement une base de donnée postgres
2. Cloner le repo (ou dézipper le dossier)
   ```sh
   git clone git@github.com:ebbane/GraphQl.git
   ```

2. Pour lancer la demo vous avez deux possibilitées :

    1. générer un nouveau jar
   ````sh
       mvn clean package
       java -jar target/game-api.jar
   ````

    2. lancer l'application via spring
   ```` sh
       mvn clean install -DskipTests=true
       mvn spring-boot:run
   ````

## Utilisation

L'application tourne au port **8080**

- [GraphQl interface](http://localhost:8080/graphiql?path=/graphql)

## Commandes utiles

1. Voir les container en cours d'exécution
   ```sh
      docker ps
    ```
3. Supprimer une image docker
   ```sh
      docker rmi <image_id>
    ```

<p align="right">(<a href="#top">Retour en haut</a>)</p>

## Contact

Ebbane DIET - ebbane.diet@ynov.com - support (À contacter si un problème survient au lancement)
Github : [@ebbane](https://github.com/ebbane)
<p align="right">(<a href="#top">Retour en haut</a>)</p>

## Documentation

- [Building a GraphQL service](https://spring.io/guides/gs/graphql-server/)
- [Spring for GraphQL](https://spring.io/projects/spring-graphql)
- [GraphQL Java](https://www.graphql-java.com/)
- [Dockerfile reference](https://docs.docker.com/engine/reference/builder/)
- [Liquibase YAML](https://docs.liquibase.com/start/get-started/liquibase-yaml.html)
- [Advanced Spring Data JPA - Specifications and Querydsl](https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl)