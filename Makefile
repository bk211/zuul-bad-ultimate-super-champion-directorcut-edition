CC= javac
CCFLAGS = -Werror -Xlint:-deprecation

Main.class:	Main.java Command.class Player.class CommandWords.class Game.class GameModel.class GameView.class Parser.class Room.class UserInterface.class Item.class
	$(CC) $(CCFLAGS) Main.java

Item.class:	Item.java
	$(CC) $(CCFLAGS) $<

Command.class:	Command.java
	$(CC) $(CCFLAGS) $<
CommandWords.class:	CommandWords.java
	$(CC) $(CCFLAGS) $<
Game.class:	Game.java
	$(CC) $(CCFLAGS) $<
Player.class:	Player.java
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
RoomFileReader.class:	RoomFileReader.java
	$(CC) $(CCFLAGS) $<


run:
	java Main

clean:	
	rm -rf *.class

doc:	
	javadoc -d docprog -author -version -private -linksource *.java
	javadoc -d docuser -author -version *.java