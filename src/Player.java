import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Player 
{
   private int currentX;
   private int currentY;
   private int calculatedX;
   private int calculatedY;
   private int offsetX;
   private int offsetY;
   private int height;
   private int width;
   private Color color1;
   private int rate;
   private int playerNumber;
   private int location;
   private int offset;
   private Scanner reader;
   private MoveGuide guide;
   private boolean winner = false;
   private int moveDelay = 0; 

   
   public Player(Color color, int playerNumber) //for solid fill
   {
      reader = new Scanner(System.in);
      switch(playerNumber)
      {
         case 1:
            this.offsetX = -15;
            this.offsetY = 15;
            break;
         case 2:
            this.offsetX = 15;
            this.offsetY = 15;
            break;
         case 3:
            this.offsetX = -15;
            this.offsetY = -15;
            break;
         case 4:
            this.offsetX = 15;
            this.offsetY = -15;
            break;         
      }
      
      this.currentX = 0;
      this.currentY = 0;
      this.height = 30;
      this.width = 30;
      this.color1 = color;
      this.playerNumber = playerNumber;
      this.location = 0;
      guide = new MoveGuide();
   }
	
	
   public void findXCoordinate()
   {
      int temp = 0;
      boolean even = false;
   	
      if((this.location/10)%2 == 0)
         even = true;
      else
         even = false;
   	
   	
      if(this.location > 9)
         temp = this.location % 10;
      else
         temp = this.location;
   		
      switch(temp)
      {
         case 6:
         	case 5: offset = 0;
            break;
         case 7:	
         	case 4: offset = 1;
            break;
         case 8: 
         	case 3: offset = 2;
            break;
         case 9:
         	case 2: offset = 3;
            break;
         case 1:
         	case 0: offset = 4;
            break;
      }
   		
      if((even && temp < 6) || (!even && temp > 5))
         this.calculatedX = 355 - (80 * offset);
      if((even && temp > 5) || (!even && temp < 6)) 
         this.calculatedX = 435 + (80 * offset);   
   }
	
   public void FindYCoordiante()
   {
      int tempY = (this.location / 10);
      if(!(this.location % 10 == 0))
         tempY++;
      this.calculatedY = 780 - tempY * 80 + 40;
   }
   
   public void paint(Graphics2D g)
   {
      g.setColor(this.color1);
      g.fillOval(currentX, currentY, this.width, this.height);
   }
   public void setInitialPosition()
   {
      int baseX = 10;
      int baseY = 819;
      switch(this.playerNumber)
      {
         case 1: this.currentX = baseX;
            this.currentY = baseY;
            break;
         case 2:	this.currentX = baseX + this.width;
            this.currentY = baseY;
            break;
         case 3:	this.currentX = baseX + this.width * 2;
            this.currentY = baseY;
            break;
         case 4:	this.currentX = baseX + this.width * 3;
            this.currentY = baseY;
            break;
      }
   }
   public int getHeight() {
      return height;
   }
   public int getWidth() {
      return width;
   }
   public int getX() {
      return currentX;
   }
   public int getY() {
      return currentY;
   }
   public void move() 
   {
      location = guide.getDestination();
      if(this.location >= 100	) //Keep player icon from moving off the board
      {
         this.location = 100;
         winner = true;
      }
      
      if(this.location > 0)
      {
         moveDelay++;
         
         if(this.calculatedX + this.offsetX > this.currentX)
         {
            this.currentX+=2; //Doubled increment for faster movement
            moveDelay = 0;
         }   
         else if(this.calculatedX + this.offsetX < this.currentX)
         {
            this.currentX-=2;
            moveDelay = 0;
         }   
      
         if(this.calculatedY + this.offsetY > this.currentY)
         {
            this.currentY+=2;
            moveDelay = 0;
         }   
         else if(this.calculatedY + this.offsetY < this.currentY)
         {
            this.currentY-=2;
            moveDelay = 0;
         }   
         
         //if no move made (destination reached), get next destination
         if(moveDelay >= 5)
            guide.destinationReached();
         
         //checks for non movement with a location of 100 before calling hasWon
         if (moveDelay >= 10 && this.location == 100)
         {
            hasWon();
         }            
      }
   }


   public int getPlayerNumber() 
   {
      return playerNumber;
   }


   public int getLocation() 
   {
      return location;
   }


   public void setLocation(int location) 
   {
      this.location = location;
   }

   public void updateLocation(int value) 
   {
      guide.addDestination(location, this.location + value);
      this.location = guide.getDestination();	
   }
   
   /** Win condition checking */
   public void hasWon()
   {
      if (winner == true)
      {
         int again = JOptionPane.showConfirmDialog(null, "Player" + this.playerNumber + " Has Won! Play again?", "Winner", JOptionPane.YES_NO_OPTION);
         if(again == JOptionPane.YES_OPTION)
         {
            GameManager.frame.dispose();
            GameManager.reStart();
         }
         else
         {
            System.exit(0);
         }  
      }
   
   
   }      
}