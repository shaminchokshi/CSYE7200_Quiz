package Spring2023

import scala.util.Random

case class Table[T](xs: List[T]) {

  /**
   * Method to get the size of this Table.
   *
   * Points: 5
   *
   * @return the size of this Table.
   */
  def size: Int = xs.length

  /**
   * Method to do a filter on T, given a predicate of type P=>Boolean, and by using a "lens" function of type T=>P.
   *
   * Points: 10
   *
   * @param p    the predicate of type P => Boolean
   * @param lens the lens function which takes a T and returns a P.
   * @tparam P the underlying type of the predicate.
   * @return a new Table[T] with only the matching rows.
   */
  def lensFilter[P](p: P => Boolean)(lens: T => P): Table[T] = Table(xs.filter(x => p(lens(x))))

  /**
   * Method to do a filter on T given a predicate of type T=>Boolean.
   *
   * Points: 8
   *
   * NOTE: 3 points will be deducted if you do not use lensFilter in your expression.
   *
   * @param p the predicate of type T => Boolean
   * @return a new Table[T] with only the matching rows.
   */
  def filter(p: T => Boolean): Table[T] = lensFilter(p)(x => x)

  /**
   * Method to sample the Table and return a Table which is typically a lot smaller.
   *
   * Points: 5
   *
   * NOTE: 2 points will be deducted if you do not use filter in your expression.
   *
   * @param n the odds against selecting any particular row.
   *          If n = 1, then all rows will be selected;
   *          If n = 2, then approximately half the rows will be selected.
   * @param r a Random number generator.
   * @return a new Table[T].
   */
  def sample(n: Int)(implicit r: Random): Table[T] = Table(xs.filter(_ => r.nextInt(n) == 0))

  /**
   * This is a bit harder. In order to make this work, you will have to understand implicits.
   *
   * Note that, for the bonus, you are to return a Double, regardless of the type of T.
   * If you cannot get that to work, just return the sum as a T.
   *
   * 12 points if you return the result as a T;
   * 5 bonus points available if you return the result as a Double.
   *
   * @return a Double.
   */
  def sum(implicit ev: Numeric[T]): Double = {
    val n = ev
    xs.map(n.toDouble).sum
  }
}

object Table {

  /**
   * Object method to build a Table[T] from a LazyList[T].
   * Watch out! You need to force the input to have finite size.
   *
   * NOTE: 5 points.
   *
   * @param xs a LazyList[T].
   * @tparam T the underlying type of xs and the resulting Table.
   * @return a Table[T].
   */
  def apply[T](xs: LazyList[T]): Table[T] = Table(xs.toList)

  /**
   * Object method to build a Table[T] from a variable number of T parameters.
   *
   * NOTE: 4 points.
   *
   * @param xs a variable number of T values.
   * @tparam T the underlying type of xs and the resulting Table.
   * @return a Table[T].
   */
  def apply[T](xs: T*): Table[T] = Table(xs.toList)
}
