package com.zensei.rogers.process;

import com.zensei.rogers.model.Rss;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class FeedReaderTest {

    @Test
    public void testReadFeed() throws Exception {
        
        FeedReader feedReader = new FeedReader(ClassLoader.class.getResourceAsStream("/maclean.rss"));
        Rss rss = feedReader.readFeed();

        Assert.assertTrue(rss != null);
        Assert.assertTrue(rss.getChannel() != null);
        Assert.assertTrue(rss.getChannel().getAtomLink() != null);
        Assert.assertTrue(rss.getChannel().getItems().size() == 40);

        List<String> expectedJson = FileUtils.readLines(new File("src/test/resources/expectedfeed.json"));
        Assert.assertEquals(expectedJson.get(0), rss.toJson());

    }
}