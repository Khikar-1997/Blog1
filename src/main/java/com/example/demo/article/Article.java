package com.example.demo.article;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Article {
    private String name;
    private Date date;
    private String description;
    private int userId;

    public Article(String name, Date date, String description, int userId) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.userId = userId;
    }

    public Article() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = Date.valueOf(LocalDate.now());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return userId == article.userId &&
                Objects.equals(name, article.name) &&
                Objects.equals(date, article.date) &&
                Objects.equals(description, article.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, description, userId);
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
