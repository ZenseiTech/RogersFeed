create database maccleanfeed;


CREATE TABLE rss(id int NOT NULL AUTO_INCREMENT,
    version VARCHAR(20) NOT NULL,
    namespaces VARCHAR(2000) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=INNODB;


CREATE TABLE channel (id int NOT NULL AUTO_INCREMENT,
				rss_id INT NOT NULL,
				title VARCHAR(200) NOT NULL,
				description VARCHAR(200) NOT NULL,
				link VARCHAR(200) NOT NULL,
				lastBuildDate VARCHAR(200) NOT NULL,
				language VARCHAR(200) NOT NULL,
				updatePeriod VARCHAR(200) NOT NULL,
				updateFrequency VARCHAR(200) NOT NULL,
				generator VARCHAR(200) NOT NULL,
				PRIMARY KEY (id),
                    INDEX (rss_id),
                    FOREIGN KEY (rss_id) REFERENCES rss(id)
                      ON DELETE CASCADE
) ENGINE=INNODB;



CREATE TABLE atomLink (id int NOT NULL AUTO_INCREMENT,
				channel_id INT NOT NULL,
				href VARCHAR(200) NOT NULL,
				rel VARCHAR(200) NOT NULL,
				type VARCHAR(200) NOT NULL,
				PRIMARY KEY (id),
                    INDEX (channel_id),
                    FOREIGN KEY (channel_id) REFERENCES channel(id)
                      ON DELETE CASCADE
) ENGINE=INNODB;


CREATE TABLE item (id int NOT NULL AUTO_INCREMENT,
				channel_id INT NOT NULL,
				title VARCHAR(200) NOT NULL,
				link VARCHAR(200) NOT NULL,
				pubDate VARCHAR(200) NOT NULL,
				creator VARCHAR(200) NOT NULL,
				categories VARCHAR(200) NOT NULL,
				guidPermalink VARCHAR(200) NOT NULL,
				guid VARCHAR(200) NOT NULL,
				description VARCHAR(2000) NOT NULL,
				contentEncoded TEXT NOT NULL,
				commentRss VARCHAR(200) NOT NULL,
				slashComments VARCHAR(200) NOT NULL,
				comments VARCHAR(200) NOT NULL,
				PRIMARY KEY (id),
                    INDEX (channel_id),
                    FOREIGN KEY (channel_id) REFERENCES channel(id)
                      ON DELETE CASCADE
) ENGINE=INNODB;