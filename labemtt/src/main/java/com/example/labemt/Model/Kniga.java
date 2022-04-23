package com.example.labemt.Model;

import com.example.labemt.Model.Enumerations.Category;

import javax.persistence.*;

@Entity
public class Kniga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Avtor avtor;
    private Integer availablecopies;

    public Kniga(String name, Category category, Avtor avtor, Integer availablecopies) {
        this.name = name;
        this.category = category;
        this.avtor = avtor;
        this.availablecopies = availablecopies;
    }

    public Kniga() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAvtor(Avtor avtor) {
        this.avtor = avtor;
    }

    public void setAvailablecopies(Integer availablecopies) {
        this.availablecopies = availablecopies;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Avtor getAvtor() {
        return avtor;
    }

    public Integer getAvailablecopies() {
        return availablecopies;
    }
}
