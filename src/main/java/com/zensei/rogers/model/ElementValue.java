package com.zensei.rogers.model;

import javax.xml.stream.events.Attribute;
import java.util.Iterator;

/**
 * Created by jorge on 15/02/15.
 */
public interface ElementValue {

    public void addElementValue(String localPart, String elementText, Iterator<Attribute> attributes);
}
