package com.zensei.rogers.model;

import javax.xml.stream.events.Attribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jorge on 14/02/15.
 */
public class Channel extends AbstractElementValue{

    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String GENERATOR = "generator";
    public static final String ATOM_LINK = "atom:link";
    public static final String DESCRIPTION = "description";
    public static final String LAST_BUILD_DATE = "lastBuildDate";
    public static final String LANGUAGE = "language";
    public static final String SY_UPDATE_PERIOD = "sy:updatePeriod";
    public static final String SY_UPDATE_FREQUENCY = "sy:updateFrequency";

    private String title;
    private String description;
    private AtomLink atomLink;
    private String link;
    private String lastBuildDate;
    private String language;
    private String updatePeriod;
    private String updateFrequency;
    private String generator;

    private List<Item> items = new ArrayList<Item>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public AtomLink getAtomLink() {
        return atomLink;
    }

    public void setAtomLink(AtomLink atomLink) {
        this.atomLink = atomLink;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUpdatePeriod() {
        return updatePeriod;
    }

    public void setUpdatePeriod(String updatePeriod) {
        this.updatePeriod = updatePeriod;
    }

    public String getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    /**
     * *
     * @param localPart
     * @param elementText
     */
    @Override
    public void addElementValue(String localPart, String elementText, Iterator<Attribute> attributes) {

        if (localPart.equals(TITLE)) {
            this.setTitle(elementText);
        }
        if (localPart.equals(DESCRIPTION)) {
            this.setDescription(elementText);
        }
        if (localPart.equals(LAST_BUILD_DATE)) {
            this.setLastBuildDate(elementText);
        }
        if (localPart.equals(LANGUAGE)) {
            this.setLanguage(elementText);
        }
        if (localPart.equals(SY_UPDATE_PERIOD)) {
            this.setUpdatePeriod(elementText);
        }
        if (localPart.equals(SY_UPDATE_FREQUENCY)) {
            this.setUpdateFrequency(elementText);
        }
        if (localPart.equals(LINK)) {
            this.setLink(elementText);
        }
        if (localPart.equals(GENERATOR)) {
            this.setGenerator(elementText);
        }
        if (localPart.equals(ATOM_LINK)) {
            AtomLink atomLink = new AtomLink();
            this.setAtomLink(atomLink);
            atomLink.setHref(getAttributeValue(attributes, "href"));
            atomLink.setRel(getAttributeValue(attributes, "rel"));
            atomLink.setType(getAttributeValue(attributes, "type"));
        }
    }

    @Override
    public String toString() {
        return "Channel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", atomLink=" + atomLink +
                ", link='" + link + '\'' +
                ", lastBuildDate='" + lastBuildDate + '\'' +
                ", language='" + language + '\'' +
                ", updatePeriod='" + updatePeriod + '\'' +
                ", updateFrequency='" + updateFrequency + '\'' +
                ", generator='" + generator + '\'' +
                ", items=" + items +
                '}';
    }
}
