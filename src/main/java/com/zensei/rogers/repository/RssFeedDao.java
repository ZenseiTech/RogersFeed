package com.zensei.rogers.repository;

import com.zensei.rogers.model.AtomLink;
import com.zensei.rogers.model.Channel;
import com.zensei.rogers.model.Item;
import com.zensei.rogers.model.Rss;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Created by jorge on 14/02/15.
 */
public class RssFeedDao extends JdbcDaoSupport {

    /**
     * *
     */
    public void deleteRss() {
        String deleteSql = "DELETE FROM rss";
        getJdbcTemplate().update(deleteSql);
    }

    /**
     * *
     *
     * @param rss
     */
    public void insertFeed(final Rss rss) throws Exception {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(this.getDataSource());

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String insertIntoSql = "INSERT INTO rss (version, namespaces) VALUES(?,?)";

        try {
            getJdbcTemplate().update(
                    new PreparedStatementCreator() {
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            PreparedStatement ps = connection.prepareStatement(insertIntoSql, Statement.RETURN_GENERATED_KEYS);
                            ps.setString(1, rss.getVersion());
                            ps.setString(2, rss.getNameSpaces());
                            return ps;
                        }
                    }, keyHolder);

            insertChannel(keyHolder.getKey().intValue(), rss.getChannel());
            transactionManager.commit(status);
        } catch(Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * *
     *
     * @param id
     * @param channel
     */
    private void insertChannel(final int id, final Channel channel) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String insertIntoSql = "INSERT INTO channel (rss_id, title, description, link, lastBuildDate, " +
                "language, updatePeriod, updateFrequency, generator) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(insertIntoSql, Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, id);
                        ps.setString(2, channel.getTitle());
                        ps.setString(3, channel.getDescription());
                        ps.setString(4, channel.getLink());
                        ps.setString(5, channel.getLastBuildDate());
                        ps.setString(6, channel.getLanguage());
                        ps.setString(7, channel.getUpdatePeriod());
                        ps.setString(8, channel.getUpdateFrequency());
                        ps.setString(9, channel.getGenerator());
                        return ps;
                    }
                }, keyHolder);

        insertAtomLink(keyHolder.getKey().intValue(), channel.getAtomLink());
        insertItems(keyHolder.getKey().intValue(), channel.getItems());
    }

    /**
     * @param id
     * @param atomLink
     */
    private void insertAtomLink(final int id, final AtomLink atomLink) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String insertIntoSql = "INSERT INTO atomLink (channel_id, href, rel, type) VALUES(?,?,?,?)";

        getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(insertIntoSql, Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, id);
                        ps.setString(2, atomLink.getHref());
                        ps.setString(3, atomLink.getRel());
                        ps.setString(4, atomLink.getType());
                        return ps;
                    }
                }, keyHolder);
    }

    /**
     * *
     *
     * @param id
     * @param items
     */
    private void insertItems(final int id, final List<Item> items) {

        String sql = "INSERT INTO item " +
                "(channel_id, title, link, pubDate, creator, categories, guidPermalink, guid, " +
                "description, contentEncoded, commentRss, slashComments, comments) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Item item = items.get(i);
                ps.setInt(1, id);
                ps.setString(2, item.getTitle());
                ps.setString(3, item.getLink());
                ps.setString(4, item.getPubDate());
                ps.setString(5, item.getCreator());
                ps.setString(6, item.getCategoryValues());
                ps.setString(7, item.getGuidPermalink());
                ps.setString(8, item.getGuid());
                ps.setString(9, item.getDescription());
                ps.setString(10, item.getContentEncoded());
                ps.setString(11, item.getCommentRss());
                ps.setString(12, item.getSlashComments());
                ps.setString(13, item.getComments());
            }

            @Override
            public int getBatchSize() {
                return items.size();
            }
        });
    }

    /**
     * *
     *
     * @return
     */
    public Rss getRssFeed() {
        String sql = "SELECT *  FROM rss";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        Rss rss = new Rss();

        for (Map row : rows) {
            int id = (Integer) row.get("id");
            rss.setVersion((String) row.get("version"));
            rss.setNameSpaces((String) row.get("namespaces"));
            rss.setChannel(getChannel(id));
        }

        return rss;
    }

    /**
     * *
     *
     * @param extId
     * @return
     */
    private Channel getChannel(int extId) {

        Channel channel = new Channel();

        String sql = "SELECT *  FROM channel where rss_id = ?";
        Object[] parameters = new Object[]{extId};

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, parameters);

        for (Map row : rows) {
            int id = (Integer) row.get("id");
            channel.setTitle((String) row.get("title"));
            channel.setDescription((String) row.get("description"));
            channel.setLink((String) row.get("link"));
            channel.setLastBuildDate((String) row.get("lastBuildDate"));
            channel.setLanguage((String) row.get("language"));
            channel.setUpdatePeriod((String) row.get("updatePeriod"));
            channel.setUpdateFrequency((String) row.get("updateFrequency"));
            channel.setGenerator((String) row.get("generator"));
            setItems(channel, id);
            channel.setAtomLink(getAtomLink(id));
        }
        return channel;
    }

    private AtomLink getAtomLink(int extId) {

        String sql = "SELECT *  FROM atomLink where channel_id = ?";
        Object[] parameters = new Object[]{extId};

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, parameters);

        AtomLink atomLink = new AtomLink();

        for (Map row : rows) {
            atomLink.setHref((String) row.get("href"));
            atomLink.setRel((String) row.get("rel"));
            atomLink.setType((String) row.get("type"));
        }
        return atomLink;
    }

    /**
     * *
     *
     * @param channel
     * @param extId
     */
    private void setItems(Channel channel, int extId) {

        String sql = "SELECT *  FROM item where channel_id = ?";
        Object[] parameters = new Object[]{extId};

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, parameters);

        for (Map row : rows) {
            Item item = new Item();
            channel.addItem(item);
            item.setTitle((String) row.get("title"));
            item.setLink((String) row.get("link"));
            item.setPubDate((String) row.get("pubDate"));
            item.setCreator((String) row.get("creator"));
            item.setCategoryValues((String) row.get("categories"));
            item.setGuidPermalink((String) row.get("guidPermalink"));
            item.setGuid((String) row.get("guid"));
            item.setDescription((String) row.get("description"));
            item.setContentEncoded((String) row.get("contentEncoded"));
            item.setCommentRss((String) row.get("commentRss"));
            item.setSlashComments((String) row.get("slashComments"));
            item.setComments((String) row.get("comments"));
        }
    }

}
