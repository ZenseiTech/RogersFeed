package com.zensei.rogers.model;

import javax.xml.stream.events.Attribute;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by jorger on 2/13/15.
 */
public class Item extends AbstractElementValue {

    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String DESCRIPTION = "description";

    public static final String CONTENT_ENCODED = "content:encoded";

    public static final String COMMENTS = "comments";
    public static final String PUB_DATE = "pubDate";
    public static final String DC_CREATOR = "dc:creator";
    public static final String CATEGORY = "category";
    public static final String GUID = "guid";
    public static final String PERMALINK = "isPermaLink";
    public static final String WFW_COMMENTS_RSS = "wfw:commentRss";
    public static final String SLASH_COMMENTS = "slash:comments";

    private String title;
    private String link;
    private String pubDate;
    private String creator;
    private transient String categoryValues = "";
    private Set<String> categories = new HashSet<String>();
    private String guidPermalink;
    private String guid;
    private String description;
    private String contentEncoded;
    private String commentRss;
    private String slashComments;
    private String comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getContentEncoded() {
        return contentEncoded;
    }

    public void setContentEncoded(String contentEncoded) {
        this.contentEncoded = contentEncoded;
    }

    public String getCategoryValues() {
        return categoryValues;
    }

    public void setCategoryValues(String categoryValues) {
        this.categoryValues = categoryValues;
        this.setCategories();
    }

    public void addCategory(String category) {
        this.categoryValues += "," + category;
        this.setCategories();
    }
    
    public void setCategories() {
        String [] values = this.categoryValues.split(",");
        for(String value : values) {
            if (!value.isEmpty()) {
                this.categories.add(value);
            }
        }
    }

    public Set<String> getCategories() {
        return this.categories;
    }

    public void setGuidPermalink(String guidPermalink) {
        this.guidPermalink = guidPermalink;
    }

    public String getGuidPermalink() {
        return guidPermalink;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommentRss() {
        return commentRss;
    }

    public void setCommentRss(String commentRss) {
        this.commentRss = commentRss;
    }

    public String getSlashComments() {
        return slashComments;
    }

    public void setSlashComments(String slashComments) {
        this.slashComments = slashComments;
    }

    /**
     * 
     * @param localPart
     * @param elementText
     * @param attributes
     */
    @Override
    public void addElementValue(String localPart, String elementText, Iterator<Attribute> attributes) {
        
        if (localPart.equals(TITLE)) {
            this.setTitle(elementText);
        }
        if (localPart.equals(LINK)) {
            this.setLink(elementText);
        }
        if (localPart.equals(DESCRIPTION)) {
            this.setDescription(elementText);
        }
        if (localPart.equals(CONTENT_ENCODED)) {
            this.setContentEncoded(elementText);
        }
        if (localPart.equals(COMMENTS)) {
            this.setComments(elementText);
        }
        if (localPart.equals(PUB_DATE)) {
            this.setPubDate(elementText);
        }
        if (localPart.equals(DC_CREATOR)) {
            this.setCreator(elementText);
        }
        if (localPart.equals(CATEGORY)) {
            this.addCategory(elementText);
        }
        if (localPart.equals(GUID)) {
            this.setGuid(elementText);
            this.setGuidPermalink(getAttributeValue(attributes, PERMALINK));
        }
        if (localPart.equals(WFW_COMMENTS_RSS)) {
            this.setCommentRss(elementText);
        }
        if (localPart.equals(SLASH_COMMENTS)) {
            this.setSlashComments(elementText);
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", creator='" + creator + '\'' +
                ", categoryValues='" + categoryValues + '\'' +
                ", guidPermalink='" + guidPermalink + '\'' +
                ", guid='" + guid + '\'' +
                ", description='" + description + '\'' +
                ", contentEncoded='" + contentEncoded + '\'' +
                ", commentRss='" + commentRss + '\'' +
                ", slashComments='" + slashComments + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

}
