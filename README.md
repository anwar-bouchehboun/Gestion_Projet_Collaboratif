
### Gestion de Projet Collaboratif 2.0
## Description du projet
La Gestion de Projet Collaboratif 2.0 est une application web permettant la gestion collaborative de projets, tâches, et équipes. Elle fournit une interface intuitive et responsive pour la création, gestion, et suivi des projets d'entreprise, des équipes de collaborateurs, et de leurs tâches respectives. L'application est basée sur une architecture MVC en couches, développée avec Java 8, JSP, Servlets, JSTL, et JDBC, et fonctionne avec une base de données MySQL.

#Objectif général de l'application
L'application a pour but de faciliter la gestion de projet collaborative, en permettant aux utilisateurs de :

- Créer, modifier et supprimer des projets
- Assigner des tâches aux membres des équipes
- Suivre l'avancement des projets et des tâches
- Gérer les équipes et leurs membres

## Technologies utilisées
- Langage : Java 8
- Frameworks : JSP, Servlets, JSTL
- Gestion des dépendances : Maven
- Serveur d'application : Tomcat
- Base de données : MySQL (JDBC)
- Design de l'interface : Bootstrap
- Maquettes : Figma
- Tests unitaires : JUnit
- Outils de gestion : Jira pour la gestion agile et Git pour le versionnement

## Structure du projet
<img width="419" alt="Capture d’écran 2024-10-05 212356" src="https://github.com/user-attachments/assets/d8295c0b-172a-4c35-aaab-9be7b233ef17">


## Description de l'architecture adoptée
L'application utilise une architecture en couches basée sur le modèle MVC (Modèle-Vue-Contrôleur) pour séparer les responsabilités :

Controller : Les Servlets qui gèrent les requêtes HTTP et redirigent vers les vues ou services appropriés.
Model : Contient les classes métiers représentant les objets principaux comme Projet, Tâche, Equipe, etc.
Service : Contient la logique métier de l'application, responsable des règles de gestion.
Repository : Gère l'accès aux données en utilisant JDBC pour effectuer les opérations CRUD sur la base de données MySQL.
Util : Fournit des classes utilitaires comme des fonctions de validation ou de gestion des dates.

## SQL
<img width="538" alt="CollabClassDiagramme" src="https://github.com/user-attachments/assets/b55f15b2-6373-47db-a28d-64666677f40d">


## Instructions d'installation et d'utilisation

# 1 Prérequis
- Java JDK 8 ou supérieur
- Apache Tomcat 9 ou supérieur
- MySQL pour la base de données
- Maven pour la gestion des dépendances

 # 2 Étapes pour configurer la base de données
- Créez une base de données MySQL.
- Exécutez le script SQL fourni pour créer les tables nécessaires (voir dossier SQL/).
- Configurez la connexion JDBC dans le fichier web.xml avec vos identifiants MySQL.

# 3 Comment lancer l'application sur Tomcat
- Compilez le projet avec Maven : mvn clean install
- Déployez le fichier WAR généré dans le répertoire target/ vers votre serveur Tomcat.
- Accédez à l'application dans votre navigateur à l'adresse http://localhost:8080/Projet_Calbo.

## Captures d'écran
# Equipe
![mise_a_j![pageEquipe](https://github.com/user-attachments/assets/790282b8-fed2-45d7-8450-79da63689b5c)
our](https://github.com/user-attachments/assets/52ac2332-f106-4d83-a850-e4095004bb4d)
# Projet
<img width="418" alt="affichageProjets" src="https://github.com/user-attachments/assets/ffbd6c42-a8c4-4aaa-bfd5-e866dc26109d">
<img width="341" alt="gestionProjets" src="https://github.com/user-attachments/assets/6eb1329b-d974-4a94-9a6f-bb3f8f074aa1">
# Tache
![modifier_tache](https://github.com/user-attachments/assets/055d84fb-2a6e-4b27-bdf6-3e5dfe8b17dc)
![creation_tache](https://github.com/user-attachments/assets/b9f56f74-6f84-49dc-9562-ffad6264f94a)
![table_taches](https://github.com/user-attachments/assets/022101b9-c49c-486a-9ec9-0c679f4d83b6)

## Améliorations futures possibles
- Ajout de la gestion des notifications : Notifications pour les échéances de tâches et les nouvelles affectations.
- Intégration avec un service de messagerie : Envoi d'e-mails pour informer les membres d'équipe des nouvelles tâches ou mises à jour.
- Support pour plusieurs langues : Ajout de l'internationalisation (i18n) pour supporter différentes langues.
- Amélioration du tableau de bord : Ajouter plus de statistiques et graphiques pour visualiser l'avancement des projets.



