package exe.udm.inout;

import java.util.regex.Pattern;

public class GameFilter {
  // optional fields only
  private final Pattern nameRegex;
  private final Pattern platformRegex;
  private final boolean considerRestriction;
  private final boolean isRestricted;
  private final String releasedBefore;
  private final String releasedAfter;
  private final String finishedBefore;
  private final String finishedAfter;
  private final String masteredBefore;
  private final String masteredAfter;
  private final double minCompletion;
  private final double maxCompletion;
  private final int minRating;
  private final int maxRating;
  private final Pattern notesRegex;

  private GameFilter(GameFilterBuilder builder) {
    this.nameRegex = builder.nameRegex;
    this.platformRegex = builder.platformRegex;
    this.considerRestriction = builder.considerRestriction;
    this.isRestricted = builder.isRestricted;
    this.releasedBefore = builder.releasedBefore;
    this.releasedAfter = builder.releasedAfter;
    this.finishedBefore = builder.finishedBefore;
    this.finishedAfter = builder.finishedAfter;
    this.masteredBefore = builder.masteredBefore;
    this.masteredAfter = builder.masteredAfter;
    this.minCompletion = builder.minCompletion;
    this.maxCompletion = builder.maxCompletion;
    this.minRating = builder.minRating;
    this.maxRating = builder.maxRating;
    this.notesRegex = builder.notesRegex;
  }

  public Pattern nameRegex() {
    return this.nameRegex;
  }

  public Pattern platformRegex() {
    return this.platformRegex;
  }

  public boolean considerRestriction() {
    return this.considerRestriction;
  }

  public boolean isRestricted() {
    return this.isRestricted;
  }

  public String releasedBefore() {
    return this.releasedBefore;
  }

  public String releasedAfter() {
    return this.releasedAfter;
  }

  public String finishedBefore() {
    return this.finishedBefore;
  }

  public String finishedAfter() {
    return this.finishedAfter;
  }

  public String masteredBefore() {
    return this.masteredBefore;
  }

  public String masteredAfter() {
    return this.masteredAfter;
  }

  public double minCompletion() {
    return this.minCompletion;
  }

  public double maxCompletion() {
    return this.maxCompletion;
  }

  public int minRating() {
    return this.minRating;
  }

  public int maxRating() {
    return this.maxRating;
  }

  public Pattern notesRegex() {
    return this.notesRegex;
  }

  public static class GameFilterBuilder {
    private Pattern nameRegex;
    private Pattern platformRegex;
    private boolean considerRestriction = false;
    private boolean isRestricted;
    private String releasedBefore;
    private String releasedAfter;
    private String finishedBefore;
    private String finishedAfter;
    private String masteredBefore;
    private String masteredAfter;
    private double minCompletion = -1;
    private double maxCompletion = -1;
    private int minRating = -1;
    private int maxRating = -1;
    private Pattern notesRegex;

    public GameFilterBuilder() {}

    public GameFilterBuilder setNameSearch(String s) {
      this.nameRegex = Pattern.compile(s, Pattern.CASE_INSENSITIVE);
      return this;
    }

    public GameFilterBuilder setPlatformSearch(String s) {
      this.platformRegex = Pattern.compile(s, Pattern.CASE_INSENSITIVE);
      return this;
    }

    public GameFilterBuilder setConsiderRestriction(boolean considerRestriction) {
      this.considerRestriction = considerRestriction;
      return this;
    }

    public GameFilterBuilder setRestricted(boolean isRestricted) {
      this.isRestricted = isRestricted;
      return this;
    }

    public GameFilterBuilder setReleasedBefore(String s) {
      this.releasedBefore = s;
      return this;
    }

    public GameFilterBuilder setReleasedAfter(String s) {
      this.releasedAfter = s;
      return this;
    }

    public GameFilterBuilder setFinishedBefore(String s) {
      this.finishedBefore = s;
      return this;
    }

    public GameFilterBuilder setFinishedAfter(String s) {
      this.finishedAfter = s;
      return this;
    }

    public GameFilterBuilder setMasteredBefore(String s) {
      this.masteredBefore = s;
      return this;
    }

    public GameFilterBuilder setMasteredAfter(String s) {
      this.masteredAfter = s;
      return this;
    }

    public GameFilterBuilder setMinCompletion(double minCompletion) {
      this.minCompletion = minCompletion;
      return this;
    }

    public GameFilterBuilder setMaxCompletion(double maxCompletion) {
      this.maxCompletion = maxCompletion;
      return this;
    }

    public GameFilterBuilder setMinRating(int minRating) {
      this.minRating = minRating;
      return this;
    }

    public GameFilterBuilder setMaxRating(int maxRating) {
      this.maxRating = maxRating;
      return this;
    }

    public GameFilterBuilder setNotesRegex(String s) {
      this.notesRegex = Pattern.compile(s, Pattern.CASE_INSENSITIVE);
      return this;
    }

    public GameFilter build() {
      return new GameFilter(this);
    }
  }
}
