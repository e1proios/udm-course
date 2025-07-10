package exe.udm.inout.game;

public record PlayedGame(
  String name,
  String platform,
  boolean restricted,
  String released,
  String finished,
  String mastered,
  float completion,
  int rating,
  String notes
) {
  public static PlayedGame create(
    String name,
    String platform,
    boolean restricted,
    String released,
    String finished,
    String mastered,
    float completion,
    int rating,
    String notes
  ) {
    return new PlayedGame(name, platform, restricted, released, finished, mastered, completion, rating, notes);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(this.name());
    sb.append(" - ");
    sb.append("played on " + this.platform());

    sb.append(" - ");
    sb.append("released: " + this.released());

    sb.append(" - ");
    sb.append("completed: " + this.finished());

    sb.append(" ----- ");
    sb.append(this.notes());

    return sb.toString();
  }
}
