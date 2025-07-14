package exe.udm.threads;

public class Threads {
  public static Thread getEven() {
    return new Thread(new Runnable() {
      public void run() {
        try {
          Thread.sleep(250);

          for (int i = 1; i <= 25; ++i) {
            System.out.println(i * 2);
            Thread.sleep(500);
          }
          System.out.println("EVEN THREAD END");
        } catch (InterruptedException ie) {
          System.out.println("EVEN INTERRUPTED");
        }
      }
    });
  }

  public static Thread getOdd() {
    return new Threads.OddThread();
  }

  private static class OddThread extends Thread {
    @Override
    public void run() {
      try {
        for (int i = 0; i <= 24; ++i) {
          System.out.println(i * 2 + 1);

          Thread.sleep(500);
        }
        System.out.println("ODD THREAD END");
      } catch (InterruptedException ie) {
        System.out.println("ODD INTERRUPTED");
      }
    }
  }
}
