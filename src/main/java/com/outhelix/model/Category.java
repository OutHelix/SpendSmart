package com.outhelix.model;


import jakarta.persistence.*;

@Entity
@Table(name = "categories", schema = "db")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 10,  nullable = false)
    private CategoryType type;

    public Category() {}

    public Category(int id, String name, CategoryType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public enum CategoryType {
        INCOME,
        EXPENSE
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
