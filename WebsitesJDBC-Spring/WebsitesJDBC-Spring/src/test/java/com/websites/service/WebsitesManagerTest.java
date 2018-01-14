package com.websites.service;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.websites.domain.Website;
import com.websites.domain.Client;
import com.websites.domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class WebsitesManagerTest {

  private static final double _PRECISION = 0.01;

  //Website examples
  private static final String DOMAIN_1 = "test1.com";
  private static final int PR_1 = 50;
  private static final int DOMAIN_AUTHORITY_1 = 55;
  private static final Date CREATED_AT_1 = new Date(2017,1,1);
  private static final double PRICE_1 = 120.99;

  private static final String DOMAIN_2 = "test2.com";
  private static final int PR_2 = 60;
  private static final int DOMAIN_AUTHORITY_2 = 65;
  private static final Date CREATED_AT_2 = new Date(2017,2,2);
  private static final double PRICE_2 = 220.99;

  private static final String DOMAIN_3 = "test3.com";
  private static final int PR_3 = 70;
  private static final int DOMAIN_AUTHORITY_3 = 75;
  private static final Date CREATED_AT_3 = new Date(2017,3,3);
  private static final double PRICE_3 = 320.99;

  //Category examples
  private static final String NAME_1 = "IT";
  private static final String NAME_2 = "Cooking";
  private static final String NAME_3 = "News";

  //Client examples
  private static final String FIRSTNAME_1 = "John";
  private static final String LASTNAME_1 = "Johnovsky";
  private static final String EMAIL_1 = "john.johnovsky@example.com";

  private static final String FIRSTNAME_2 = "Mark";
  private static final String LASTNAME_2 = "Markovsky";
  private static final String EMAIL_2 = "mark.markovsky@example.com";

  private static final String FIRSTNAME_3 = "Eddy";
  private static final String LASTNAME_3 = "Eddovsky";
  private static final String EMAIL_3 = "eddy.eddovsky@example.com";


  @Autowired
  WebsitesManager manager;

  //Websites
  @Test
  public void shouldAddOneWebsite() {
    int counterBeforeAddOneWebsite = manager.getAllWebsites().size();
    Website website = new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, PRICE_1, CREATED_AT_1, new Client(), new Category());
    manager.addWebsite(website);
    int websitesCounter = manager.getAllWebsites().size();
    assertEquals(counterBeforeAddOneWebsite + 1, websitesCounter);

    Website temporaryWebsite = manager.getAllWebsites().get(websitesCounter - 1);
    manager.removeWebsite(temporaryWebsite);
  }

  @Test
  public void shouldGetAllWebsites() {
    int websitesCounter = manager.getAllWebsites().size();
    assertThat(websitesCounter, either(is(0)).or(is(1)));
  }
  
  @Test
  public void shouldRemoveAllWebsites() {
    Website website = new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, PRICE_1, CREATED_AT_1, new Client(), new Category());
    manager.addWebsite(website);
    manager.removeAllWebsites();
    int websitesCounter = manager.getAllWebsites().size();
    assertThat(websitesCounter, is(0));
  }

  @Test
  public void shouldUpdateAllFieldsWebsite() {
    Website website = new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, PRICE_1, CREATED_AT_1, new Client(), new Category());
    manager.addWebsite(website);

    int websitesCounter = manager.getAllWebsites().size();
    Website retrievedWebsite = manager.getAllWebsites().get(websitesCounter - 1);
    retrievedWebsite.setDomain(DOMAIN_2);
    retrievedWebsite.setPageRank(PR_2);
    retrievedWebsite.setDomainAuthority(DOMAIN_AUTHORITY_2);
    retrievedWebsite.setPrice(PRICE_2);
    retrievedWebsite.setCreatedAt(CREATED_AT_2);
    retrievedWebsite.setClient(new Client(FIRSTNAME_1, LASTNAME_1, EMAIL_1));
    retrievedWebsite.setCategory(new Category(NAME_1));
    manager.updateWebsite(retrievedWebsite);

    retrievedWebsite = manager.getAllWebsites().get(websitesCounter - 1);
    assertEquals(retrievedWebsite.getDomain(), DOMAIN_2);
    assertEquals(retrievedWebsite.getPageRank(), PR_2);
    assertEquals(retrievedWebsite.getDomainAuthority(), DOMAIN_AUTHORITY_2);
    assertEquals(retrievedWebsite.getPrice(), PRICE_2, _PRECISION);
    assertEquals(retrievedWebsite.getCreatedAt(), CREATED_AT_2);
    assertEquals(retrievedWebsite.getClient().getFirstName(), FIRSTNAME_1);
    assertEquals(retrievedWebsite.getCategory().getName(), NAME_1);

    Website temporaryWebsite = manager.getAllWebsites().get(websitesCounter - 1);
    manager.removeWebsite(temporaryWebsite);
  }

  @Test
  public void shouldRemoveOneAddedWebsite() {
    Website website = new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, PRICE_1, CREATED_AT_1, new Client(), new Category());
    manager.addWebsite(website);

    int counterBeforeRemoveOneWebsite = manager.getAllWebsites().size();
    Website retrievedWebsite = manager.getAllWebsites().get(counterBeforeRemoveOneWebsite - 1);
    manager.removeWebsite(retrievedWebsite);

    int websitesCounter = manager.getAllWebsites().size();
    assertEquals(counterBeforeRemoveOneWebsite - 1, websitesCounter);
  }

  @Test
  public void shouldFindCorrectlyWebsiteById() {
    Website website = new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, PRICE_1, CREATED_AT_1, new Client(), new Category());
    manager.addWebsite(website);

    long id = website.getId();
    Website retrievedWebsite = manager.findWebsiteById(id);
    assertEquals(retrievedWebsite, website);

    manager.removeWebsite(retrievedWebsite);
  }

  @Test
  public void shouldFindCorrectlyWebsiteByDomain() {
    Website website = new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, PRICE_1, CREATED_AT_1, new Client(), new Category());
    manager.addWebsite(website);

    String domain = website.getDomain();
    Website retrievedWebsite = manager.findByDomain(domain);
    assertEquals(retrievedWebsite, website);

    manager.removeWebsite(retrievedWebsite);
  }

  //Categories
  @Test
  public void shouldAddOneCategory() {
    int counterBeforeAddOneCategory = manager.getAllCategories().size();
    Category category = new Category(NAME_1);
    manager.addCategory(category);

    int categoriesCounter = manager.getAllCategories().size();
    assertEquals(counterBeforeAddOneCategory + 1, categoriesCounter);
    Category temporaryCategory = manager.getAllCategories().get(categoriesCounter - 1);

    manager.removeCategory(temporaryCategory);
  }

  @Test
  public void shouldGetAllCategories() {
    int categoriesCounter = manager.getAllCategories().size();
    assertThat(categoriesCounter, either(is(0)).or(is(1)));
  }
  
  @Test
  public void shouldRemoveAllCategories() {
	Category category = new Category(NAME_1);
	manager.addCategory(category);
    manager.removeAllCategories();
    int categoriesCounter = manager.getAllCategories().size();
    assertThat(categoriesCounter, is(0));
  }

  @Test
  public void shouldUpdateAllFieldsCategory() {
    Category category = new Category(NAME_1);
    manager.addCategory(category);

    int categoriesCounter = manager.getAllCategories().size();
    Category retrievedCategory = manager.getAllCategories().get(categoriesCounter - 1);
    retrievedCategory.setName(NAME_2);
    manager.updateCategory(retrievedCategory);

    retrievedCategory = manager.getAllCategories().get(categoriesCounter - 1);
    assertEquals(retrievedCategory.getName(), NAME_2);

    Category temporaryCategory = manager.getAllCategories().get(categoriesCounter - 1);
    manager.removeCategory(temporaryCategory);
  }

  @Test
  public void shouldRemoveOneAddedCategory() {
    Category category = new Category(NAME_1);
    manager.addCategory(category);

    int counterBeforeRemoveOneCategory = manager.getAllCategories().size();
    Category retrievedCategory = manager.getAllCategories().get(counterBeforeRemoveOneCategory - 1);
    manager.removeCategory(retrievedCategory);

    int categoriesCounter = manager.getAllCategories().size();
    assertEquals(counterBeforeRemoveOneCategory - 1, categoriesCounter);
  }

  @Test
  public void shouldFindCorrectlyCategoryById() {
    Category category = new Category(NAME_1);
    manager.addCategory(category);

    long id = category.getId();
    Category retrievedCategory = manager.findCategoryById(id);
    assertEquals(retrievedCategory, category);

    manager.removeCategory(retrievedCategory);
  }

  @Test
  public void shouldFindCorrectlyCategoryByName() {
    Category category = new Category(NAME_1);
    manager.addCategory(category);

    String name = category.getName();
    Category retrievedCategory = manager.findCategoryByName(name);
    assertEquals(retrievedCategory, category);

    manager.removeCategory(retrievedCategory);
  }

  //Clients
  @Test
  public void shouldAddOneClient() {
    int counterBeforeAddOneClient = manager.getAllClients().size();
    Client client = new Client(FIRSTNAME_1, LASTNAME_1, EMAIL_1);
    manager.addClient(client);

    int clientsCounter = manager.getAllClients().size();
    assertEquals(counterBeforeAddOneClient + 1, clientsCounter);

    Client temporaryClient = manager.getAllClients().get(clientsCounter - 1);
    manager.removeClient(temporaryClient);
  }

  @Test
  public void shouldGetAllClients() {
    int clientsCounter = manager.getAllClients().size();
    assertThat(clientsCounter, either(is(0)).or(is(1)));
  }

  @Test
  public void shouldRemoveAllClients() {
	Client client = new Client(FIRSTNAME_2, LASTNAME_2, EMAIL_2);
	manager.addClient(client);
	manager.removeAllClients();
	int clientsCounter = manager.getAllClients().size();
	assertThat(clientsCounter, is(0));
  }
  
  @Test
  public void shouldUpdateAllFieldsClient() {
    Client client = new Client(FIRSTNAME_2, LASTNAME_2, EMAIL_2);
    manager.addClient(client);

    int clientsCounter = manager.getAllClients().size();
    Client retrievedClient = manager.getAllClients().get(clientsCounter - 1);
    retrievedClient.setFirstName(FIRSTNAME_3);
    retrievedClient.setLastName(LASTNAME_3);
    retrievedClient.setEmail(EMAIL_3);
    manager.updateClient(retrievedClient);

    retrievedClient = manager.getAllClients().get(clientsCounter - 1);
    assertEquals(retrievedClient.getFirstName(), FIRSTNAME_3);
    assertEquals(retrievedClient.getLastName(), LASTNAME_3);
    assertEquals(retrievedClient.getEmail(), EMAIL_3);

    Client temporaryClient = manager.getAllClients().get(clientsCounter - 1);
    manager.removeClient(temporaryClient);
  }

  @Test
  public void shouldRemoveOneAddedClient() {
    Client client = new Client(FIRSTNAME_1, LASTNAME_1, EMAIL_1);
    manager.addClient(client);

    int counterBeforeRemoveOneClient = manager.getAllClients().size();
    Client retrievedClient = manager.getAllClients().get(counterBeforeRemoveOneClient - 1);
    manager.removeClient(retrievedClient);

    int clientsCounter = manager.getAllClients().size();
    assertEquals(counterBeforeRemoveOneClient - 1, clientsCounter);
  }

  @Test
  public void shouldFindCorrectlyClientById() {
    Client client = new Client(FIRSTNAME_1, LASTNAME_1, EMAIL_1);
    manager.addClient(client);

    long id = client.getId();
    Client retrievedClient = manager.findClientById(id);
    assertEquals(retrievedClient, client);

    manager.removeClient(retrievedClient);
  }


  //Relations
  @Test
  public void shouldReturnCorrectlyNumberOfWebsitesInClient() {
    Client client = new Client();
    Website website1 = new Website();
    Website website2 = new Website();
    Website website3 = new Website();
    List<Website> websites = new ArrayList<Website>();
    websites.add(website1);
    websites.add(website2);
    websites.add(website3);
    client.setWebsites(websites);
    manager.addClient(client);

    Client retrievedClient = manager.getAllClients().get(manager.getAllClients().size() - 1);
    assertEquals(retrievedClient.getWebsites().size(), 3);
    manager.removeClient(retrievedClient);
  }

  @Test
  public void shouldReturnCorrectlyNumberOfWebsitesInCategory() {
    Category category = new Category();
    Website website1 = new Website();
    Website website2 = new Website();
    Website website3 = new Website();
    List<Website> websites = new ArrayList<Website>();
    websites.add(website1);
    websites.add(website2);
    websites.add(website3);
    category.setWebsites(websites);
    manager.addCategory(category);

    Category retrievedCategory = manager.getAllCategories().get(manager.getAllCategories().size() - 1);
    assertEquals(retrievedCategory.getWebsites().size(), 3);
    manager.removeCategory(retrievedCategory);
  }
}
