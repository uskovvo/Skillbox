import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;

public class XMLHandler extends DefaultHandler {
    @Getter
    static boolean endDocument;
    static String name;
    static String birthDate;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("voter")){
            name = attributes.getValue("name");
            birthDate = attributes.getValue("birthDay");

            try {
                DBConnection.countVoter(name, birthDate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        try {
            DBConnection.flush();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
