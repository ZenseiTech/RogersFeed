package com.zensei.rogers.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.stream.events.Attribute;
import java.util.Iterator;

/**
 * Created by jorge on 14/02/15.
 */
public class Rss extends AbstractElementValue {

    private static final String VERSION = "version";

    private transient String nameSpaces = "";
    private Channel channel;
    private String version;

    public Channel getChannel() {
        return channel;
    }

    public void addNameSpace(String nameSpace) {
        nameSpaces += "," + nameSpace;
    }

    public String getNameSpaces() {
        return nameSpaces;
    }

    public void setNameSpaces(String nameSpaces) {
        this.nameSpaces = nameSpaces;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    public void setNamespaceValues(Iterator<Object> namespaces) {
        while (namespaces.hasNext()) {
            this.addNameSpace(namespaces.next().toString());
        }
    }

    public void addAttributeValue(Iterator<Attribute> attributes) {
        this.setVersion(getAttributeValue(attributes, VERSION));
    }

    @Override
    public String toString() {
        return "Rss{" +
                "nameSpaces='" + nameSpaces + '\'' +
                ", channel=" + channel +
                ", version='" + version + '\'' +
                '}';
    }

    public String toJson() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(this);
    }
}
