package com.websites.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.websites.domain.Website;
import com.websites.domain.Client;
import com.websites.domain.Category;

@Component
@Transactional
public class WebsitesManagerImpl implements WebsitesManager {

  @Autowired
  private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void addWebsite(Website website) {
    sessionFactory.getCurrentSession().persist(website);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Website> getAllWebsites() {
    return sessionFactory.getCurrentSession().getNamedQuery("website.all").list();
  }
  
  @Override
  public void removeAllWebsites() {
	sessionFactory.getCurrentSession().getNamedQuery("website.destroyAll").executeUpdate();
  }

  @Override
  public void updateWebsite(Website website) {
    sessionFactory.getCurrentSession().saveOrUpdate(website);
  }

  @Override
  public void removeWebsite(Website website) {
    sessionFactory.getCurrentSession().delete(website);
  }

  @Override
  public Website findWebsiteById(Long id) {
    return (Website) sessionFactory.getCurrentSession().get(Website.class, id);
  }

  @Override
  public Website findByDomain(String domain) {
    return (Website) sessionFactory.getCurrentSession().getNamedQuery("website.byDomain")
        .setString("domain", domain).uniqueResult();
  }

  @Override
  public void addCategory(Category category) {
    sessionFactory.getCurrentSession().persist(category);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Category> getAllCategories() {
    return sessionFactory.getCurrentSession().getNamedQuery("category.all").list();
  }
  
  @Override
  public void removeAllCategories() {
	sessionFactory.getCurrentSession().getNamedQuery("category.destroyAll").executeUpdate();
  }

  @Override
  public void updateCategory(Category category) {
    sessionFactory.getCurrentSession().saveOrUpdate(category);
  }

  @Override
  public void removeCategory(Category category) {
    sessionFactory.getCurrentSession().delete(category);
  }

  @Override
  public Category findCategoryById(Long id) {
    return (Category) sessionFactory.getCurrentSession().get(Category.class, id);
  }

  @Override
  public Category findCategoryByName(String name) {
    return (Category) sessionFactory.getCurrentSession().getNamedQuery("category.byName")
      .setString("name", name).uniqueResult();
  }

  @Override
  public void addClient(Client client) {
    sessionFactory.getCurrentSession().persist(client);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Client> getAllClients() {
    return sessionFactory.getCurrentSession().getNamedQuery("client.all").list();
  }

  @Override
  public void removeAllClients() {
	sessionFactory.getCurrentSession().getNamedQuery("client.destroyAll").executeUpdate();
  }
  
  @Override
  public void updateClient(Client client) {
    sessionFactory.getCurrentSession().saveOrUpdate(client);
  }

  @Override
  public void removeClient(Client client) {
    sessionFactory.getCurrentSession().delete(client);
  }

  @Override
  public Client findClientById(Long id) {
    return (Client) sessionFactory.getCurrentSession().get(Client.class, id);
  }

  @Override
  public Client findClientByEmail(String email) {
    return (Client) sessionFactory.getCurrentSession().getNamedQuery("client.byDomain")
      .setString("email", email).uniqueResult();
  }
}
