package com.websites.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "category.all", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "category.byName", query = "SELECT c FROM Category c WHERE c.name = :name"),
    @NamedQuery(name = "category.destroyAll", query = "DELETE FROM Category") })
public class Category {
  private Long id;
  private String name;
  private List<Website> websites = new ArrayList<Website>();
  

  public Category() {
    super();
  }

  public Category(String name) {
    super();
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(unique = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public List<Website> getWebsites() {
    return websites;
  }

  public void setWebsites(List<Website> websites) {
    this.websites = websites;
  }
}
