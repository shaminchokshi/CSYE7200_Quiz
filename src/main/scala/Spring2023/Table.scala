package Spring2023

import scala.util.Random

case class Table[T](xs: List[T]) {
  def size: Int = /** SOLUTION END */ ???

  def lensFilter[P](p: P=>Boolean)(lens: T=>P): Table[T] = /** SOLUTION END */ ???

  def filter(p: T => Boolean): Table[T] = /** SOLUTION END */ ???

  def sample(n: Int)(implicit r: Random): Table[T] = /** SOLUTION END */ ???
}

object Table {

  def apply[T](xs: LazyList[T]): Table[T] = /** SOLUTION END */ ???

  def apply[T](xs: T*): Table[T] = /** SOLUTION END */ ???
}
