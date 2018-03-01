# Backend OPOWER
## JPA + Hibernate + MySQL + Swagger with Spring Boot
#### [Istic](https://istic.univ-rennes1.fr/) School project

version 0.0.1 [development]

Project in Distributed information system (SIR [Système d'information répartie] : french)

This is a backend API OPOWER.

Allows you to compare the consumption of your home's electrical equipment with your friends.

## Explications

J'ai débuté le projet par des ébauches de codes. Je me suis rendu compte qu'il existait des frameworks comme 
Spring et Swagger qi permettent de simplifier le travail du développeur.

Je suis parti sur cet horizon pour avoir un code structuré et propre.

Je suis parti d'une partie du diagramme de classe proposé, ainsi que le texte qui allait avec.
j'ai amélioré un peu le digramme par rapport au texte.

## Spécifications

<img src="https://user-images.githubusercontent.com/8668325/36869431-1481a1c0-1d9c-11e8-9aac-412fea73206b.PNG" width="706" height="314">

- Une personne peut avoir 0 ou plusieurs amis.
- Une personne peut avoir 0 ou plusieurs résidences.
- Une résidence ne voit pas son propriétaire (car flêche sens unique sur le diagramme).
- Une résidence peut avoir 0 ou plusieurs équipements intelligents (Chauffages et/ou Equipements électriques).
- Un équipement intelligent ne peut pas voir le résidence (flêche sens unique sur le diagramme).
- Chauffage et 2quipement électrique héritent de Intelligent.

## Détails des commandes

- On peut créer des personnes et attacher des amis à ces personnes.
- On peut créer des résidences depuis une personne.
- On peut créer une résidence sans personne.
- On peut rattacher une résidence à une personne.
- On peut créer des équipements intelligents (chauffages ou autre équipements électriques).
- On peut rattacher des équipements intelligents à une résidence.
- On peut supprimer les personnes et garder les résidences au cas où une personne souhaiterai reprendre cette résidence.
- On supprimer des personnes.
- On peut supprimer des amis.
- On peut ajouter ou supprimer des éqquipements (chauffages ou autre équipements électriques)
- On peut afficher la liste de toutes les personnes.
- On peut afficher la liste de toutes les résidences.
- On peut afficer les lsite des tous les équipements (chauffages ou/et autre équipements électriques).

## Authors

* **Cavron Jérémy** - *Initial work* - [Portfolio](http://www.dbs.bzh/portfolio)


## Acknowledgments

* Project school. [Istic](https://istic.univ-rennes1.fr/) - university - Rennes 1.

* API documentation JSON [http://localhost:8080/v2/api-docs/](http://localhost:8080/v2/api-docs)
* API documentation WEB UI [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)