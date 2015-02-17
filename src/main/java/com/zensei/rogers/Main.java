package com.zensei.rogers;

import com.zensei.rogers.model.Rss;
import com.zensei.rogers.process.FeedReader;
import com.zensei.rogers.repository.RssFeedDao;
import com.zensei.rogers.utils.Utility;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.io.FileUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.File;

/**
 * Created by jorge on 14/02/15.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        CompositeConfiguration config = Utility.loadProperties("application.properties");

        Rss rss = getRssFromFeed(config);
        
        if(config.getBoolean("use.datbase")) {
            
            RssFeedDao dao = new RssFeedDao();
            dao.setDataSource(getDataSource(config));
            dao.deleteRss();
            dao.insertFeed(rss);

            Rss rssFeed = dao.getRssFeed();
            FileUtils.writeStringToFile(new File(config.getString("rss.json")), rssFeed.toJson());
            
        } else {
            
            FileUtils.writeStringToFile(new File(config.getString("rss.json")), rss.toJson());
            
        }
        
    }
    
    private static Rss getRssFromFeed(CompositeConfiguration config) throws Exception {
        return new FeedReader(config.getString("feedUrl")).readFeed();
    }
    
    private static DriverManagerDataSource getDataSource(CompositeConfiguration config) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getString("dataSource.driverClassName"));
        dataSource.setUrl(config.getString("dataSource.url"));
        dataSource.setUsername(config.getString("dataSource.username"));
        dataSource.setPassword(config.getString("dataSource.password"));
        return dataSource;
    }
}
