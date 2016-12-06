package xml;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * A sample DOM writer. This sample program illustrates how to
 * traverse a DOM tree.
 *
 * Основной недостаток парсера DOM: полностью загружает xml в оперативную память
 */

public class TestDom
{
  public void parseAndPrint(String uri)
  {
    Document doc = null;

    try
    {
      DocumentBuilderFactory dbf = 
        DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      doc = db.parse(uri);
      if (doc != null)
        printDomTree(doc);
    }
    catch (Exception e)
    {
      System.err.println("Sorry, an error occurred: " + e);
    }
  }

  /** Prints the specified node, recursively. */
  public void printDomTree(Node node) 
  {
    int type = node.getNodeType();
    switch (type)
    {
      // print the document element
      case Node.DOCUMENT_NODE: 
        {
          System.out.println("<?xml version=\"1.0\" ?>");
          printDomTree(((Document)node).getDocumentElement());
          break;
        }

         // print element and any attributes
      case Node.ELEMENT_NODE: 
        {
          System.out.print("<");
          System.out.print(node.getNodeName());

          NamedNodeMap attrs = node.getAttributes();
          for (int i = 0; i < attrs.getLength(); i++)
            printDomTree(attrs.item(i));

          System.out.print(">");

          //if exist Child element
          if (node.hasChildNodes())
          {
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++)
              printDomTree(children.item(i));
          }

          System.out.print("</");
          System.out.print(node.getNodeName());
          System.out.print('>');

          break;
        }

        // Print attribute nodes
      case Node.ATTRIBUTE_NODE:
        {
          System.out.print(" " + node.getNodeName() + "=\"" +
                           ((Attr)node).getValue() + "\"");
          break;
        }

      // print text
      case Node.TEXT_NODE: 
        {
          System.out.print(node.getNodeValue());
          break;
        }
    }
  } 

  /** Main program entry point. */
  public static void main(String argv[]) 
  {
    TestDom d1 = new TestDom();
    d1.parseAndPrint("test.xml");
  }
}