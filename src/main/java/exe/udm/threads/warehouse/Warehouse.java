package exe.udm.threads.warehouse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Warehouse {
  private final int MAX_ORDERS = 3;
  private Map<ProductType, Integer> storage;
  private LinkedList<Order> orders = new LinkedList<>();

  {
    this.storage = new HashMap<>(Map.ofEntries(
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
    while(this.orders.size() >= MAX_ORDERS) {
      wait();
    }
    var currentQuantity = this.storage.get(o.type());

    if (o.quantity() <= currentQuantity) {
      this.orders.addLast(o);
      this.storage.put(o.type(), Integer.valueOf(currentQuantity - o.quantity()));
      System.out.println(
        "[%s] order #%d placed: %d %s".formatted(
          Thread.currentThread().getName(),
          o.id(),
          o.quantity(),
          o.type().toString(o.quantity())
        )
      );
      notifyAll();
    } else {
      System.out.println(
        "[%s] can't place order: %d %s, insufficient quantity in storage: %d".formatted(
          Thread.currentThread().getName(),
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
    Order o = this.orders.poll();
    System.out.println("[%s] processing order %d".formatted(Thread.currentThread().getName(), o.id()));
    notifyAll();
  }
}
