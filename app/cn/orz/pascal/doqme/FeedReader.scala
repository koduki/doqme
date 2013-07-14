package cn.orz.pascal.doqme

import models.Feed

class FeedReader {
  def read(url: String) = {
    import scala.xml.XML
    import org.joda.time.DateTime

    val rss = XML.load(new java.net.URL(url))
    (rss \ "item").map { item => Feed(item \ "title" text, item \ "link" text, item \ "encoded" text, item \ "encoded" text, new DateTime(item \ "date" text).toDate) }
  }
}