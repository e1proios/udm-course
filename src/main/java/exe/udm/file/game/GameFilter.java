package exe.udm.file.game;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.List;
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
  private final float minCompletion;
  private final float maxCompletion;
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

  public Predicate<PlayedGame> satisfyAll() {
    List<Predicate<PlayedGame>> conditions = new ArrayList<>();

    if (this.nameRegex != null) {
      conditions.add(game -> this.nameRegex.matcher(game.name()).find());
    }
    if (this.platformRegex != null) {
      conditions.add(game -> this.platformRegex.matcher(game.platform()).find());
    }
    if (this.considerRestriction) {
      conditions.add(game -> this.isRestricted == game.restricted());
    }

    if (this.releasedBefore != null) {
      conditions.add(game -> this.releasedBefore.compareTo(game.released()) >= 0);
    }
    if (this.releasedAfter != null) {
      conditions.add(game -> this.releasedAfter.compareTo(game.released()) <= 0);
    }

    if (this.finishedBefore != null) {
      conditions.add(game -> this.finishedBefore.compareTo(game.finished()) >= 0);
    }
    if (this.finishedAfter != null) {
      conditions.add(game -> this.finishedAfter.compareTo(game.finished()) <= 0);
    }

    if (this.masteredBefore != null) {
      conditions.add(game -> this.masteredBefore.compareTo(game.mastered()) >= 0);
    }
    if (this.masteredAfter != null) {
      conditions.add(game -> this.masteredAfter.compareTo(game.mastered()) <= 0);
    }

    if (this.minCompletion > 0) {
      conditions.add(game -> game.completion() >= this.minCompletion);
    }
    if (this.maxCompletion > 0) {
      conditions.add(game -> game.completion() <= this.maxCompletion);
    }

    if (this.minRating > 1 && this.minRating < 11) {
      conditions.add(game -> game.rating() >= this.minRating);
    }
    if (this.maxRating < 10 && this.maxRating > 0) {
      conditions.add(game -> game.rating() <= this.maxRating);
    }

    if (this.notesRegex != null) {
      conditions.add(game -> this.notesRegex.matcher(game.notes()).find());
    }

    return conditions.stream()
      .reduce(Predicate::and)
      .orElse(t -> true);
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
    private float minCompletion = -1;
    private float maxCompletion = -1;
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

    public GameFilterBuilder setMinCompletion(float minCompletion) {
      this.minCompletion = minCompletion;
      return this;
    }

    public GameFilterBuilder setMaxCompletion(float maxCompletion) {
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
