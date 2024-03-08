import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Eschal Najmi
 */
public class Track {

  ArrayList<Point> points;

  // TODO: Create a stub for the constructor
  public Track(){
    points = new ArrayList<Point>();
  }

  public Track(String filename){
    try{
      readFile(filename);
    } catch(IOException e){

    }
  }

  // TODO: Create a stub for readFile()
  public void readFile(String filename) throws IOException{
    points.clear();
    File f = new File(filename);
    Scanner scanner = new Scanner(f);
    String h = scanner.nextLine();

    while(scanner.hasNextLine()){
      String x = scanner.nextLine();
      String[] number = x.split(",");
      int dataLength = number.length;

      if(dataLength != 4){
        throw new GPSException("Invalid number of variables");
      }

      ZonedDateTime t = ZonedDateTime.parse(number[0]);
      Double lon = Double.parseDouble(number[1]);
      Double lat = Double.parseDouble(number[2]);
      Double elev = Double.parseDouble(number[3]);

      Point p = new Point(t, lon, lat, elev);
      points.add(p);
    }

    scanner.close();
  }

  // TODO: Create a stub for add()
  public void add(Point point){
    points.add(point);
  }

  // TODO: Create a stub for get()
  public Point get(int index){
    if(index<0 || index>=points.size()){
      throw new GPSException("Invalid index");
    }
    Point p = points.get(index);
    return p;
  }

  // TODO: Create a stub for size()
  public int size(){
    return points.size();
  }

  // TODO: Create a stub for lowestPoint()
  public Point lowestPoint(){
    if(size() == 0){
      throw new GPSException("Invalid number of points");
    }

    Point p1 = new Point(null, 0, 0, 2147483647);
    Point p2;
    for(int i = 0; i < size(); i++){
      p2 = get(i);
      if(p1.getElevation() > p2.getElevation()){
        p1 = p2;
      }
    }
    return p1;
  }

  // TODO: Create a stub for highestPoint()
  public Point highestPoint(){
    if(size() == 0){
      throw new GPSException("Invalid number of points");
    }

    Point p1 = new Point(null, 0, 0, 0);
    Point p2;
    for(int i = 0; i < size(); i++){
      p2 = get(i);
      if(p1.getElevation() < p2.getElevation()){
        p1 = p2;
      }
    }
    return p1;
  }

  // TODO: Create a stub for totalDistance()
  public double totalDistance(){
    if(size() < 2){
      throw new GPSException("Invalid number of points");
    }

    double distance = 0.0;

    for(int i = 0; i < size() - 1; i++){
      distance += Point.greatCircleDistance(get(i), get(i+1));
    }

    return distance;
  }

  // TODO: Create a stub for averageSpeed()
  public double averageSpeed(){
    if(size() < 2){
      throw new GPSException("Invalid number of points");
    }

    double speed = 0.0;

    for(int i = 0; i < size() - 1; i++){
      Point p1 = get(i);
      Point p2 = get(i+1);
      speed = speed + ChronoUnit.SECONDS.between(p1.getTime(), p2.getTime());
    }

    double distance = totalDistance();
    double ms = distance/speed;

    return ms;
  }
}
