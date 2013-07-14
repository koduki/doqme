package cn.orz.pascal.doqme

class ScoreCalcurator {
  def calcurate[T](rate: Map[T, Int])(features: Map[T, Int]): Int = {
    features.
      map { x => if (rate.contains(x._1)) { x._2 * rate(x._1) } else { 0 } }.
      foldLeft(0) { (r, x) => r + x }
  }
}