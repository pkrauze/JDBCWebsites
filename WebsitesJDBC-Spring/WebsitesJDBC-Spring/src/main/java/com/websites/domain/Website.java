package com.websites.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "website.all", query = "SELECT w FROM Website w"),
    @NamedQuery(name = "website.byDomain", query = "SELECT w FROM Website w WHERE w.domain = :domain"),
    @NamedQuery(name = "website.destroyAll", query = "DELETE FROM Website") })
public class Website {
  private Long id;
  private String domain;
  private int pageRank;
  private int domainAuthority;
  private double price;
  private Date createdAt;
  private Client client;
  private Category category;
  

  public Website() {
    super();
  }

  public Website(String domain, int pageRank, int domainAuthority, double price, Date createdAt, Client client, Category category) {
    super();
    this.domain = domain;
    this.pageRank = pageRank;
    this.domainAuthority = domainAuthority;
    this.price = price;
    this.createdAt = createdAt;
    this.client = client;
    this.category = category;
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
  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public int getPageRank() {
	  return pageRank;
  }

  public void setPageRank(int pageRank) {
	  this.pageRank = pageRank;
  }

  public int getDomainAuthority() {
	  return domainAuthority;
  }

  public void setDomainAuthority(int domainAuthority) {
	  this.domainAuthority = domainAuthority;
  }
  
  public double getPrice() {
	  return price;
  }

  public void setPrice(double price) {
	  this.price = price;
  }

  public Date getCreatedAt() {
	  return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
	  this.createdAt = createdAt;
  }

   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   public Client getClient() {
     return client;
   }

   public void setClient(Client client) {
     this.client = client;
  }

   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   public Category getCategory() {
     return category;
   }

   public void setCategory(Category category) {
     this.category = category;
  }
}
