package program;

public class MyThread extends Thread
{
   private int startIndex, threadsNumber, maxIndex;

   public MyThread(int startIndex, int threadsNumber, int maxIndex)
   {
      this.startIndex = startIndex;
      this.threadsNumber = threadsNumber;
      this.maxIndex = maxIndex;
   }

   @Override
   public void run()
   {
      for(int i = this.startIndex; i < this.maxIndex; i += this.threadsNumber)
      {
         System.out.println("[ID " + this.getId() + "] " + i);
      }
   }
}