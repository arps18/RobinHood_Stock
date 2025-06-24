package util;

/**
 * A convenience class to represent name-value pairs.It stores two fields: one of type T and another
 * of type R.
 *
 * @param <T> Type of the first element
 * @param <R> Type of the second element
 */
public class Pair<T, R> {

  private final T first;
  private final R second;

  /**
   * Pair implement.
   *
   * @param first  the first element
   * @param second the second element
   */
  public Pair(T first, R second) {
    this.first = first;
    this.second = second;
  }

  public T getFirst() {
    return first;
  }

  public R getSecond() {
    return second;
  }

  @Override
  public String toString() {
    return this.first + ": " + this.second;
  }
}
