package exe.udm.threads.warehouse;

public class WarehouseProcedure {
  private static Warehouse wh = new Warehouse();
  private static int id = 0;

  public static void execute() {
    Thread producer = new Thread(() -> {
      try {
        wh.receiveOrder(new Order(++id, ProductType.SNOWBOARD, 2));
        wh.receiveOrder(new Order(++id, ProductType.SNOWBOARD, 1));
        wh.receiveOrder(new Order(++id, ProductType.KITEBOARD, 3));
        wh.receiveOrder(new Order(++id, ProductType.SURF, 1));
        wh.receiveOrder(new Order(++id, ProductType.PADDLEBOARD, 10));
        wh.receiveOrder(new Order(++id, ProductType.PADDLEBOARD, 4));
        wh.receiveOrder(new Order(++id, ProductType.WAKEBOARD, 1));
        wh.receiveOrder(new Order(++id, ProductType.PADDLEBOARD, 1));
        wh.receiveOrder(new Order(++id, ProductType.KITEBOARD, 3));
        wh.receiveOrder(new Order(++id, ProductType.LONGBOARD, 2));
        wh.receiveOrder(new Order(++id, ProductType.SURF, 1));
        wh.receiveOrder(new Order(++id, ProductType.SKATEBOARD, 5));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread consumerA = new Thread(() -> {
      int orderCount = 0;

      try {
        while(orderCount < 5) {
          wh.fulfillOrder();
          Thread.sleep(500);
        }
      } catch (InterruptedException ie) {
        System.out.println("Consumer A interrupted");
      }
    });

    Thread consumerB = new Thread(() -> {
      int orderCount = 0;

      try {
        Thread.sleep(250);

        while(orderCount < 5) {
          wh.fulfillOrder();
          Thread.sleep(500);
        }
      } catch (InterruptedException ie) {
        System.out.println("Consumer B interrupted");
      }
    });

    producer.start();
    consumerA.start();
    consumerB.start();

    try {
      Thread.sleep(3000);
      consumerA.interrupt();
      consumerB.interrupt();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
