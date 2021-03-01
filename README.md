# Nine Men's Morris
This project is a group project for CS449 to introduce software design processes. We implement the (solved) game Nine Men's Morris using ```Java``` and created a GUI. The game can be played against a computer opponent or locally between two people.

## Project Members:
- Miller, Matt
- Salazar Castano, Paula
- Scheer, William
- Schneider, Richard
- Singh, Jashandeep

# Nine Men's Morris Rules
There is a seven-by-seven board with lines on it as below. The intersections of the lines mark the locations where a player's pieces can be placed. See: 

![A Nine Men's Morris Board is a seven-by-seven grid with only a handful of valid locations. These are the three-by-three squares for each size with the square connected at the middles of the sides.](Img/BlankBoard.png)

- There are two players to this game.
- Both start with nine pieces, called ``men."
- The goal is to form a ``mill" and remove all of the opponent's men. A mill is formed by three adjacent pieces connected by the paths. If a mill is formed, that player can remove any of the opponent's men that are NOT in a mill.
- The game starts with each player repeatedly placing a piece on the board in turn.
- Once all pieces are placed, the men on the board are allowed to move. Movement is restricted to one ``step" along the paths between locations. Men are not allows to jump over each other.
- When one of the player's is reduced to three pieces, both players are allowed to move their men to any available location. This is called ``flying."
- The game ends when either a player is reduced to two pieces or they are incapiable of making a valid move.
