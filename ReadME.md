# Java GUI Chess Engine
chess engine is a computer program that analyses chess positions and makes decisions on the best chess moves.
The chess engine decides what moves to make, it interacts directly with the user through graphical user interface (GUI) using the mouse or using voice by saying the position of current piece and the new position (still working on it).
## AI Algorithms
minimax and Alpha-Beta Algorithms are implemented in this Chess-Engine.these algorithms are formulated for two-player zero-sum game theory, covering both the cases where players take alternate moves and those where they make simultaneous moves.
### First: Minimax
Calculating the maximin value of a player is done in a worst-case approach: for each possible action of the player, we check all possible actions of the other players and determine the worst possible combination of actions - the one that gives player i the smallest value. Then, we determine which action player i can take in order to make sure that this smallest value is the largest possible.
### Second: Alpha-Beta
it is a search algorithm that seeks to decrease the number of nodes that are evaluated by the minimax algorithm in its search tree.It stops completely evaluating a move when at least one possibility has been found that proves the move to be worse than a previously examined move. 
