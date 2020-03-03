CC= javac

Main.class:	Main.java Command.class CommandWords.class Game.class GameModel.class GameView.class Parser.class Room.class UserInterface.class
	$(CC) Main.java

Command.class:	Command.java
	$(CC) $<
CommandWords.class:	CommandWords.java
	$(CC) $<
Game.class:	Game.java
	$(CC) $<
GameModel.class:	GameModel.java
	$(CC) $<
GameView.class:	GameView.java
	$(CC) $<
Parser.class:	Parser.java
	$(CC) $<
Room.class:	Room.java
	$(CC) $<
UserInterface.class:	UserInterface.java
	$(CC) $<

run:
	java Main

clean:	
	rm -rf Command.class CommandWords.class Game.class GameModel.class GameView.class Parser.class Room.class UserInterface.class Main.class
