package com.zensei.rogers.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.stream.events.Attribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemTest extends BaseTest {

    private Item item;
    private List<Attribute> attributes;

    @Before
    public void setUp() throws Exception {

        item = new Item();
        attributes = new ArrayList<Attribute>();
    }

    @Test
    public void testAddElementValue() throws Exception {
        
        Iterator< Attribute > iterator = attributes.iterator();

        String titleValue = "title1";
        addElementValue(item, Item.TITLE, titleValue, iterator);
        Assert.assertEquals(titleValue, item.getTitle());
        
        String linkValue = "link";
        addElementValue(item, Item.LINK, linkValue, iterator);
        Assert.assertEquals(titleValue, item.getTitle());
        
        String descriptionValue = "description";
        addElementValue(item, Item.DESCRIPTION, descriptionValue, iterator);
        Assert.assertEquals(titleValue, item.getTitle());

        String contentEncodedValue = "content:encoded";
        addElementValue(item, Item.CONTENT_ENCODED, contentEncodedValue, iterator);
        Assert.assertEquals(titleValue, item.getTitle());

        String commentsValue = "comments";
        addElementValue(item, Item.COMMENTS, commentsValue, iterator);
        Assert.assertEquals(titleValue, item.getTitle());
        
        String pubDateValue = "pubDate";
        addElementValue(item, Item.PUB_DATE, pubDateValue, iterator);
        Assert.assertEquals(titleValue, item.getTitle());
        
        String dcCreatorValue = "dc:creator";
        addElementValue(item, Item.DC_CREATOR, dcCreatorValue, iterator);
        Assert.assertEquals(titleValue, item.getTitle());

        String guidValue = "guid";
        addElementValue(item, Item.GUID, guidValue, iterator);
        Assert.assertEquals(titleValue, item.getTitle());
        
        String isPermaLinkValue = "isPermaLink";
        addElementValue(item, Item.PERMALINK, isPermaLinkValue, iterator);
        Assert.assertEquals(titleValue, item.getTitle());
        
        String commentRssValue = "wfw:commentRss";
        addElementValue(item, Item.WFW_COMMENTS_RSS, commentRssValue, iterator);
        Assert.assertEquals(titleValue, item.getTitle());
        
        String slashCommentsValue = "slash:comments";
        addElementValue(item, Item.SLASH_COMMENTS, slashCommentsValue, iterator);
        Assert.assertEquals(titleValue, item.getTitle());

        String categoriesValue = "category1,category2,,,category3,,category3";
        addElementValue(item, Item.CATEGORY, categoriesValue, iterator);
        Assert.assertTrue(item.getCategories().size() == 3);
        Assert.assertTrue(item.getCategories().contains("category1"));
        Assert.assertTrue(item.getCategories().contains("category2"));
        Assert.assertTrue(item.getCategories().contains("category3"));
    }
}