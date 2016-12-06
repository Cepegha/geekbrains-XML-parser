package xml;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.FileReader;
import java.io.IOException;

//XPath реализовывает навигацию по DOM в XML
public class XPathExample {

  public static void main(String[] args) 
   throws ParserConfigurationException, SAXException, 
          IOException, XPathExpressionException {
      //фабрика + парсер
    XPathFactory factory = XPathFactory.newInstance();
    XPath xpath = factory.newXPath();
    //формируем запрос, варианты:
      // /bookstore/book[1]
      // /bookstore/book[last()-1]
      // /bookstore/book[position()<3]
      // title[@lang]
      // title[@lang='en']
      // /bookstor/book[price>35.00]/title
      //XPathExpression expr = xpath.compile("//book[author='Neal Stephenson']/title/text()");
      XPathExpression expr = xpath.compile("//book[price<35.00]/title/text()");
    //применяем запрос к нашему файлу
    Object result = expr.evaluate(new InputSource(new FileReader("book.xml")),
    								XPathConstants.NODESET);
      //вывод результата
    NodeList nodes = (NodeList) result;
    for (int i = 0; i < nodes.getLength(); i++) {
        System.out.println(nodes.item(i).getNodeValue()); 
    }

  }

}