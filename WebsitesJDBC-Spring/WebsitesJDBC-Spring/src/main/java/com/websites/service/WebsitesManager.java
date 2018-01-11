package com.websites.service;

import java.util.List;

import com.websites.domain.Website;
import com.websites.domain.Client;
import com.websites.domain.Order;

public interface WebsitesManager {

  //Website
  void addWebsite(Website website);
  List<Website> getAllWebsites();
  void updateWebsite(Website website);
  void removeWebsite(Website website);
  Website findWebsiteById(Long id);
  Website findByDomain(String domain);

  //Order
  void addOrder(Order order);
  List<Order> getAllOrders();
  void updateOrder(Order order);
  void removeOrder(Order order);
  Order findOrderById(Long id);

  //Client
  void addClient(Client client);
  List<Client> getAllClients();
  void updateClient(Client client);
  void removeClient(Client client);
  Client findClientById(Long id);
  Client findClientByEmail(String email);
}
