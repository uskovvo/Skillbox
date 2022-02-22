import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;

public class XMLHandler extends DefaultHandler {

    int limit = 5_000_000;
    @Getter
    static int number = 0;
    boolean endDocument;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("voter") && number < limit){
            String name = attributes.getValue("name");
            String birthDate = attributes.getValue("birthDay");

            try {
                DBConnection.countVoter(name, birthDate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            number++;
        }
    }
}
