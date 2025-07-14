package exe.udm.threads.warehouse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Warehouse {
  // product list?
  public static Map<ProductType, Integer> storage;

  private final int MAX_ORDERS = 3;
  private LinkedList<Order> orders = new LinkedList<>();

  static {
    storage = new HashMap<>(Map.ofEntries(
      Map.entry(ProductType.KITEBOARD, 8),
      Map.entry(ProductType.LONGBOARD, 3),
      Map.entry(ProductType.PADDLEBOARD, 5),
      Map.entry(ProductType.SKATEBOARD, 10),
      Map.entry(ProductType.SNOWBOARD, 2),
      Map.entry(ProductType.SURF, 2),
      Map.entry(ProductType.WAKEBOARD, 2)
    ));
  }

  public synchronized void receiveOrder(Order o) throws InterruptedException{
    // should poll or loop indefinitely, checking the size of the list
    // call wait if the list has reached max capacity
    while(this.orders.size() > MAX_ORDERS) {
      wait();
    }
    var currentQuantity = storage.get(o.type());

    if (o.quantity() <= currentQuantity) {
      this.orders.addLast(o);
      storage.put(o.type(), Integer.valueOf(currentQuantity - o.quantity()));
      System.out.println(
        String.format("Order #%d placed: %d %s", o.id(), o.quantity(), o.type().toString(o.quantity()))
      );
      notifyAll();
    } else {
      System.out.println(
        String.format(
          "Can't place order: %d %s, insufficient quantity in storage: %d",
          o.quantity(),
          o.type().toString(o.quantity()),
          currentQuantity
        )
      );
    }
  }

  public synchronized void fulfillOrder() throws InterruptedException {
    // poll the list, check if it's empty and wait in loop, until an order is added
    while(this.orders.size() == 0) {
      wait();
    }
    this.orders.poll();
    notifyAll();
  }
}
