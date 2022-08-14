package parsing;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XmlParser {
    public static Map<String, String> parse(String filePath) {
        return traverseDocument(accessDocument(filePath));
    }

    public static Document accessDocument(String filePath) {
        Document doc=null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(filePath);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return doc;
    }
    public static Map<String, String> traverseDocument(Document doc) {
        Map<String, String> map = new LinkedHashMap<>();

        NodeList sortNodes = doc.getElementsByTagName("sort");

        for(int k = 0; k < sortNodes.getLength(); k++) {
            Node sortNode = sortNodes.item(k);

            NodeList subNodes = sortNode.getChildNodes();

            for(int j = 0; j < subNodes.getLength(); j++) {
                Node node = subNodes.item(j);

                if("name".equals(node.getNodeName())){
                    map.put(node.getNodeName(), node.getTextContent());
                } else if ("price".equals(node.getNodeName())) {
                    map.put(node.getNodeName(), node.getTextContent());
                } else if ("rate".equals(node.getNodeName()));{
                    map.put(node.getNodeName(), node.getTextContent());
                }
            }
        }
        map.remove("#text");

        return map;
    }
}
