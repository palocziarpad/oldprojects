import java.util.LinkedList;


public class Game
{
	
	private LinkedList<SnakeBodyPart>		snake;
	private byte								tablesize;
	private static SnakeBodyPart			food[];
	private int								score;
	private boolean 						scorerised;
	private boolean							eaten;
	private boolean 						showfood2;
	private SnakeBodyPart.Direction						direction;
	private SnakeBodyPart			newpart;
	private LinkedList<SnakeBodyPart>		kaja2;
	private LinkedList<SnakeBodyPart>		eatenFood;
	private GameOver	gameover;
	
	static{
		food = new SnakeBodyPart[2];
		food[0] = new SnakeBodyPart((byte) 5, (byte) 8);
		food[1] = new SnakeBodyPart((byte) 20, (byte) 20);
	}
	{
		kaja2=new LinkedList<SnakeBodyPart>();
		eatenFood=new LinkedList<SnakeBodyPart>();
		kaja2.add(new SnakeBodyPart((byte) 5, (byte) 8));
	}
	
	/*public void initPosition(byte tablameret)
	{
		this.tablesize = tablameret;
		snake=new LinkedList<SnakeBodyPart>();
		// 3 hosszúságú lesz a kígyó
		getSnake().add(new SnakeBodyPart((byte) 5, (byte) 5));
		getSnake().add(new SnakeBodyPart((byte) 5, (byte) 6));
		getSnake().add(new SnakeBodyPart((byte) 5, (byte) 7));
	}*/
	
	public void reinit(){
		
		/*kaja2.removeAll(null);
		kaja2.add(new SnakeBodyPart((byte) 5, (byte) 8));
		eatenFood.removeAll(null);
		*/
		kaja2=new LinkedList<SnakeBodyPart>();
		eatenFood=new LinkedList<SnakeBodyPart>();
		kaja2.add(new SnakeBodyPart((byte) 5, (byte) 8));
		score=0;
		gameover=GameOver.INGAME;
	}
	
	public Game()
	{
		super();
		showfood2=scorerised=eaten=false;
		gameover=GameOver.INGAME;
		this.setSnake(new LinkedList<SnakeBodyPart>());
		score=tablesize = 0;
		newpart = new SnakeBodyPart((byte) 5, (byte) 8);
		//food[0] = new SnakeBodyPart((byte) 5, (byte) 8);
	}
	
	public static byte random(byte from, byte to)
	{
		return (byte) ((Math.random() * (to - from)) + from);
	}
	
	public void step()
	{
		/**
		 * We use the snake tail (last element) 
		 * to be the new head, thats how it moves.
		 */
		// Kigyotestresz uj=new Kigyotestresz
		// (kigyotest.getFirst().getX(),kigyotest.getFirst().y);
		SnakeBodyPart uj = getSnake().getLast();
		uj.setX(getSnake().getFirst().getX());
		uj.setY(getSnake().getFirst().getY());
		uj.setPartKind(SnakeBodyPart.Parts.HEAD);
		if (getDirection() != null)
		{
			if (getSnake().get(1).getIrany().equals(getDirection().opposite()))
			{
				// System.out.println(snake.get(1).getIrany()+" dir:"+direction+" opp:"+direction.opposite());
			} else
				getSnake().getFirst().setIrany(getDirection());
			setDirection(null);
		}
		switch (getSnake().getFirst().getIrany())
		{
		// go to left
		case LEFT:
		{
			uj.setX((byte) (uj.getX() - 1));
			uj.setDir(SnakeBodyPart.Direction.LEFT);
		}
			break;
		// go to right
		case RIGHT:
		{
			uj.setX((byte) (uj.getX() + 1));
			uj.setDir(SnakeBodyPart.Direction.RIGHT);
		}
			break;
		// go down
		case DOWN:
		{
			uj.setY((byte) (uj.getY() + 1));
			uj.setDir(SnakeBodyPart.Direction.DOWN);
		}
			break;
		// go up
		case UP:
		{
			uj.setY((byte) (uj.getY() - 1));
			uj.setDir(SnakeBodyPart.Direction.UP);
		}
			break;
		}
		// if (kajal() ) return;
		//eating();
		
		eating2food();
		getSnake().removeLast();
		getSnake().addFirst(uj);
		
	}
	
	public void step_foodsver()
	{
		/**
		 * We use the snake tail (last element) 
		 * to be the new head, thats how it moves.
		 */
		// Kigyotestresz uj=new Kigyotestresz
		// (kigyotest.getFirst().getX(),kigyotest.getFirst().y);
		SnakeBodyPart uj = getSnake().getLast();
		uj.setX(getSnake().getFirst().getX());
		uj.setY(getSnake().getFirst().getY());
		uj.setPartKind(SnakeBodyPart.Parts.HEAD);
		if (getDirection() != null)
		{
			if (getSnake().get(1).getIrany().equals(getDirection().opposite()))
			{
				// System.out.println(snake.get(1).getIrany()+" dir:"+direction+" opp:"+direction.opposite());
			} else
				getSnake().getFirst().setIrany(getDirection());
			setDirection(null);
		}
		switch (getSnake().getFirst().getIrany())
		{
		// go to left
		case LEFT:
		{
			uj.setX((byte) (uj.getX() - 1));
			uj.setDir(SnakeBodyPart.Direction.LEFT);
		}
			break;
		// go to right
		case RIGHT:
		{
			uj.setX((byte) (uj.getX() + 1));
			uj.setDir(SnakeBodyPart.Direction.RIGHT);
		}
			break;
		// go down
		case DOWN:
		{
			uj.setY((byte) (uj.getY() + 1));
			uj.setDir(SnakeBodyPart.Direction.DOWN);
		}
			break;
		// go up
		case UP:
		{
			uj.setY((byte) (uj.getY() - 1));
			uj.setDir(SnakeBodyPart.Direction.UP);
		}
			break;
		}
		// if (kajal() ) return;
		//eating();
		
		eatingfoods();
		getSnake().removeLast();
		getSnake().addFirst(uj);
		
	}
	
	public boolean isThereNextStep()
	{
		// if (tablameret==0) return false;
		newpart.setX(getSnake().getFirst().getX());
		newpart.setY(getSnake().getFirst().getY());
		switch (getSnake().getFirst().getIrany())
		{
		// balra
		case LEFT:
			newpart.setX((byte) (newpart.getX() - 1));
			break;
		// jobbra
		case RIGHT:
			newpart.setX((byte) (newpart.getX() + 1));
			break;
		// le
		case DOWN:
			newpart.setY((byte) (newpart.getY() + 1));
			break;
		// fel
		case UP:
			newpart.setY((byte) (newpart.getY() - 1));
			break;
		}
		// ha kilépett a tábla keretbõl
		if (newpart.getX() < -1 || newpart.getX() > tablesize || newpart.getY() < -1
				|| newpart.getY() > tablesize)
		{
		
			return false;
		}
		return true;
	}
	
	public boolean eating()
	{
		if (getSnake().getFirst().isAt(food))
		{
			food[0].setDir(getSnake().getFirst().getIrany());
			getSnake().addFirst(new SnakeBodyPart(food[0].getX(), food[0].getY()));
			getSnake().getFirst().setIrany(food[0].getDir());
			
			newFoodPlace();
			scoreInc();
			setEaten(true);
			
			return true;
		}
		return false;
		
	}
	public boolean eating2food()
	{
		if (getSnake().getFirst().isAt(food,1)>-1)
		{
			food[getSnake().getFirst().isAt(food,1)].setDir(getSnake().getFirst().getIrany());
			
			//getSnake().add(0,new SnakeBodyPart(food[getSnake().getFirst().isAt(food,1)].getX(), food[getSnake().getFirst().isAt(food,1)].getY(),SnakeBodyPart.Parts.NEWPART));
			
			getSnake().addFirst(new SnakeBodyPart(food[getSnake().getFirst().isAt(food,1)].getX(), food[getSnake().getFirst().isAt(food,1)].getY(),SnakeBodyPart.Parts.NEWPART));
			getSnake().getFirst().setIrany(food[getSnake().getFirst().isAt(food,1)].getDir());
			if (getSnake().getFirst().isAt(food,1)!=1) newFoodPlace();
			scoreInc();
			/**
			 * food2
			 */
			/**/
			if (score==20) {
				newFoodPlace(food[1]);
				showfood2=true;
			}
			if (getSnake().getFirst().isAt(food,1)==1)
				{
					showfood2=false;
					//set the food 2 out of the board
					food[1].setX((byte)20);
					food[1].setY((byte)20);
				}
			/**/
			setEaten(true);
			
			return true;
			
		}
		return false;
		
	}
	
	public boolean eatingfoods()
	{
		int whichfood=getSnake().getFirst().isAt(kaja2,1);
		SnakeBodyPart sbp;
		if (whichfood>-1)
		{
			sbp=kaja2.get(whichfood);
			//System.out.println("hablaty");
			sbp.setDir(snake.getLast().getIrany());
			//food[getSnake().getFirst().isAt(food,1)].setDir(getSnake().getFirst().getIrany());
			eatenFood.addFirst(new SnakeBodyPart(sbp.getX(), sbp.getY(),SnakeBodyPart.Parts.NEWPART));
			//eatenFood.getFirst().setIrany(kaja2.get(whichfood).getDir());
			
			//snake.addFirst(new SnakeBodyPart(food[getSnake().getFirst().isAt(food,1)].getX(), food[getSnake().getFirst().isAt(food,1)].getY(),SnakeBodyPart.Parts.NEWPART));
			//snake.getFirst().setIrany(food[getSnake().getFirst().isAt(food,1)].getDir());
			if (whichfood==0) newFoodsPlace(whichfood);
			else if (whichfood==1) kaja2.removeLast();
			scoreInc();
			/**
			 * food2
			 */
			/**/
			if (score==20) {
				kaja2.addLast(new SnakeBodyPart(snake.getLast().getX(), snake.getLast().getY()));
				newFoodsPlace(1);
				
			}
			/*if (getSnake().getFirst().isAt(food,1)==1)
				{
					showfood2=false;
					//set the food 2 out of the board
					food[1].setX((byte)20);
					food[1].setY((byte)20);
				}*/
			/**/
			setEaten(true);
			
			return true;
			
		}
		return false;
		
	}
	public void scoreInc()
	{
		score+=10;
		setScorerised(true);
		
	}
	
	public void newFoodPlace_old()
	{
		food[0].setX(random((byte) 0, tablesize));
		food[0].setY(random((byte) 0, tablesize));
		for (int k = 0; k < getSnake().size(); k++)
		{
			if (getSnake().get(k).isAt(food))
			{
				newFoodPlace();
				break;
			}
		}
	}
	public void newFoodPlace_old(SnakeBodyPart food)
	{
		food.setX(random((byte) 0, tablesize));
		food.setY(random((byte) 0, tablesize));
		for (int k = 0; k < getSnake().size(); k++)
		{
			if (getSnake().get(k).isAt(food))
			{
				newFoodPlace_old(food);
				break;
			}
		}
	}
	public void newFoodsPlace(int which)
	{
		
		boolean collison=false;
		do{
			kaja2.get(which).setState(random((byte) 0, tablesize), random((byte) 0, tablesize), null);
			collison=false;
			//System.out.println("random x" +kaja2.get(which).getX()+" y:"+kaja2.get(which).getY());
			for (int k = 0; k < getSnake().size(); k++)
			{
				if (snake.get(k).isAt(kaja2.get(which)))
				{
					collison=true;
					break;
				}
			}
			if (collison) continue;
			for (int k = 0; k < kaja2.size(); k++)
			{
				if (k==which) continue;
				if (kaja2.get(k).isAt(kaja2.get(which)))
				{
					collison=true;
					break;
				}
			}
		}
		while (collison);
	}
	public void newFoodPlace()
	{
		
		boolean collison=false;
		do{
			food[0].setX(random((byte) 0, tablesize));
			food[0].setY(random((byte) 0, tablesize));
			
			collison=false;
			for (int k = 0; k < getSnake().size(); k++)
			{
				if (getSnake().get(k).isAt(food))
				{
					collison=true;
					break;
				}
			}
			/*for (int k = 1; k < food.length; k++)
			{
				if (food[k].isAt(food))
				{
					collison=true;
					continue;
				}
			}*/
		}
		while (collison);
	}
	public void newFoodPlace(SnakeBodyPart food)
	{
		boolean collison=false;
		do{
			food.setX(random((byte) 0, tablesize));
			food.setY(random((byte) 0, tablesize));
			collison=false;
			for (int k = 0; k < getSnake().size(); k++)
			{
				if (getSnake().get(k).isAt(food))
				{
					collison=true;
				}
			}
		}
		while (collison);
	}
	
	public boolean isHeadInBody()
	{
		for (int k = 1; k < getSnake().size(); k++)
		{
			if (getSnake().get(k).isAt(getSnake().get(0)))
			{
				// System.out.println(snake.get(k).x + "" + snake.get(k).y
				// + "" + snake.get(0).x + "" + snake.get(0).y
				// + "");
				return true;
			}
		}
		return false;
	}

	public void eatenFoodAtTail(){
		SnakeBodyPart tail=snake.getLast();
		int whichfood;
		whichfood=tail.isAt(eatenFood, 0);	
		if(whichfood>-1){
				snake.addLast(eatenFood.get(whichfood));
				snake.getLast().setDir(tail.getIrany());
				eatenFood.remove(whichfood);
			}
		
	}
	
	public void setSnake(LinkedList<SnakeBodyPart> snake)
	{
		this.snake = snake;
	}

	public LinkedList<SnakeBodyPart> getSnake()
	{
		return snake;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public int getScore()
	{
		return score;
	}

	public void setDirection(SnakeBodyPart.Direction direction)
	{
		this.direction = direction;
	}

	public SnakeBodyPart.Direction getDirection()
	{
		return direction;
	}

	public void setEaten(boolean eaten)
	{
		this.eaten = eaten;
	}

	public boolean isEaten()
	{
		return eaten;
	}

	public void setScorerised(boolean scorerised)
	{
		this.scorerised = scorerised;
	}

	public boolean isScorerised()
	{
		return scorerised;
	}
	public static SnakeBodyPart[] getFood()
	{
		return food;
	}
	
	public static void setFood(SnakeBodyPart[] food)
	{
		Game.food = food;
	}
	
	public byte getTablesize()
	{
		return tablesize;
	}
	
	public void setTablesize(byte tablesize)
	{
		this.tablesize = tablesize;
	}
	public boolean isShowfood2()
	{
		return showfood2;
	}

	public void setShowfood2(boolean showfood2)
	{
		this.showfood2 = showfood2;
	}

	public LinkedList<SnakeBodyPart> getKaja2()
	{
		return kaja2;
	}

	public void setKaja2(LinkedList<SnakeBodyPart> kaja2)
	{
		this.kaja2 = kaja2;
	}

	public LinkedList<SnakeBodyPart> getEatenFood()
	{
		return eatenFood;
	}

	public void setEatenFood(LinkedList<SnakeBodyPart> eatenFood)
	{
		this.eatenFood = eatenFood;
	}
	
	public GameOver getGameover()
	{
		return gameover;
	}

	public void setGameover(GameOver gameover)
	{
		this.gameover = gameover;
	}

	public enum GameOver{
		INGAME,BITE,WALL
	}
}
