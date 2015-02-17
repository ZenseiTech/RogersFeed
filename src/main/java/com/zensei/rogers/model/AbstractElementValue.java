package com.zensei.rogers.model;

import javax.xml.stream.events.Attribute;
import java.util.Iterator;

/**
 * Created by jorge on 15/02/15.
 */
public abstract class AbstractElementValue implements ElementValue {

    /**
     * *
     * @param localPart
     * @param elementText
     * @param attributes
     */
    public void addElementValue(String localPart, String elementText, Iterator<Attribute> attributes) {
        
    }

    /**
     * *
     * @param attributes
     * @param attributeName
     * @return
     */
    public String getAttributeValue(Iterator<Attribute> attributes, String attributeName) {

        while (attributes.hasNext()) {
            Attribute attribute = attributes.next();
            if (attribute.getName().toString().equals(attributeName)) {
                return attribute.getValue();
            }
        }
        return "";
    }
}
