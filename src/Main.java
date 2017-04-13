import java.util.Scanner;
import java.util.InputMismatchException;

public class Main
{

    public static void main(String[] args)
    {

      Scanner input = new Scanner(System.in);

      double obsDesired = 0;
      double currentLoc = 0;
      double diff1;
      double diff2;
      String cdiPosition;
      String flag;
	    boolean gettingOBS = true;
	    boolean gettingLoc = true;

      System.out.println("VHF Omni Directional Radio Range(VOR) System");

      while(gettingOBS) //Continues looping until a valid OBS is entered
      {
        System.out.print("Enter desired obs: ");
        try
        {
          obsDesired = input.nextInt(); //Get input
          gettingOBS = false; //Switch flag if valid input
        }
        catch (InputMismatchException ime) //Catch inputMismatchError
        {
          System.out.println("Please enter a valid OBS as a number"); //Prompt for proper input
          gettingOBS = true; //Switch flag to loop again
          String trash = input.nextLine();
        }
      }

      while(gettingLoc) //Continues looping until a valid location is entered
      {
        System.out.print("Enter current location: ");
        try
        {
          currentLoc = input.nextInt(); //Get input
          gettingLoc = false; //Switch flag if valid input
        }
        catch (InputMismatchException ime) //Catch inputMismatchError
        {
          System.out.println("Please enter a valid location as a number"); //Prompt for proper input
          gettingLoc = true; //Switch flag to loop again
          String trash = input.nextLine();
        }
      }

      // showing what was given for input
      System.out.println("Desired Radial: " + obsDesired);
      System.out.println("Intercepted Radial: " + currentLoc);

      // VOR Logic Accepts obs and location
          diff1 = getDifference(obsDesired, currentLoc);

          if (obsDesired - 180 < 0) diff2 = getDifference(obsDesired+180, currentLoc);
          else diff2 = getDifference(obsDesired-180, currentLoc);
          if (diff1 > diff2) {
            diff1 = diff2;
            if (obsDesired - 180 < 0) obsDesired += 180;
            else obsDesired -= 180;
          }

          cdiPosition = getCDI(obsDesired, currentLoc, diff1);
          flag = getFlag(diff1);
      //End VOR Logic

      // Print Results from VOR calculations
      System.out.println("Traveling " + flag + " VOR Station");
      System.out.println(cdiPosition);

    }

    // VOR functions
    public static double getDifference(double obs, double currentPos)
    {
      if (obs < currentPos)
      {
        if (Math.abs(currentPos - obs) > 180)

          return Math.abs((360 - currentPos) + obs);

        else

          return (Math.abs(obs - currentPos));
      }

      else
      {
        if (Math.abs(obs - currentPos) > 180)

          return Math.abs((360 - obs) + currentPos);

        else

          return (Math.abs(currentPos - obs));
      }
    }

    public static String getCDI(double obsDesired, double currentLoc, double diff) // Get needle deflection
    {
      if (diff == 90) return "Signal is Bad.";
      if (obsDesired > currentLoc) {
          if (diff > 9) return "Deflection: Full Left. Signal is Good.";
          if (diff > 7) return "Deflection: 4 dots left. Signal is Good.";
          if (diff > 5) return "Deflection: 3 dots left. Signal is Good.";
          if (diff > 3) return "Deflection: 2 dots left. Signal is Good.";
          if (diff > 1) return "Deflection: 1 dot left. Signal is Good.";
      }
      else if (obsDesired < currentLoc) {
        if (diff > 9) return "Deflection: Full Right. Signal is Good.";
        if (diff > 7) return "Deflection: 4 dots right. Signal is Good.";
        if (diff > 5) return "Deflection: 3 dots right. Signal is Good.";
        if (diff > 3) return "Deflection: 2 dots right. Signal is Good.";
        if (diff > 1) return "Deflection: 1 dot right. Signal is Good.";
      }
      return "Deflection: Centered. Signal is Good.";
    }

    public static String getFlag(double diff)
    {
      if (diff <= 90) return "TO";
      return "FROM";
    }
    // End VOR functions
}
