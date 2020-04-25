CC= javac
CCFLAGS = -Werror
SRC = Parser.java CommandWord.java CommandWords.java Item.java GameModel.java Player.java RoomRandomizer.java TransporterRoom.java Room.java RoomFileReader.java GameView.java UserInterface.java Game.java Main.java Command.java LookCommand.java BeamCommand.java MineCommand.java GoCommand.java QuitCommand.java TakeCommand.java DropCommand.java HelpCommand.java TestCommand.java EatCommand.java BackCommand.java
OBJ = $(SRC:.c=.class)
Main.class:	$(OBJ)
	$(CC) $(CCFLAGS) Main.java

Parser.class:	Parser.java
	$(CC) $(CCFLAGS) $<
CommandWord.class:	CommandWord.java
	$(CC) $(CCFLAGS) $<
CommandWords.class:	CommandWords.java
	$(CC) $(CCFLAGS) $<
Item.class:	Item.java
	$(CC) $(CCFLAGS) $<
GameModel.class:	GameModel.java
	$(CC) $(CCFLAGS) $<
Player.class:	Player.java
	$(CC) $(CCFLAGS) $<
RoomRandomizer.class:	RoomRandomizer.java
	$(CC) $(CCFLAGS) $<
TransporterRoom.class:	TransporterRoom.java
	$(CC) $(CCFLAGS) $<
Room.class:	Room.java
	$(CC) $(CCFLAGS) $<
RoomFileReader.class:	RoomFileReader.java
	$(CC) $(CCFLAGS) $<
GameView.class:	GameView.java
	$(CC) $(CCFLAGS) $<
UserInterface.class:	UserInterface.java
	$(CC) $(CCFLAGS) $<
Game.class:	Game.java
	$(CC) $(CCFLAGS) $<
Command.class:	Command.java
	$(CC) $(CCFLAGS) $<
LookCommand.class:	LookCommand.java
	$(CC) $(CCFLAGS) $<
BeamCommand.class:	BeamCommand.java
	$(CC) $(CCFLAGS) $<
MineCommand.class:	MineCommand.java
	$(CC) $(CCFLAGS) $<
GoCommand.class:	GoCommand.java
	$(CC) $(CCFLAGS) $<
QuitCommand.class:	QuitCommand.java
	$(CC) $(CCFLAGS) $<
TakeCommand.class:	TakeCommand.java
	$(CC) $(CCFLAGS) $<
DropCommand.class:	DropCommand.java
	$(CC) $(CCFLAGS) $<
HelpCommand.class:	HelpCommand.java
	$(CC) $(CCFLAGS) $<
TestCommand.class:	TestCommand.java
	$(CC) $(CCFLAGS) $<
EatCommand.class:	EatCommand.java
	$(CC) $(CCFLAGS) $<
BackCommand.class:	BackCommand.java
	$(CC) $(CCFLAGS) $<


run:
	java Main

clean:	
	rm -rf *.class

doc:	
	javadoc -d docprog -author -version -private -linksource *.java
	javadoc -d docuser -author -version *.java