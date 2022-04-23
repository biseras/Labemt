package com.example.labemt.Model.dto;

import com.example.labemt.Model.Avtor;
import com.example.labemt.Model.Enumerations.Category;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

public class KnigaDto {
    private String name;
    private Category category;
    private Long avtor;
    private Integer availablecopies;

    public KnigaDto(String name, Category category, Long avtor, Integer availablecopies) {
        this.name = name;
        this.category = category;
        this.avtor = avtor;
        this.availablecopies = availablecopies;
    }

    public String getName() {
        return name;
    }



    public Integer getAvailablecopies() {
        return availablecopies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getAvtor() {
        return avtor;
    }

    public void setAvtor(Long avtor) {
        this.avtor = avtor;
    }

    public void setAvailablecopies(Integer availablecopies) {
        this.availablecopies = availablecopies;
    }
}
