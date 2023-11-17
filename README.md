# Projet Persistence
## Description
Ce projet est réalisé dans le cadre du cours Patrons et Composants.
Le but de ce projet est de mettre en pratique les différents patrons de conception vus en cours mais aussi la mise en place de test.
Ces différentes pratiques sont appliquées à travers un projet d'éditeur graphique.

### Patrons de conception
#### Patron visiteur : 
Pour réaliser la persistence des formes à travers des exports en XML et JSON, nous avons utlisé le patron visiteur.

![Patron Visiteur](Doc%2FVisiteur.svg)
> Toutes les attributs et méthodes non nécessaires à la compréhension du patron ont été retirés des diagrammes.

Pour implémenter le patron *Visiteur*, nous avons donc une classe abstraite `Visitor` qui définit les méthodes `visit` pour chaque type de forme.
Ainsi que les classes `XMLVisitor` et `JSONVisitor` qui implémentent ces méthodes.

Nous avons ensuite une interface `Visitable` qui définit la méthode `accept` qui permet d'accepter un visiteur.
Et l'interface `Shape` qui définit la méthode `draw` pour dessiner la forme. (NB : Cette interface n'est pas liée au patron *Visiteur*.)

Puis, nous avons la classe abstraite `AbstractShape` qui implémente l'interface `Visitable` et `SimpleShape`.
Enfin, nous avons les classes `Circle`, `Rectangle`, `Triangle` et `GroupShape` qui héritent de `AbstractShape` et implémentent l'interface `Shape`.
Les forment implémentent donc la méthode `accept` qui appelle la méthode `visit` du visiteur passé en paramètre.

Dans notre projet, c'est la classe `Saver` qui fait office de client pour le patron *Visiteur*.

#### Patron composite :
