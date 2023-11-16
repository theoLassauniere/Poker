# Projet Poker - Team B

**[Lassauniere Théo](https://github.com/theoLassauniere), [Galli Evan](https://github.com/06Games),
[Lubrat Jilian](https://github.com/LubratJilian), [Michelozzi Antoine-Marie](https://github.com/mantoniu)**  
Polytech Nice-Sophia - SI3 PS5

------------------------

### Objectifs : Déterminer la main gagnante parmi 2 mains de Poker

### Bonus : Création d'un jeu de Texas Hold'em

Le but de ce projet est d’entrer 2 mains de poker de 5 cartes sur l’entrée standard et de renvoyer la main gagnante suivie de la raison de la victoire ou « égalité ».

Une erreur s’affiche et le jeu redémarre, si une des erreurs suivantes est rencontrée :
 - des cartes inconnues
 - un nombre de cartes entrées différent de 5
 - duplication de carte au sein de la même main
 - duplication de carte entre les mains
 
 Les types standards de mains de poker sont implémentés :

- Paire
- Double paire
- Brelan
- Suite
- Couleur
- Full
- Carré
- Quinte flush

Chacun de ces types de mains sont classés suivant la force ou suivant la règle de la carte
la plus haute dans le cas d’une égalité au niveau du type.

#### Démo de la comparaison de 2 mains :

![Demo_Poker](https://github.com/pns-si3-projects/dojo-poker-23-24-ps5-23-24-poker-b/assets/120557548/f0f1abb6-572d-42c9-ae9e-6a5c9165d280)


### Jeu de Texas Hold'em :

Ici, le but est de faire évoluer la comparaison entre 2 mains pour passer à un jeu entier de Texas Hold'em sans mises.
Les règles du jeu sont les suivantes :

- On tire 4 mains pour 4 joueurs de 2 cartes aléatoires
- Au premier tour, on tire 3 cartes aléatoires au milieu de la table (tour de jeu appelé flop)
- Au deuxième tour, on tire une carte supplémentaire au milieu de la table (tour de jeu appelé turn)
- Au troisième tour, on tire la dernière carte du milieu de table (tour appelé river)

Durant chacun des trois tours, on affiche la **meilleure main possible** pour chaque joueur.
Une fois que les 3 tours sont passés, on affiche le gagnant sur l'entrée standard, et la raison de sa victoire.


#### Démo du jeu de Texas Hold'em : 

![Demo_Jeu_Poker](https://github.com/pns-si3-projects/dojo-poker-23-24-ps5-23-24-poker-b/assets/120557548/84e39fa5-e35d-4886-9c00-c2224027ca0c)


