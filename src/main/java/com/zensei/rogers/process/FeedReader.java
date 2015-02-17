package com.zensei.rogers.process;

import com.zensei.rogers.model.Channel;
import com.zensei.rogers.model.Item;
import com.zensei.rogers.model.Rss;
import org.javalite.http.Get;
import org.javalite.http.Http;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

/**
 * Created by jorge on 14/02/15.
 */
public class FeedReader {

    private static final String RSS = "rss";
    private static final String CHANNEL = "channel";
    private static final String ITEM = "item";

    private final InputStream inputStream;

    public FeedReader(String url) throws Exception {
        Get get = Http.get(url);
        inputStream = get.getInputStream();
    }
    
    public FeedReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    

    /**
     * *
     * @return
     * @throws Exception
     */
    public Rss readFeed() throws Exception {

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
        
        Rss rss = new Rss();
        Channel channel = new Channel();
        Item item = null;
        boolean isItem = false;
        boolean isChannel = false;

        while (eventReader.hasNext()) {

            XMLEvent event = eventReader.nextEvent();

            if (event.isStartElement()) {

                StartElement startElement = event.asStartElement();
                String localPart = getWithPrefix(startElement);

                if (localPart.equals(RSS)) {
                    
                    rss.addAttributeValue(startElement.getAttributes());
                    rss.setNamespaceValues(startElement.getNamespaces());
                    
                } else if(localPart.equals(ITEM)) {

                    item = new Item();
                    channel.addItem(item);
                    isItem = true;
                    isChannel = false;
                    
                } else if(localPart.equals(CHANNEL)) {
                    
                    rss.setChannel(channel);
                    isChannel = true;
                    
                } else if (isChannel) {
                    
                    channel.addElementValue(localPart, eventReader.getElementText(), startElement.getAttributes());
                    
                } else if(isItem) {
                    
                    item.addElementValue(localPart, eventReader.getElementText(), startElement.getAttributes());
                }
            }

        }

        return rss;
    }

    /**
     * *
     *
     * @param startElement
     * @return
     */
    private String getWithPrefix(StartElement startElement) {

        String prefix = startElement.getName().getPrefix();
        if (!prefix.isEmpty()) {
            return prefix + ":" + startElement.getName().getLocalPart();
        }
        return startElement.getName().getLocalPart();

    }
}
