import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;


public class GameManager 
{
   static JFrame frame;
   private static CardLayout cl;
   private static int numberOfPlayers = 0, numberComputerPlayers = 0;
   private static GameScreen gs;
   private static MainMenu mm;
	
   public static void main(String[] args) throws InterruptedException
   {
      reStart();
      while(true)//game loop
      {
         gs.updateItems();
         gs.move();
         gs.repaint();
         Thread.sleep(10);
      }
   
   }
	
   public static void reStart()
   {
      frame = new JFrame("Chutes and Ladders");
      cl = new CardLayout();
      gs = new GameScreen("gs");
      mm = new MainMenu();
      frame.setLayout(cl);
      frame.add(mm);
      frame.add(gs);
      frame.pack();
      frame.setSize(640, 480);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      gs.updateItems();
      gs.setInitial();
   }

	public static void setNumberOfPlayers(int numberOfPlayers, int numberComputerPlayers) 
	{
		GameManager.numberOfPlayers = numberOfPlayers;
		GameManager.numberComputerPlayers = numberComputerPlayers;
		System.out.println(GameManager.numberComputerPlayers);
	}
    
   public static int getNumberOfPlayers() {
      return numberOfPlayers;
   }
	public static int getNumberComputerPlayers()
	{
		return numberComputerPlayers;
	}

   public static void switchScreen()
   {
      cl.next(frame.getContentPane());
      frame.setSize(1070, 890);
      gs.addPlayers(numberOfPlayers, numberComputerPlayers);
   }
}