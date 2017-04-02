import java.util.Scanner;

public class Main
{

    public static void   main(String[] args)
    {

      Scanner input = new Scanner(System.in);

      double obsDesired;
      double currentLoc;
      double diff1;
      double diff2;
      String cdiPosition;
      String flag;

      System.out.println("VHF Omni Directional Radio Range(VOR) System");
      System.out.print("Enter desired obs: ");
      obsDesired = input.nextInt();
      System.out.print("Enter current location: ");
      currentLoc = input.nextInt();

      System.out.println("Desired Radial: " + obsDesired);

      // Normal
      diff1 = getDifference(obsDesired, currentLoc);

      // opposite
      if (obsDesired - 180 < 0) diff2 = getDifference(obsDesired+180, currentLoc);
      else diff2 = getDifference(obsDesired-180, currentLoc);
      if (diff1 > diff2) {
        diff1 = diff2;
        if (obsDesired - 180 < 0) obsDesired += 180;
        else obsDesired -= 180;
      }

      cdiPosition = getCDI(obsDesired, currentLoc, diff1);
      flag = getFlag(diff1);

      System.out.println("Intercepted Radial: " + currentLoc);
      System.out.println("Traveling " + flag + " VOR Station");
      System.out.println(cdiPosition);
      System.out.println(diff1);



    }


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

    public static String getCDI(double obsDesired, double currentLoc, double diff)
    {
      if (obsDesired > currentLoc) {
          if (diff > 9) return "Deflection: Full Left";
          if (diff > 7) return "Deflection: 4 dots left";
          if (diff > 5) return "Deflection: 3 dots left";
          if (diff > 3) return "Deflection: 2 dots left";
          if (diff > 1) return "Deflection: 1 dot left";
      }
      else if (obsDesired < currentLoc) {
        if (diff > 9) return "Deflection: Full Right";
        if (diff > 7) return "Deflection: 4 dots right";
        if (diff > 5) return "Deflection: 3 dots right";
        if (diff > 3) return "Deflection: 2 dots right";
        if (diff > 1) return "Deflection: 1 dot right";
      }
      return "Deflection: Centered";
    }

    public static String getFlag(double diff)
    {
      if (diff <= 90) return "TO";
      return "FROM";
    }
}
