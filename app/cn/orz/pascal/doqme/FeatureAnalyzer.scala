package cn.orz.pascal.doqme

import models.Feed
import net.java.sen.SenFactory
import net.java.sen.StringTagger
import net.java.sen.dictionary.Token

object FeatureAnalyzer {

  private val tagger = SenFactory.getStringTagger(null);

  def analyze(item: Feed): Map[String, Int] = {
    import scala.collection.JavaConverters._

    def parse(tokens: TraversableOnce[Token]): Map[String, Int] = {
      import scala.collection.mutable.Map
      tokens.foldLeft(Map[String, Int]().withDefaultValue(0)) { (r, x) => r + (x.getSurface -> (r(x.getSurface) + 1)) }.
        toList.
        sortBy(_._2).
        reverse.
        toMap
    }
    val document = item.description.replaceAll("<(.*?)>", "")
    val tokens = tagger.analyze(document, new java.util.ArrayList[Token]()).asScala.filter { _.getMorpheme.getPartOfSpeech.contains("名詞") }
    parse(tokens)
  }
}