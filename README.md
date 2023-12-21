# Space Invaders OpenGL

## Description
Ce projet, "Space Invaders OpenGL", est un jeu de tir spatial basé sur Java. Il utilise JOGL pour le rendu graphique OpenGL. Le jeu comporte un cube contrôlé par le joueur (le 'bigCube') qui peut tirer des projectiles pour détruire les cubes ennemis. Le joueur doit esquiver les projectiles ennemis et éliminer tous les ennemis pour gagner.

## Caractéristiques
- Rendu graphique 3D utilisant JOGL (Java Binding for the OpenGL API).
- Cube contrôlé par le joueur avec capacités de mouvement horizontal et de tir.
- Plusieurs cubes ennemis qui descendent du haut de l'écran.
- Détection de collision entre les projectiles du joueur, les cubes ennemis et le cube du joueur.
- Scénarios de fin de jeu pour collision du joueur avec des projectiles ennemis ou des cubes ennemis.

## Comment Exécuter
Pour lancer le jeu, assurez-vous d'avoir installé Java et JOGL. Compilez et exécutez la classe `Main`. La fenêtre du jeu devrait s'ouvrir avec le cube du joueur en bas et les ennemis apparaissant du haut.

## Commandes
- Utilisez les touches fléchées gauche et droite pour déplacer le cube du joueur horizontalement.
- Cliquez avec la souris pour tirer des projectiles depuis le cube du joueur.

## Structure du Code
- `Main` : Le canevas principal du jeu et la boucle.
- `Cube` : Représente le cube du joueur et les cubes ennemis.
- `Projectile` : Représente les projectiles tirés par le joueur et les ennemis.
- `GraphicalObject` : Une classe abstraite qui fournit des attributs et méthodes de base pour les objets dessinables.
- `Square` : Une classe d'assistance utilisée pour créer les faces des cubes.

## Dépendances
- Java JDK
- JOGL (Java OpenGL)
