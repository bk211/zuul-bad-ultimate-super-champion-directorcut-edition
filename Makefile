CC= javac
CCFLAGS = -Werror

Main.class:	Main.java Command.class CommandWords.class Game.class GameModel.class GameView.class Parser.class Room.class UserInterface.class Item.class
	$(CC) Main.java

Item.class:	Item.java
	$(CC) $(CCFLAGS) $<

Command.class:	Command.java
	$(CC) $(CCFLAGS) $<
CommandWords.class:	CommandWords.java
	$(CC) $(CCFLAGS) $<
Game.class:	Game.java
	$(CC) $(CCFLAGS) $<
GameModel.class:	GameModel.java
	$(CC) $(CCFLAGS) $<
GameView.class:	GameView.java
	$(CC) $(CCFLAGS) $<
Parser.class:	Parser.java
	$(CC) $(CCFLAGS) $<
Room.class:	Room.java
	$(CC) $(CCFLAGS) $<
UserInterface.class:	UserInterface.java
	$(CC) $(CCFLAGS) $<

run:
	java Main

clean:	
	rm -rf Command.class CommandWords.class Game.class GameModel.class GameView.class Parser.class Room.class UserInterface.class Main.class
