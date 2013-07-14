package cn.orz.pascal.doqme

class Example {

    case class Item(val title:String, val link:String, val description:String, val content:String, val date:java.util.Date)
    def readFeed(url:String) = {
        import scala.xml.XML
        import org.joda.time.DateTime

        val rss = XML.load(new java.net.URL(url))
        (rss \ "item").map{item => Item(item \ "title" text, item \ "link" text, item \ "encoded" text, item \ "encoded" text, new DateTime(item \ "date" text).toDate)}
    }

    def parseFeatures(item:Item):Map[String, Int] = {
        import net.java.sen.SenFactory;
        import net.java.sen.StringTagger
        import net.java.sen.dictionary.Token
        import scala.collection.JavaConverters._

        def parse(tokens:List[Token]):Map[String, Int] = {
          import scala.collection.mutable.Map
          tokens.foldLeft(Map[String,Int]().withDefaultValue(0)){(r, x) => r + (x.getSurface -> (r(x.getSurface) + 1))}.
          toList.
          sortBy(_._2).
          reverse.
          toMap
        }
        val tagger = SenFactory.getStringTagger(null);
        val document = item.description.replaceAll("<(.*?)>","")
        val tokens = tagger.analyze(document, new java.util.ArrayList[Token]()).asScala.filter{_.getMorpheme.getPartOfSpeech.contains("名詞")}
        parse(tokens.toList)
    }

    def score[T](x:Map[T, Int])(y:Map[T, Int]):Double = {
        import java.lang.Math._
        val dist = y.keys.foldLeft(0){(r, k) => r + pow((y(k) - (if(x.contains(k)){x(k)}else{0})), 2).toInt}
        val max = y.keys.foldLeft(0){(r, k) => r + pow((y(k) - 0), 2).toInt}
        dist / max.toDouble
    }
/*
    val url = "http://b.hatena.ne.jp/hotentry.rss"
    val items = readFeed(url)

    val favorites = Map("閉店" -> 5, "まとめ" -> 6, "理由" -> 7)
    val features = parseFeatures(items(0))
    dist(favorites)(features)
    */
}
