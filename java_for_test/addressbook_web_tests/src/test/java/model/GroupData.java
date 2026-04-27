package model;

import jakarta.persistence.*;

@Entity
@Table(name = "group_list")  // замените на реальное имя таблицы в БД
public class GroupData {
    @Id
    @Column(name = "group_id")
    private String id;

    @Column(name = "group_name")
    private String name;

    @Column(name = "group_header")
    private String header;

    @Column(name = "group_footer")
    private String footer;

    // Пустой конструктор для Hibernate (обязателен)
    public GroupData() {
    }

    // Конструктор со всеми полями
    public GroupData(String id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    // Геттеры и сеттеры (обязательны для Hibernate)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    // Fluent-методы (как в вашем record, но без создания нового объекта)
    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    public GroupData withId(String id) {
        this.id = id;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }
}