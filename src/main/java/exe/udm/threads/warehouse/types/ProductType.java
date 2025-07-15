package exe.udm.threads.warehouse.types;

public enum ProductType {
  KITEBOARD,
  LONGBOARD,
  PADDLEBOARD,
  SKATEBOARD,
  SNOWBOARD,
  SURF,
  WAKEBOARD;

  public String toString(int quantity) {
    return quantity == 1 ? this.name().toLowerCase() : this.name().toLowerCase() + "s";
  }
}
