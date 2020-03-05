package com.example.demo.service;

import com.example.demo.article.Article;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class ArticleService {
    public void create(Article article) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "INSERT INTO article(name,date,description,user_id)VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, article.getName());
            preparedStatement.setDate(2, article.getDate());
            preparedStatement.setString(3, article.getDescription());
            preparedStatement.setInt(4, article.getUserId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Article> selectAllArticls() {
        ArrayList<Article> articles = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "SELECT * FROM article";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Article article = new Article();
                article.setName(resultSet.getString("name"));
                article.setDate(resultSet.getDate("date"));
                article.setDescription(resultSet.getString("description"));
                article.setUserId(resultSet.getInt("user_id"));
                articles.add(article);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return articles;
    }

    public Article findUserById(int id) {
        Article article = new Article();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "SELECT * FROM article WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                article.setName(resultSet.getString("name"));
                article.setDate(resultSet.getDate("date"));
                article.setDescription(resultSet.getString("description"));
                article.setUserId(resultSet.getInt("user_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return article;
    }

    public void update(int id, Article article) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "UPDATE article SET name = ?,date = DATE_ADD(now()),description = ?,user_id = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, article.getName());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setInt(3, article.getUserId());
            preparedStatement.setInt(4, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "DELETE FROM article WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Article> selectArticlsByUserId(int userId) {
        ArrayList<Article> articles = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "SELECT * FROM article WHERE user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Article article = new Article();
                article.setName(resultSet.getString("name"));
                article.setDate(resultSet.getDate("date"));
                article.setDescription(resultSet.getString("description"));
                article.setUserId(resultSet.getInt("user_id"));
                articles.add(article);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return articles;
    }
}
