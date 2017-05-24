package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by Julia on 16.05.2017.
 */
@Entity
@Table(name ="catalog_item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CatalogItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CatalogItem parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CatalogItem getParent() {
        return parent;
    }

    public void setParent(CatalogItem parent) {
        this.parent = parent;
    }
}
