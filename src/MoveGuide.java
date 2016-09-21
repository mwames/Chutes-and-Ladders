/**
 * Calculates location checkpoints and feeds these to the player object, such that
 * it roughly follows the board's intended path.
 */

public class MoveGuide
{
   int[] dest;
   int moves;
   
   public MoveGuide()
   {
      dest = new int[5];
      moves = 0;
   }
   
   public void reset()
   {
      moves = 0;
   }      
   
   /** Used internally */
   private void addDestination(int dest)
   {
      this.dest[moves] = dest;
      
      moves++;
   }
   
   /**
    * Used by Player class to feed data into this class
    */
   public void addDestination(int loc, int dest)
   {
      reset();
      addDestination(loc+1); //Small workaround, first destination was being skipped
      
      if((loc-1)/10 < (dest-1)/10) //if moving to different row:
      {
         if(loc+1 == dest) //if moving one space, can simply move directly
            addDestination(dest);
         else if(loc%10 == 0) //if currently at edge:
         {
            addDestination(loc+1);
            addDestination(dest);
         }   
         else if(dest%10 == 1) //if destination is at edge:
         {
            addDestination(dest-1);
            addDestination(dest);
         }
         else
         {
            int temp = dest - (dest%10);
            addDestination(temp);
            addDestination(temp+1);
            addDestination(dest);
         }      
      }
      else //if on same row, move directly
         addDestination(dest);
      
      addDestination(); //calculate chute or ladder 
   }   
   
   /**
    * Used internally to calculate motion via chutes or ladders.
    */
   private void addDestination()
   {
      int jumpLocation = BoardData.checkLocation(dest[moves-1]);
      
      if(jumpLocation != dest[moves-1])
         addDestination(jumpLocation);
   }
   
   /** Shift all destinations down one in the array, when player piece reaches its destination. */
   public int destinationReached()
   {
      switch(moves)
      {
         case 0:
            break;
         case 1:
            moves--;
            break;
         case 2:
            moves--;
            dest[0] = dest[1];
            break;
         case 3:
            moves--;
            dest[0] = dest[1];
            dest[1] = dest[2];
            break;
         case 4:
            moves--;
            dest[0] = dest[1];
            dest[1] = dest[2];
            dest[2] = dest[3];
            break;
         case 5:
            moves--;
            dest[0] = dest[1];
            dest[1] = dest[2];
            dest[2] = dest[3];
            dest[3] = dest[4];   
      }      
      return dest[0];                   
   }   
   
   public int getDestination()
   {
      return dest[0];
   }

public int getMoves() {
	return moves;
}   
   
}