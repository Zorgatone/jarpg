import jarpg.Application;


public class Main {

  public static void main(String[] args) {
    Application app = new Application(args);

    try {
      app.run();
    } catch (Exception e) {
      System.err.println("Something went wrong.");
      System.exit(1);
    }
  }

}
