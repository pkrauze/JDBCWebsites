package com.websites.service;

import java.util.List;

import com.websites.domain.Website;
import com.websites.domain.Client;
import com.websites.domain.Category;

public interface WebsitesManager {

  //Website
  void addWebsite(Website website);
  List<Website> getAllWebsites();
  void removeAllWebsites();
  void updateWebsite(Website website);
  void removeWebsite(Website website);
  Website findWebsiteById(Long id);
  Website findByDomain(String domain);

  //Category
  void addCategory(Category category);
  List<Category> getAllCategories();
  void removeAllCategories();
  void updateCategory(Category category);
  void removeCategory(Category category);
  Category findCategoryById(Long id);
  Category findCategoryByName(String name);

  //Client
  void addClient(Client client);
  List<Client> getAllClients();
  void removeAllClients();
  void updateClient(Client client);
  void removeClient(Client client);
  Client findClientById(Long id);
  Client findClientByEmail(String email);
}
