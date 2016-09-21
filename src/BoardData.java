
public class BoardData 
{

   public static int checkLocation(int currentLocation)
   {
      int newLocation = currentLocation;
      switch(currentLocation)
      {
         case 1: newLocation = 38;
            break;
         case 4: newLocation = 14;
            break;
         case 9: newLocation = 31;
            break;
         case 16: newLocation = 6;
            break;
         case 21: newLocation = 42;
            break;
         case 28: newLocation = 84;
            break;
         case 36: newLocation = 44;
            break;
         case 47: newLocation = 26;
            break;
         case 49: newLocation = 11;
            break;
         case 51: newLocation = 67;
            break;
         case 56: newLocation = 53;
            break;
         case 62: newLocation = 19;
            break;
         case 64: newLocation = 60;
            break;
         case 71: newLocation = 91;
            break;
         case 80: newLocation = 100;
            break;
         case 87: newLocation = 24;
            break;
         case 93: newLocation = 73;
            break;
         case 95: newLocation = 75;
            break;
         case 98: newLocation = 78;
            break;
         default: newLocation = currentLocation;
            break;
      
      }
      return newLocation;
   }
}
