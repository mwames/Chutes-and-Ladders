import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class GameScreen extends JPanel
{
   private ArrayList<Player> playerList;
   private Player player1;
   private Player player2;
   private Player player3;
   private Player player4;
   private GameObject powerBar;
   private GameObject powerBarBorder;
   private int degrees;
   private BufferedImage img;
   private BufferedImage spinner;
   private Arrow arrow;
   JButton spinButton, newGameButton;
   private int playerTurn;

   JLabel ShowPlayerTurn;
   JLabel Blue;	// player 1 label
   JLabel Red; // player 2 label
   JLabel Black; // player 3 label
   JLabel Green; // player 4 label
	
   public GameScreen(String name)
   {
      playerList = new ArrayList<Player>();
      player1 = new Player(Color.BLUE, 1);
      player2 = new Player(Color.RED, 2);
      player3 = new Player(Color.BLACK, 3);
      player4 = new Player(Color.GREEN, 4);
      playerTurn = 0;
      powerBar = new GameObject(0, 0, Color.GREEN, Color.RED);
      powerBarBorder = new GameObject(0, 0);
      arrow = new Arrow();
      
   // Shows which player's turn it is (always starts with player 1)
      ShowPlayerTurn = new JLabel("Player " + 1 + "'s turn");	
      this.add(ShowPlayerTurn);
      ShowPlayerTurn.setVisible(true);
      ShowPlayerTurn.setSize(200,200);
      
      // Show which game piece each player is
      Blue = new JLabel("Player 1 is blue");
      this.add(Blue);
      Blue.setVisible(true);
      Blue.setSize(200,200);  
      
      Red = new JLabel("Player 2 is red");
      Red.setVisible(true);
      Red.setSize(200,200);
      
      Black = new JLabel("Player 3 is Black");
      Black.setVisible(true);
      Black.setSize(200,200);
      
      Green = new JLabel("Player 4 is Green");
      Green.setLocation(820, 70);  			  
      Green.setVisible(true);
      Green.setSize(200,200);
      
      //restarts game
      newGameButton = new JButton("New Game?");
      newGameButton.setVisible(true);
      this.add(newGameButton);
      newGameButton.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent e) 
               {
                  GameManager.frame.dispose();
                  GameManager.reStart();						
               }
            });
      
      spinButton = new JButton("Spin!");
      this.add(spinButton);
      spinButton.setVisible(true);
      spinButton.addActionListener(
            new ActionListener() 
            {
               public void actionPerformed(ActionEvent e) 
               {
                  if(arrow.getSpeed() == 0)
                  {
                     arrow.spin(powerBar);
                     updatePlayerTurn();
                  }
                  else
                     System.out.println("spin in progress");
               }
            });
      try 
      { 
         img = ImageIO.read(new File("images/GameBoard.gif"));
         spinner = ImageIO.read(new File("images/smallSpinner.png"));
      } 
      catch (IOException e) 
      { 
         System.out.println("bad file");
         e.printStackTrace();
      }
   }

   public void updateItems() 
   {


      for(Player player : playerList)
      {
         player.findXCoordinate();
         player.FindYCoordiante();
	         if(arrow.getSpeed() == 0 && this.playerTurn > GameManager.getNumberOfPlayers() && this.playerTurn <= (GameManager.getNumberOfPlayers() + GameManager.getNumberComputerPlayers()))
	         {
				arrow.spin(powerBar);
				updatePlayerTurn();
	         }
      }
      if(arrow.isValueReady())
      {
         for(Player player : playerList)
         {
            if(player.getPlayerNumber() == this.playerTurn)
            {
               player.updateLocation(arrow.getValue());
               arrow.setValueReady(false);
               ShowPlayerTurn.setText("Player " + this.playerTurn + "'s Turn");
            }
         }
      }
      powerBarBorder.updateBorder(this.getWidth(), this.getHeight());
      powerBar.updateGradient(powerBarBorder.getX(), powerBarBorder.getY(), powerBarBorder.getHeight());
      arrow.updateArrow(this.getWidth(), this.getHeight());
      spinButton.setLocation(this.getWidth() - 130 - spinButton.getWidth()/2, this.getHeight() - 240 - spinButton.getHeight()/2);
      newGameButton.setLocation(this.getWidth()- 130 -newGameButton.getWidth()/2, this.getHeight() - 20 - newGameButton.getHeight()/2);
      ShowPlayerTurn.setLocation(880, 570);
      Blue.setLocation(820,10);
      Red.setLocation(820,25);
      Black.setLocation(820,40);
      Green.setLocation(820,55);
   }
   public void move()
   {
      for(Player player : playerList)
      {
         player.move();
      }
      powerBar.grow();
   }
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.drawImage(img, 0, 0, 800, 800, null);
      g2d.drawImage(spinner, this.getWidth() - 230, this.getHeight() - 230, 200, 200, null);
      g2d.setColor(Color.WHITE);
      g2d.fillOval(this.getWidth() - 130, this.getHeight() - 130, 2, 2);
      for(Player player : playerList)
      {
         player.paint(g2d);
      }
      powerBar.paint(g2d);
      powerBarBorder.paint(g2d);
      arrow.paint(g2d);
   }
   public void setInitial()
   {
      powerBar.setY(powerBarBorder.getY());
      arrow.setInitial(this.getWidth(), this.getHeight());
      player1.setInitialPosition();
      player2.setInitialPosition();
      player3.setInitialPosition();
      player4.setInitialPosition();
   }
   public void addPlayers(int numberOfPlayers, int numberComputerPlayers)
   {
      switch(numberOfPlayers + numberComputerPlayers)//fallthrough is intentional
      {
      case 4: 
    	  playerList.add(player4);
    	  this.add(Green);
      case 3: 
    	  playerList.add(player3);
    	  this.add(Black);
      case 2: 
    	  playerList.add(player2);
    	  this.add(Red);
      case 1: 
    	  playerList.add(player1);
      }
    	  	
   }
   public void updatePlayerTurn()
   {
   	
      if(this.playerTurn == (GameManager.getNumberOfPlayers()+GameManager.getNumberComputerPlayers()))
         this.playerTurn = 1;
      else
         this.playerTurn++;
   }
   public int getCurrentPlayer()
   {
      return playerTurn;
   }
}
