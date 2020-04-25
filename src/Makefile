run:
	make clean
	javac Game.java
	java Game

jar:
	make clean
	javac Game.javac
	jar cvmf MANIFEST.MF Game.jar Game.class pkg_commands/BackCommand.class pkg_commands/BeamCommand.class pkg_commands/DropCommand.class pkg_commands/EatCommand.class pkg_commands/GoCommand.class pkg_commands/HelpCommand.class pkg_commands/LookCommand.class pkg_commands/MineCommand.class pkg_commands/QuitCommand.class pkg_commands/TakeCommand.class pkg_commands/TestCommand.class pkg_data/Item.class pkg_data/Player.class pkg_data/Room.class pkg_mainStruct/GameModel.class pkg_mainStruct/GameView.class pkg_mainStruct/UserInterface\$1.class pkg_mainStruct/UserInterface.class pkg_tools/Command.class pkg_tools/CommandWord.class pkg_tools/CommandWords.class pkg_tools/Parser.class pkg_tools/RoomFileReader.class img/* cmd.txt explore_all.txt RoomData.csv ideal_run.txt 

clean:
	rm -rf Game.class
	rm -rf pkg_commands/*.class
	rm -rf pkg_data/*.class
	rm -rf pkg_tools/*.class
	rm -rf pkg_mainStruct/*.class
	