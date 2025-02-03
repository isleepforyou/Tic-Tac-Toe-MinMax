# Jeu de Tic Tac Toe

Ce projet implémente le jeu Tic Tac Toe en Java avec un joueur IA (CPU) utilisant les algorithmes **MinMax** et **Alpha-Beta** pour déterminer le meilleur coup. Le CPU ne perd jamais et garantit au minimum un match nul contre un adversaire jouant parfaitement.

## Structure du projet

- **Mark.java**  
  Contient l'énumération `Mark` qui définit les pièces du jeu : `X`, `O` et `EMPTY`.

- **Move.java**  
  Définit la classe `Move` qui représente un coup (les coordonnées ligne et colonne).

- **Board.java**  
  Représente le plateau de jeu.
    - `play(Move m, Mark mark)`: Place la pièce `mark` sur le plateau à la position indiquée par `m`.
    - `evaluate(Mark mark)`: Évalue l'état du plateau et retourne `100` pour une victoire, `-100` pour une défaite, et `0` pour un match nul.
    - `getAvailableMoves()`: Retourne la liste des coups possibles (cases vides).
    - `getGrid()`: Permet d'obtenir le tableau de jeu pour l'affichage (utilisé dans le fichier Test).

- **CPUPlayer.java**  
  Implémente le joueur IA qui utilise deux méthodes principales :
    - `getNextMoveMinMax(Board board)`: Retourne la liste des meilleurs coups selon l'algorithme MinMax.
    - `getNextMoveAB(Board board)`: Retourne la liste des meilleurs coups selon l'algorithme Alpha-Beta.  
      La variable `numExploredNodes` permet de suivre le nombre de nœuds explorés durant la recherche.

- **Test.java**  
  Contient une classe avec une méthode `main` permettant de tester le jeu.  
  Le programme demande à l'utilisateur d'entrer des coordonnées pour jouer (le joueur humain joue avec `X`), et le CPU joue avec `O`.

## Comment compiler et exécuter le code

1. **Ouvrir un terminal** dans le répertoire du projet (contenant tous les fichiers `.java`).

2. **Compiler le code**  
   Exécute la commande suivante pour compiler tous les fichiers Java :

   ```bash
   javac Mark.java Move.java Board.java CPUPlayer.java Test.java
3. **Lancer le test**
    Exécute la commande suivante pour lancer un test de jeu : 
    ```bash
   Java Test
