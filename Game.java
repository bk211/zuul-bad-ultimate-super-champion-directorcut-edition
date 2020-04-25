import pkg_mainStruct.GameModel;
import pkg_mainStruct.GameView;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Game 
{
    private GameModel gameModel;
    private GameView gameView;


    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        gameModel = new GameModel();
        
        gameView = new GameView(gameModel);
        gameModel.addObserver(gameView);
        gameModel.addGameView(gameView);

    }

    /**
     *  Main play routine.
     */
    public void launch() 
    {            
        gameModel.play();    
    }

    
    public static void main(String[] args) {
        Game g = new Game();
        g.launch();
    }

}
