package com.websites.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "order.all", query = "SELECT o FROM Order o")
public class Order {
  private Long id;
  private double price;
  private String description;
  private String status;
  private Website website;
  private Client client;

  public Order() {
    super();
  }

  public Order(double price, String description, String status, Website website, Client client) {
    super();
    this.price = price;
    this.description = description;
    this.status = status;
    this.website = website;
    this.client = client;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public Website getWebsite() {
    return website;
  }

  public void setWebsite(Website website) {
    this.website = website;
  }

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

}
