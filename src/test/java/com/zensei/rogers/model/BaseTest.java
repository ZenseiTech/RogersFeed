package com.zensei.rogers.model;

import javax.xml.stream.events.Attribute;
import java.util.Iterator;

/**
 * Created by jorge on 16/02/15.
 */
public class BaseTest {

    protected void addElementValue(AbstractElementValue elementValue, String localPart, String value, Iterator<Attribute> attributes) {
        elementValue.addElementValue(localPart, value, attributes);
    }
}
