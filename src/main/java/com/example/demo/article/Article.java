package com.example.demo.article;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Article {
    private String name;
    private Date date = Date.valueOf(LocalDate.now());
    private String description;

    public Article(String name, Date date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
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
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDexcription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(name, article.name) &&
                Objects.equals(date, article.date) &&
                Objects.equals(description, article.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, description);
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
