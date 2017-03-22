import java.util.Scanner;

public class Main
{

    public static void   main(String[] args)
    {

      Scanner input = new Scanner(System.in);

      double obsDesired;
      double currentLoc;
      double diff;

      System.out.println("VHF Omni Directional Radio Range(VOR) System");
      System.out.print("Enter desired obs: ");
      obsDesired = input.nextInt();
      System.out.print("Enter current location: ");
      currentLoc = input.nextInt();

      diff = getDifference(obsDesired, currentLoc);

      System.out.println("You need to set your course by " + diff + " to get your get your OBS");


    }


    public static double getDifference(double obs, double currentPos)
    {
      if (obs < currentPos)
      {
        if (Math.abs(currentPos - obs) > 180)

          return Math.abs((360 - currentPos) + obs);

        else

          return (-1 * Math.abs(obs - currentPos));
      }

      else
      {
        if (Math.abs(obs - currentPos) > 180)

          return Math.abs((360 - obs) + currentPos);

        else

          return (-1 * Math.abs(currentPos - obs));
      }
    }
}
