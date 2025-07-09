package exe.udm.inout;

import java.util.regex.Pattern;

public class GameFilter {
  // optional fields only
  private Pattern nameRegex;
  private Pattern platformRegex;
  private boolean considerRestriction;
  private boolean isRestricted;
  private Pattern releasedBefore;
  private Pattern releasedAfter;
  private Pattern finishedBefore;
  private Pattern finishedAfter;
  private Pattern masteredBefore;
  private Pattern masteredAfter;
  private double minCompletion;
  private double maxCompletion;
  private int minRating;
  private int maxRating;
  private Pattern notesRegex;

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

  public Pattern releasedBefore() {
    return this.releasedBefore;
  }

  public Pattern releasedAfter() {
    return this.releasedAfter;
  }

  public Pattern finishedBefore() {
    return this.finishedBefore;
  }

  public Pattern finishedAfter() {
    return this.finishedAfter;
  }

  public Pattern masteredBefore() {
    return this.masteredBefore;
  }

  public Pattern masteredAfter() {
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
    private Pattern releasedBefore;
    private Pattern releasedAfter;
    private Pattern finishedBefore;
    private Pattern finishedAfter;
    private Pattern masteredBefore;
    private Pattern masteredAfter;
    private double minCompletion = -1;
    private double maxCompletion = -1;
    private int minRating = -1;
    private int maxRating = -1;
    private Pattern notesRegex;

    public GameFilterBuilder() {}

    public GameFilterBuilder setNameRegex(Pattern nrgx) {
      this.nameRegex = nrgx;
      return this;
    }

    public GameFilterBuilder setPlatformRegex(Pattern platformRegex) {
      this.platformRegex = platformRegex;
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

    public GameFilterBuilder setReleasedBefore(Pattern releasedBefore) {
      this.releasedBefore = releasedBefore;
      return this;
    }

    public GameFilterBuilder setReleasedAfter(Pattern releasedAfter) {
      this.releasedAfter = releasedAfter;
      return this;
    }

    public GameFilterBuilder setFinishedBefore(Pattern finishedBefore) {
      this.finishedBefore = finishedBefore;
      return this;
    }

    public GameFilterBuilder setFinishedAfter(Pattern finishedAfter) {
      this.finishedAfter = finishedAfter;
      return this;
    }

    public GameFilterBuilder setMasteredBefore(Pattern masteredBefore) {
      this.masteredBefore = masteredBefore;
      return this;
    }

    public GameFilterBuilder setMasteredAfter(Pattern masteredAfter) {
      this.masteredAfter = masteredAfter;
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

    public GameFilterBuilder setNotesRegex(Pattern notesRegex) {
      this.notesRegex = notesRegex;
      return this;
    }

    public GameFilter build() {
      return new GameFilter(this);
    }
  }
}
