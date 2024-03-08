import java.io.IOException;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Eschal Najmi
 */
public class TrackInfo {
  public static void main(String[] args) throws IOException {

    //Check for correct number of command line inputs
    if(args.length != 1){
      System.out.println("Invalid number of command line arguments");
      System.exit(0);
    }

    //Create the object
    Track track = new Track();
    track.readFile(args[0]);
    
    //Find the object size
    try{
      int size = track.size();
    } catch(GPSException e){
      System.out.println("Invalid number of points");
      System.exit(0);
    }
    int size = track.size();
    System.out.println(size + " number of points in file");

    //Find the lowest point in the object
    try{
      Point lowestPoint = track.lowestPoint();
    } catch(GPSException e){
      System.out.println("Invalid number of points");
      System.exit(0);
    }
    Point lowestPoint = track.lowestPoint();
    System.out.println("Lowest point is " + lowestPoint);

    //Find the highest point in the object
    try{
      Point highestPoint = track.highestPoint();
    } catch(GPSException e){
      System.out.println("Invalid number of points");
      System.exit(0);
    }
    Point highestPoint = track.highestPoint();
    System.out.println("Highest point is " + highestPoint);

    //Find the total distance of the points in the object
    try{
      double distance = track.totalDistance();
    } catch(GPSException e){
      System.out.println("Invalid number of points");
      System.exit(0);
    }
    double distance = track.totalDistance();
    distance = distance/1000;
    System.out.printf("Total distance = %.3f km", distance);

    System.out.println("");

    //Find the average speed of the points
    try{
      double speed = track.averageSpeed();
    } catch(GPSException e){
      System.out.println("Invalid number of points");
      System.exit(0);
    }
    double speed = track.averageSpeed();
    System.out.printf("Average speed = %.3f m/s", speed);


  }
}
