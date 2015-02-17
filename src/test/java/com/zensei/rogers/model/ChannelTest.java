package com.zensei.rogers.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.when;

public class ChannelTest extends BaseTest {
    
    private Channel channel;
    private List<Attribute> attributes;

    @Before
    public void setUp() throws Exception {
        
        channel = new Channel();
        attributes = new ArrayList<Attribute>();
        
        Attribute attribute = Mockito.mock(Attribute.class);
        when(attribute.getName()).thenReturn(new QName("href"));
        when(attribute.getValue()).thenReturn("href1");
        attributes.add(attribute);

        attribute = Mockito.mock(Attribute.class);
        when(attribute.getName()).thenReturn(new QName("rel"));
        when(attribute.getValue()).thenReturn("rel1");
        attributes.add(attribute);

        attribute = Mockito.mock(Attribute.class);
        when(attribute.getName()).thenReturn(new QName("type"));
        when(attribute.getValue()).thenReturn("type1");
        attributes.add(attribute);
    }
    

    @Test
    public void testAddElementValue() throws Exception {
        
        Iterator < Attribute > iterator = attributes.iterator();
        
        String titleValue = "title";
        addElementValue(channel, Channel.TITLE, titleValue, iterator);
        Assert.assertEquals(titleValue, channel.getTitle());

        String linkValue = "link";
        addElementValue(channel, Channel.LINK, linkValue, iterator);
        Assert.assertEquals(linkValue, channel.getLink());
        
        String generatorValue = "generator";
        addElementValue(channel, Channel.GENERATOR, generatorValue, iterator);
        Assert.assertEquals(generatorValue, channel.getGenerator());
        
        String descriptionValue = "description";
        addElementValue(channel, Channel.DESCRIPTION, descriptionValue, iterator);
        Assert.assertEquals(descriptionValue, channel.getDescription());
        
        String lastBuildDateValue = "lastBuildDate";
        addElementValue(channel, Channel.LAST_BUILD_DATE, lastBuildDateValue, iterator);
        Assert.assertEquals(lastBuildDateValue, channel.getLastBuildDate());
        
        String languageValue = "language";
        addElementValue(channel, Channel.LANGUAGE, languageValue, iterator);
        Assert.assertEquals(languageValue, channel.getLanguage());
        
        String syUpdatePeriodValue = "sy:updatePeriod";
        addElementValue(channel, Channel.SY_UPDATE_PERIOD, syUpdatePeriodValue, iterator);
        Assert.assertEquals(syUpdatePeriodValue, channel.getUpdatePeriod());
        
        String syUpdateFrequencyValue = "sy:updateFrequency";
        addElementValue(channel, Channel.SY_UPDATE_FREQUENCY, syUpdateFrequencyValue, iterator);
        Assert.assertEquals(syUpdateFrequencyValue, channel.getUpdateFrequency());

        String atomLinkValue = "atom:link";
        addElementValue(channel, Channel.ATOM_LINK, atomLinkValue, iterator);
        
        AtomLink atomLink = channel.getAtomLink();
        Assert.assertNotNull(atomLink);
        Assert.assertEquals("href1", atomLink.getHref());
        Assert.assertEquals("rel1", atomLink.getRel());
        Assert.assertEquals("type1", atomLink.getType());

    }

}