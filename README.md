# Projet Poker - Team B

**[Lassauniere Théo](https://github.com/theoLassauniere), [Galli Evan](https://github.com/06Games),
[Lubrat Jilian](https://github.com/LubratJilian), [Michelozzi Antoine-Marie](https://github.com/mantoniu)**  
Polytech Nice-Sophia - SI3 PS5

------------------------

### Objectifs : Déterminer la main gagnante parmi 2 mains de Poker

### Bonus : Création d'un jeu de Texas Hold'em

Dans ce projet le but est de créer un programme qui demande d’entrer 2 mains de poker de 5 cartes sur l’entrée standard
et renvoi la main gagnante suivie de la raison de la victoire ou « égalité ». S’il y a une erreur dans les mains
rentrées que ça soit un problème de duplication de carte entre les mains ou au sein de la même main, un nombre de cartes
par mains différent de 5, des cartes inconnues, une erreur s’affichera et le jeu redémarrera.  
Tous les types de mains de poker sont implémentés :

- Paire
- Double paire
- Brelan
- Suite
- Couleur
- Full
- Carré
- Quinte flush

Ainsi chacun de ces types de mains peuvent être et sont ensuite classé suivant la force ou suivant la règle de la carte
la plus haute dans le cas d’une égalité au niveau du type.

#### Démo de la comparaison de 2 mains :

### Jeu de Texas Hold'em :

Ici, le but est de faire évoluer la comparaison entre 2 mains pour passer à un jeu entier de Texas Hold'em sans mises.
Les règles du jeu sont les suivantes :

- On tire X mains de 2 cartes aléatoires, où X représente le nombre de joueurs,
- Au premier tour, on tire 3 cartes aléatoires au milieu de la table (tour de jeu appelé flop),
- Au deuxième tour, on tire une carte supplémentaire au milieu de la table (tour de jeu appelé turn)
- Au troisième tour, on tire la dernière carte du milieu de table (tour appelé river)

Durant chacun des trois tours, on affiche la **meilleure main possible** pour chaque joueur.
Une fois que les 3 tours sont passés, on affiche le gagnant sur l'entrée standard, et la raison de sa victoire.

#### Démo du jeu de Texas Hold'em : 


