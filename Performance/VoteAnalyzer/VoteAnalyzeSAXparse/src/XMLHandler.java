import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class XMLHandler extends DefaultHandler{

    private final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private Voter voter;
    private final HashMap<Voter, Integer> voterCounts;

    public XMLHandler(){
        voterCounts = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        try {
            if (qName.equals("voter") && voter == null) {
                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthDay);
            }
            else if(qName.equals("visit") && voter != null){
                int count = voterCounts.getOrDefault(voter, 0);
                count = count + 1;
                voterCounts.put(voter, count);
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if(qName.equals("voter")){
            voter = null;
        }
    }

    public void printDupliclatedVoters(){
        voterCounts.keySet()
                .stream()
                .filter(voterIntegerEntry -> voterCounts.get(voterIntegerEntry) > 1)
                .forEach(voter1 -> {
                System.out.println(voter1.toString() + " - " + voterCounts.get(voter1));
        });
    }
}
