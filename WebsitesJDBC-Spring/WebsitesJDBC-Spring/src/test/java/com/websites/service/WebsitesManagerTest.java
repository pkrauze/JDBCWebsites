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
import com.websites.domain.Order;

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

  private static final String DOMAIN_2 = "test2.com";
  private static final int PR_2 = 60;
  private static final int DOMAIN_AUTHORITY_2 = 65;
  private static final Date CREATED_AT_2 = new Date(2017,2,2);

  private static final String DOMAIN_3 = "test3.com";
  private static final int PR_3 = 70;
  private static final int DOMAIN_AUTHORITY_3 = 75;
  private static final Date CREATED_AT_3 = new Date(2017,3,3);

  //Order examples
  private static final double PRICE_1 = 110.10;
  private static final String DESCRIPTION_1 = "The sample description for order #1";
  private static final String STATUS_1 = "The status for order #1";

  private static final double PRICE_2 = 220.20;
  private static final String DESCRIPTION_2 = "The sample description for order #2";
  private static final String STATUS_2 = "The status for order #2";

  private static final double PRICE_3 = 330.30;
  private static final String DESCRIPTION_3 = "The sample description for order #3";
  private static final String STATUS_3 = "The status for order #3";

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
    Website website = new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, CREATED_AT_1, new Client());
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
  public void shouldUpdateAllFieldsWebsite() {
    Website website = new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, CREATED_AT_1, new Client());
    manager.addWebsite(website);

    int websitesCounter = manager.getAllWebsites().size();
    Website retrievedWebsite = manager.getAllWebsites().get(websitesCounter - 1);
    retrievedWebsite.setDomain(DOMAIN_2);
    retrievedWebsite.setPageRank(PR_2);
    retrievedWebsite.setDomainAuthority(DOMAIN_AUTHORITY_2);
    retrievedWebsite.setCreatedAt(CREATED_AT_2);
    retrievedWebsite.setClient(new Client(FIRSTNAME_1, LASTNAME_1, EMAIL_1));
    manager.updateWebsite(retrievedWebsite);

    retrievedWebsite = manager.getAllWebsites().get(websitesCounter - 1);
    assertEquals(retrievedWebsite.getDomain(), DOMAIN_2);
    assertEquals(retrievedWebsite.getPageRank(), PR_2);
    assertEquals(retrievedWebsite.getDomainAuthority(), DOMAIN_AUTHORITY_2);
    assertEquals(retrievedWebsite.getCreatedAt(), CREATED_AT_2);
    assertEquals(retrievedWebsite.getClient().getFirstName(), FIRSTNAME_1);

    Website temporaryWebsite = manager.getAllWebsites().get(websitesCounter - 1);
    manager.removeWebsite(temporaryWebsite);
  }

  @Test
  public void shouldRemoveOneAddedWebsite() {
    Website website = new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, CREATED_AT_1, new Client());
    manager.addWebsite(website);

    int counterBeforeRemoveOneWebsite = manager.getAllWebsites().size();
    Website retrievedWebsite = manager.getAllWebsites().get(counterBeforeRemoveOneWebsite - 1);
    manager.removeWebsite(retrievedWebsite);

    int websitesCounter = manager.getAllWebsites().size();
    assertEquals(counterBeforeRemoveOneWebsite - 1, websitesCounter);
  }

  @Test
  public void shouldFindCorrectlyWebsiteById() {
    Website website = new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, CREATED_AT_1, new Client());
    manager.addWebsite(website);

    long id = website.getId();
    Website retrievedWebsite = manager.findWebsiteById(id);
    assertEquals(retrievedWebsite, website);

    manager.removeWebsite(retrievedWebsite);
  }

  @Test
  public void shouldFindCorrectlyWebsiteByDomain() {
    Website website = new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, CREATED_AT_1, new Client());
    manager.addWebsite(website);

    String domain = website.getDomain();
    Website retrievedWebsite = manager.findByDomain(domain);
    assertEquals(retrievedWebsite, website);

    manager.removeWebsite(retrievedWebsite);
  }

  //Orders
  @Test
  public void shouldAddOneOrder() {
    int counterBeforeAddOneOrder = manager.getAllOrders().size();
    Order order = new Order(PRICE_1, DESCRIPTION_1, STATUS_1, new Website(), new Client());
    manager.addOrder(order);

    int ordersCounter = manager.getAllOrders().size();
    assertEquals(counterBeforeAddOneOrder + 1, ordersCounter);
    Order temporaryOrder = manager.getAllOrders().get(ordersCounter - 1);

    manager.removeOrder(temporaryOrder);
  }

  @Test
  public void shouldGetAllOrders() {
    int ordersCounter = manager.getAllOrders().size();
    assertThat(ordersCounter, either(is(0)).or(is(1)));
  }

  @Test
  public void shouldUpdateAllFieldsOrder() {
    Order order = new Order(PRICE_2, DESCRIPTION_2, STATUS_2, new Website(), new Client());
    manager.addOrder(order);

    int ordersCounter = manager.getAllOrders().size();
    Order retrievedOrder = manager.getAllOrders().get(ordersCounter - 1);
    retrievedOrder.setPrice(PRICE_3);
    retrievedOrder.setDescription(DESCRIPTION_3);
    retrievedOrder.setStatus(STATUS_3);
    retrievedOrder.setWebsite(new Website(DOMAIN_1, PR_1, DOMAIN_AUTHORITY_1, CREATED_AT_1, new Client()));
    retrievedOrder.setClient(new Client(FIRSTNAME_1, LASTNAME_1, EMAIL_1));
    manager.updateOrder(retrievedOrder);

    retrievedOrder = manager.getAllOrders().get(ordersCounter - 1);
    assertEquals(retrievedOrder.getPrice(), PRICE_3, _PRECISION);
    assertEquals(retrievedOrder.getDescription(), DESCRIPTION_3);
    assertEquals(retrievedOrder.getStatus(), STATUS_3);
    assertEquals(retrievedOrder.getWebsite().getDomain(), DOMAIN_1);
    assertEquals(retrievedOrder.getClient().getEmail(), EMAIL_1);

    Order temporaryOrder = manager.getAllOrders().get(ordersCounter - 1);
    manager.removeOrder(temporaryOrder);
  }

  @Test
  public void shouldRemoveOneAddedOrder() {
    Order order = new Order(PRICE_1, DESCRIPTION_1, STATUS_1, new Website(), new Client());
    manager.addOrder(order);

    int counterBeforeRemoveOneOrder = manager.getAllOrders().size();
    Order retrievedOrder = manager.getAllOrders().get(counterBeforeRemoveOneOrder - 1);
    manager.removeOrder(retrievedOrder);

    int ordersCounter = manager.getAllOrders().size();
    assertEquals(counterBeforeRemoveOneOrder - 1, ordersCounter);
  }

  @Test
  public void shouldFindCorrectlyOrderById() {
    Order order = new Order(PRICE_1, DESCRIPTION_1, STATUS_1, new Website(), new Client());
    manager.addOrder(order);

    long id = order.getId();
    Order retrievedOrder = manager.findOrderById(id);
    assertEquals(retrievedOrder, order);

    manager.removeOrder(retrievedOrder);
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

  // @Test
  // public void shouldReturnCorrectlyNumberOfOrdersInClient() {
  //   Client client = new Client();
  //   Order order1 = new Order();
  //   Order order2 = new Order();
  //   Order order3 = new Order();
  //   List<Order> orders = new ArrayList<Order>();
  //   orders.add(order1);
  //   orders.add(order2);
  //   orders.add(order3);
  //   client.setOrders(orders);
  //   manager.addClient(client);

  //   Client retrievedClient = manager.getAllClients().get(manager.getAllClients().size() - 1);
  //   assertEquals(retrievedClient.getOrders().size(), 3);
  //   manager.removeClient(retrievedClient);
  // }

  // @Test
  // public void shouldReturnCorrectlyNumberOfOrdersInWebsite() {
  //   Website website = new Website();
  //   Order order1 = new Order();
  //   Order order2 = new Order();
  //   Order order3 = new Order();
  //   List<Order> orders = new ArrayList<Order>();
  //   orders.add(order1);
  //   orders.add(order2);
  //   orders.add(order3);
  //   website.setOrders(orders);
  //   manager.addWebsite(website);

  //   Website retrievedWebsite = manager.getAllWebsites().get(manager.getAllWebsites().size() - 1);
  //   assertEquals(retrievedWebsite.getOrders().size(), 3);
  //   manager.removeWebsite(retrievedWebsite);
  // }
}
