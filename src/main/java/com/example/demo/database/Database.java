package com.example.demo.database;

import com.example.demo.service.MariaDbConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static Database instance = new Database();

    private Database(){
    }

    private void createDatabase(){
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL_SEREVER,MariaDbConstant.USER,MariaDbConstant.PASS)){
            String query = "CREATE DATABASE blog";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void createUserTable(){
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.PASS)){
            String query = "CREATE TABLE user(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(255)NOT NULL ," +
                    "surname VARCHAR (255)NOT NULL ," +
                    "username VARCHAR (255)NOT NULL ," +
                    "password VARCHAR (255)NOT NULL ," +
                    "PRIMARY KEY (id)" +
                    ");";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void createArticleTable(){
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.PASS)){
            String query = "CREATE TABLE article(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(255)NOT NULL ," +
                    "date DATE NOT NULL ," +
                    "description VARCHAR (255) NOT NULL ," +
                    "PRIMARY KEY (id)" +
                    ");";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void createUserArticleTable(){
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.PASS)){
            String query = "CREATE TABLE user_article(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "user_id INT NOT NULL UNIQUE ," +
                    "article_id INT NOT NULL ," +
                    "PRIMARY KEY (id)," +
                    "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ," +
                    "FOREIGN KEY (article_id) REFERENCES article(id) ON DELETE CASCADE " +
                    ");";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void initializeDatabase(){
        Database database = Database.instance;
        database.createDatabase();
        database.createUserTable();
        database.createArticleTable();
        database.createUserArticleTable();
    }
}
