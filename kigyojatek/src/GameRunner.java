
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class GameRunner
{
	
	// Size of the grid
	final static byte	gridsize	= 20;
	// the main window
	MainWindow	mainwin;
	
	int			delay;
	private final static  int DEFAULTDELAY=250 ;
	
	public GameRunner()
	{
		super();
		delay = DEFAULTDELAY;
	}
	
	public void run()
	{
		mainwin = new MainWindow();
		LinkedList<SnakeBodyPart> snake = new LinkedList<SnakeBodyPart>();
		MenuClickListener clistener = new MenuClickListener();
		//if (mainwin != null)
		//	System.out.println("mainwin");
		mainwin.getjMenuBar().getMenu(0).getItem(0)
				.addActionListener(clistener);
		mainwin.getjMenuBar().getMenu(0).getItem(1)
				.addActionListener(clistener);
		mainwin.getjMenuBar().getMenu(1).getItem(0)
				.addActionListener(clistener);
		mainwin.getjMenuBar().getMenu(1).getItem(1)
				.addActionListener(clistener);
		mainwin.getjMenuBar().getMenu(2).getItem(0)
				.addActionListener(clistener);
		mainwin.getjMenuBar().getMenu(2).getItem(1)
				.addActionListener(clistener);
		mainwin.getjMenuBar().getMenu(2).getItem(2)
		.addActionListener(clistener);
		// System.out.println( m.menuBar.getMenu(1).getItem(0).get);
		
		snake.add(new SnakeBodyPart((byte) 4, (byte) 2));
		snake.add(new SnakeBodyPart((byte) 3, (byte) 2));
		snake.add(new SnakeBodyPart((byte) 2, (byte) 2));
		
		mainwin.getTable().getSnakegame().setSnake( snake);
		mainwin.getTable().getSnakegame().setTablesize( gridsize);
		
		mainwin.getTable().repaint();
		
		game_foodsver();
		
	}
	
	public static void main(String[] args)
	{
		// System.out.println("STart");
		new GameRunner().run();
		/**
		 * @TODO 1. legyen egy játék start gomb. pipa 
		 * 		 2. pontszámláló pipa 
		 * 		 3. változtatható skinek helyett theme, pipa 
		 *       4. többféle kaja 
		 *       5. ideig ott lévõ kaja 
		 *       6. kajálás effekt 
		 *       7. kígyó hossz után gyorsuljon pipa
		 *       8. legyenek szintek 
		 *       9. játékmódok 
		 *       10. pontszámláló  pipa 
		 *       11. normálisabb gui pipa 
		 *       12. help menü pipa 
		 *       13. score számláló  pipa 
		 *       14. Image abszrakt osztály 
		 *       15. Enum osztályok megfelelõ osztályba olvadjanak be 
		 *       16. Különbözõ végek esetén különbözõ képek. 
		 *       17. score tábla, avagy toplista 
		 *       18. idõmérõ 
		 *       19. costum game 
		 *       20. idõre gyorsuljon 
		 *       21. idõrõl eltünõ cucc, ami pontcountert számol le, ha 0ra ér eltünik. 
		 *       22. lehessen pause-t nyomni, ha lemegy a fókusz a játékról akkor autopause 
		 *       23. legyenek benne achivmentek. 
		 *       24. több profilos legyen 
		 *       25. multiplayer legyen benne. 
		 *       26. mozgó kaja 
		 *       27. akadályok 
		 *       28. legyen ini fájl 
		 *       29. legyen zene és hangok 
		 *       30. evés elõtt nyiljon ki a szája 
		 *       31. varázs kajánál animációt vált. 
		 *       32. stack  overfló bug a random miatt. javítva
		 *       33. bug, a dinnye ott marad. javítva
		 * */
	}
	/**
	 * Buglist: 
	 * 1. az új test megtöri a képet fixed
	 * 2. új test lukat csinál néha, amin át lehet menni anélkül hogy véget érne a játék
	 * 3. a dupla kaja nem figyeli, hogy ha a másik kajával volt ütközés fixed
	 * 4. nincs nyú gém
	 * 5. 
	 */
	public void newGame()
	{
		delay = DEFAULTDELAY;
		//mainwin.setNewgame(false);
		//System.out.println(mainwin.getTable().getSnakegame().snake.size());
		int meret = mainwin.getTable().getSnakegame().getSnake().size();
		for (int k = 3; k < meret; k++)
		{
			mainwin.getTable().getSnakegame().getSnake().removeLast();
		}
		//System.out.println(mainwin.getTable().getSnakegame().snake.size());
		mainwin.getTable().getSnakegame().getSnake().get(0).setState((byte) 4,
				(byte) 2, SnakeBodyPart.Direction.RIGHT);
		mainwin.getTable().getSnakegame().getSnake().get(1).setState((byte) 3,
				(byte) 2, SnakeBodyPart.Direction.RIGHT);
		mainwin.getTable().getSnakegame().getSnake().get(2).setState((byte) 2,
				(byte) 2, SnakeBodyPart.Direction.RIGHT);
		mainwin.repaint();
		System.out.println("repaint");
		game_foodsver();
		
	}
	public void newGame2()
	{
		mainwin.setVisible(false);
		delay = DEFAULTDELAY;
		mainwin.setNewgame(true);
		new GameRunner().run();
	}	
	public void game_foodsver(){
		/**
		 * Wait until the player click on the start.
		 */
		while (true)
		{
			if (mainwin.getStarted() == true)
				break;
			try
			{
				Thread.sleep(delay);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * The snake mover.
		 */
		while (true)
		{
			try
			{
				Thread.sleep(delay);
				
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mainwin.getTable().getSnakegame().step_foodsver();
			mainwin.getTable().repaint();
			mainwin.getTable().getSnakegame().eatenFoodAtTail();
			
			if (mainwin.getNewgame() == true)
				break;
			if (mainwin.getTable().getSnakegame().isEaten())
			{
				mainwin.getTable().getSnakegame().setEaten(false);
			} else
			{
				if (mainwin.getTable().getSnakegame().isHeadInBody())
				{
					mainwin.gameOverBite();
					break;
				}
			}
			
			if (!mainwin.getTable().getSnakegame().isThereNextStep())
			{
				mainwin.gameOverStun();
				
				break;
				
			}
			// if (mainwin.getTable().getSnakegame().getScore()==100)
			if (mainwin.getTable().getSnakegame().getScore() == 100
					&& mainwin.getTable().getSnakegame().isScorerised())
			{
				delay /= 2;
				mainwin.getTable().getSnakegame().setScorerised(false);
			} else if (mainwin.getTable().getSnakegame().getScore() == 200
					&& mainwin.getTable().getSnakegame().isScorerised())
			{
				delay /= 2;
				mainwin.getTable().getSnakegame().setScorerised(false);
			}
			//if (mainwin.pause)System.out.println("pause in the runner");
			while (mainwin.getTable().isPause()){
				//System.out.println("pause in the runner");
				try
				{
					Thread.sleep(delay);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		// System.out.println("Vége a játéknak!");
	}
	
	public void game()
	{
		/**
		 * Wait until the player click on the start.
		 */
		while (true)
		{
			if (mainwin.getStarted() == true)
				break;
			try
			{
				Thread.sleep(delay);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * The snake mover.
		 */
		while (true)
		{
			try
			{
				Thread.sleep(delay);
				
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mainwin.getTable().getSnakegame().step();
			mainwin.getTable().repaint();
			if (mainwin.getNewgame() == true)
				return;
			if (mainwin.getTable().getSnakegame().isEaten())
			{
				mainwin.getTable().getSnakegame().setEaten(false);
			} else
			{
				if (mainwin.getTable().getSnakegame().isHeadInBody())
				{
					mainwin.gameOverBite();
					break;
				}
			}
			
			if (!mainwin.getTable().getSnakegame().isThereNextStep())
			{
				mainwin.gameOverStun();
				
				break;
				
			}
			// if (mainwin.getTable().getSnakegame().getScore()==100)
			if (mainwin.getTable().getSnakegame().getScore() == 100
					&& mainwin.getTable().getSnakegame().isScorerised())
			{
				delay /= 2;
				mainwin.getTable().getSnakegame().setScorerised(false);
			} else if (mainwin.getTable().getSnakegame().getScore() == 200
					&& mainwin.getTable().getSnakegame().isScorerised())
			{
				delay /= 2;
				mainwin.getTable().getSnakegame().setScorerised(false);
			}
			
		}
		// System.out.println("Vége a játéknak!");
	}
	
	/** Action listener for the upper menu */
	private class MenuClickListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			// m.menuBar.
			// System.out.println(m.menuBar.getMenu(0).getItem(1).toString());
			//System.out.println(e.getSource().toString());
			mainwin.getTable().setPause(true);
			if (e.getSource().equals(
					mainwin.getjMenuBar().getMenu(0).getItem(1)))
			{
				// System.out.println("file exit");
				System.exit(0);
				return;
			}
			if (e.getSource().equals(
					mainwin.getjMenuBar().getMenu(0).getItem(0)))
			{
				// System.out.println(m.menuBar.getMenu(0).getItem(0));
				
				// System.exit(0);
				// m.newgame=false;
				newGame();
				return;
			}
			if (e.getSource().equals(
					mainwin.getjMenuBar().getMenu(2).getItem(1)))
			{
				// System.out.println(
				// mainwin.getjMenuBar().getMenu(2).getItem(1).getName());
				mainwin.showAboutBox();
				return;
			}
			if (e.getSource().equals(
					mainwin.getjMenuBar().getMenu(2).getItem(0)))
			{
				
				mainwin.showHelpBox();
				return;
			}
			if (e.getSource().equals(
					mainwin.getjMenuBar().getMenu(2).getItem(2)))
			{
				//System.out.println("version");
				mainwin.showVersionBox();
				return;
			}
			if (e.getSource().equals(
					mainwin.getjMenuBar().getMenu(1).getItem(1)))
			{
				mainwin.showOptionsWin();
				
				return;
			}
			if (e.getSource().equals(
					mainwin.getjMenuBar().getMenu(1).getItem(0)))
			{
				return;
			}
			
		}
		
	}
}
