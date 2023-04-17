package BItlabJavaEE;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalProjectJavaEE", "postgres", "qwerty123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addUser(User user) {
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into users (email, password, fullName, role_id) values (?,?,?,?)");
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullName());
            stmt.setInt(4, user.getRole_id());
            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    public static User getUser(String email) {
        User user = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from users where email = ?");
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("fullname"));
                user.setRole_id(resultSet.getInt("role_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static boolean addNews(News news) {
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into news (post_date, title, content) values(NOW(), ?,?)");
            stmt.setString(1, news.getTitle());
            stmt.setString(2, news.getContent());
            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    public static ArrayList<News> getAllNews() {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from news");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                News blog = new News();
                blog.setId(resultSet.getLong("id"));
                blog.setPost_date(resultSet.getTimestamp("post_date"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                news.add(blog);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return news;
    }

    public static News getNews(Long id) {
        News news = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from news where id = ?");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                news = new News();
                news.setId(resultSet.getLong("id"));
                news.setPost_date(resultSet.getTimestamp("post_date"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return news;
    }

    public static boolean addComment(Comment comment) {
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(" " +
                    "insert into comments(comment, user_id, news_id, post_date) " +
                    "values (?,?,?,NOW())");
            stmt.setString(1, comment.getComment());
            stmt.setLong(2, comment.getUserComment().getId());
            stmt.setLong(3, comment.getNewsComment().getId());
            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }

    public static ArrayList<Comment> getAllComments(Long id) {
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("select comments.id as id,comments.comment as comment,comments.post_date as post_date, " +
                    "comments.user_id as user_id,comments.news_id as news_id,users.email as email,users.fullname as fullname FROM comments JOIN users ON comments.user_id = users.id::varchar " +
                    "where comments.news_id = ?::varchar");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("fullname"));
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));

                comment.setUserComment(user);
                comments.add(comment);
            }
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }
    public static boolean updateUser(User updUser) {
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("update users set fullname=?, email =?, password=? " +
                    "where id = ?");
            stmt.setString(1, updUser.getFullName());
            stmt.setString(2, updUser.getEmail());
            stmt.setString(3, updUser.getPassword());
            stmt.setLong(4, updUser.getId());
            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }
    public static ArrayList<News> getAllNewsSortedAZ() {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from news order by title asc");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                News blog = new News();
                blog.setId(resultSet.getLong("id"));
                blog.setPost_date(resultSet.getTimestamp("post_date"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                news.add(blog);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return news;
    }
    public static ArrayList<News> getAllNewsSortedDate() {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from news order by post_date asc");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                News blog = new News();
                blog.setId(resultSet.getLong("id"));
                blog.setPost_date(resultSet.getTimestamp("post_date"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                news.add(blog);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return news;
    }
    public static boolean deleteNews(Long id) {
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from news where id=?");
            stmt.setLong(1, id);
            rows = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows > 0;
    }
}

