package ru.job4j.grabber;

import ru.job4j.entity.Post;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private Connection cn;

    public PsqlStore(Properties cfg) throws SQLException {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        cn = DriverManager.getConnection(
                cfg.getProperty("url"),
                cfg.getProperty("username"),
                cfg.getProperty("password")
        );
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement ps = cn.prepareStatement("insert into post(id, name, text, link, created) values(?,?,?,?,?)")) {
            ps.setInt(1, post.getId());
            ps.setString(2, post.getName());
            ps.setString(3, post.getText());
            ps.setString(4, post.getLink());
            ps.setTimestamp(5, Timestamp.valueOf(post.getCreated()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        try (Statement stmt = cn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from post");
            while (rs.next()) {
                rsl.add(new Post(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("link"),
                        rs.getTimestamp("created").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Post findById(String id) {
        try (PreparedStatement ps = cn.prepareStatement("select * from post where id=?")) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Post(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("link"),
                        rs.getTimestamp("created").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        try (InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            Properties props = new Properties();
            props.load(in);
            in.close();
            Store store = new PsqlStore(props);

            Post post1 = new Post(1, "name1", "text", "link1", LocalDateTime.now());
            store.save(post1);
            assert store.findById("1").equals(post1);

            Post post2 = new Post(2, "name1", "text", "link2", LocalDateTime.now());
            store.save(post2);
            assert store.getAll().equals(List.of(post1, post2));
        }
    }
}
