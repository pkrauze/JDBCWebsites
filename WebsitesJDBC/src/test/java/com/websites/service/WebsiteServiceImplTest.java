package com.websites.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.websites.domain.Website;
import com.websites.service.WebsiteServiceImpl;

public class WebsiteServiceImplTest {
  private static WebsiteServiceImpl websiteService = new WebsiteServiceImpl();
  private static List<Website> websites = new ArrayList<Website>();
  private static Website exampleWeb = new Website("example.com", "The Example Website", 6, 65, 42);
  private static Website testWeb = new Website("test.com", "The Test Website", 3, 35, 12);
  private static Website checkWeb = new Website("checkweb.com", "The Check Website", 1, 73, 82);

  @BeforeClass
  public static void checkOpenConnection() {
    assertNotNull(websiteService.openConnection());
  }

  @AfterClass
  public static void checkCloseConnection() {
    assertNotNull(websiteService.closeConnection());
  }

  @Before
  public void checkAddAllWebsite() {
    List<Website> websitesInput = new ArrayList<Website>();
    websitesInput.add(exampleWeb);
    websitesInput.add(testWeb);
    int size = websiteService.addAllWebsitesFromList(websitesInput);
    assertEquals(size, 2);
  }

  @After
  public void checkRemoveAllWebsites() {
    websiteService.removeAllWebsites();
    assertTrue(websiteService.getAllWebsites().size() == 0);
  }

  @Test
  public void checkCreateTable() {
    assertNotNull(websiteService.createTable());
  }

  @Test
  public void checkCreateStatements() {
    assertNotNull(websiteService.createStatements());
  }

  @Test
  public void checkGetConnection() {
    assertNotNull(websiteService.getConnection());
  }

  @Test
  public void checkAddWebsite() {
    websiteService.addWebsite(checkWeb);
    assertEquals(3, websiteService.getAllWebsites().size());
  }

  @Test
  public void checkGetAllWebsites() {
    assertEquals(2, websiteService.getAllWebsites().size());
  }

  @Test
  public void checkUpdateWebsite() {
    String newDomain = "testing.com";
    String newDescription = "The new description";
    int newPageRank = 3;
    int newPageAuthority = 12;
    int newDomainAuthority = 60;
    websites = websiteService.getAllWebsites();
    websiteService.updateWebsiteDomain(websites.get(websites.size() - 1), newDomain);
    websiteService.updateWebsiteDescription(websites.get(websites.size() - 1), newDescription);
    websiteService.updateWebsitePageRank(websites.get(websites.size() - 1), newPageRank);
    websiteService.updateWebsitePageAuthority(websites.get(websites.size() - 1), newPageAuthority);
    websiteService.updateWebsiteDomainAuthority(websites.get(websites.size() - 1), newDomainAuthority);
    websites = websiteService.getAllWebsites();
    assertEquals(newDomain, websites.get(websites.size() - 1).getDomain());
    assertEquals(newDescription, websites.get(websites.size() - 1).getDescription());
    assertEquals(newPageRank, websites.get(websites.size() - 1).getPageRank());
    assertEquals(newPageAuthority, websites.get(websites.size() - 1).getPageAuthority());
    assertEquals(newDomainAuthority, websites.get(websites.size() - 1).getDomainAuthority());
  }

  @Test
  public void checkUpdateWebsiteDomain() {
    String newDomain = "testingsecondlololo.com";
    websites = websiteService.getAllWebsites();
    websiteService.updateWebsiteDomain(websites.get(websites.size() - 1), newDomain);
    websites = websiteService.getAllWebsites();
    assertEquals(newDomain, websites.get(websites.size() - 1).getDomain());
  }

  @Test
  public void checkUpdateWebsiteDescription() {
    String newDescription = "The new description for website";
    websites = websiteService.getAllWebsites();
    websiteService.updateWebsiteDescription(websites.get(websites.size() - 1), newDescription);
    websites = websiteService.getAllWebsites();
    assertEquals(newDescription, websites.get(websites.size() - 1).getDescription());
  }

  @Test
  public void checkUpdateWebsitePageRank() {
    int newPageRank = 2;
    websites = websiteService.getAllWebsites();
    websiteService.updateWebsitePageRank(websites.get(websites.size() - 1), newPageRank);
    websites = websiteService.getAllWebsites();
    assertEquals(newPageRank, websites.get(websites.size() - 1).getPageRank());
  }

  @Test
  public void checkUpdateWebsitePageAuthority() {
    int newPageAuthority = 32;
    websites = websiteService.getAllWebsites();
    websiteService.updateWebsitePageAuthority(websites.get(websites.size() - 1), newPageAuthority);
    websites = websiteService.getAllWebsites();
    assertEquals(newPageAuthority, websites.get(websites.size() - 1).getPageAuthority());
  }

  @Test
  public void checkUpdateWebsiteDomainAuthority() {
    int newDomainAuthority = 42;
    websites = websiteService.getAllWebsites();
    websiteService.updateWebsiteDomainAuthority(websites.get(websites.size() - 1), newDomainAuthority);
    websites = websiteService.getAllWebsites();
    assertEquals(newDomainAuthority, websites.get(websites.size() - 1).getDomainAuthority());
  }

  @Test
  public void checkRemoveWebsiteById() {
    websites = websiteService.getAllWebsites();
    Website tmp = websites.get(websites.size() - 1);

    websiteService.removeWebsiteById(websites.get(websites.size() - 1).getId());
    websites = websiteService.getAllWebsites();
    assertEquals(1, websites.size());
    websiteService.addWebsite(tmp);
  }


  @Test
  public void checkRemoveWebsiteByDomain() {
    websites = websiteService.getAllWebsites();
    websiteService.updateWebsiteDomain(websites.get(0), "test2.com");

    Website tmp = websites.get(0);
    websiteService.removeWebsiteByDomain(websites.get(0).getDomain());
    websites = websiteService.getAllWebsites();
    assertEquals(1, websites.size());
    websiteService.addWebsite(tmp);
  }


  @Test
  public void checkFindById() {
    websites = websiteService.getAllWebsites();
    Website tmp = websites.get(0);
    Website websiteRetrieved = websiteService.findById(tmp.getId());
    assertEquals(tmp.getDomain(), websiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), websiteRetrieved.getDescription());
    assertEquals(tmp.getPageRank(), websiteRetrieved.getPageRank());
    assertEquals(tmp.getPageAuthority(), websiteRetrieved.getPageAuthority());
    assertEquals(tmp.getDomainAuthority(), websiteRetrieved.getDomainAuthority());
  }

  @Test
  public void checkFindByDomain() {
    websites = websiteService.getAllWebsites();
    Website tmp = websites.get(0);
    Website websiteRetrieved = websiteService.findByDomain(tmp.getDomain());
    assertEquals(tmp.getDomain(), websiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), websiteRetrieved.getDescription());
    assertEquals(tmp.getPageRank(), websiteRetrieved.getPageRank());
    assertEquals(tmp.getPageAuthority(), websiteRetrieved.getPageAuthority());
    assertEquals(tmp.getDomainAuthority(), websiteRetrieved.getDomainAuthority());
  }

  @Test
  public void checkFindByDescription() {
    websites = websiteService.getAllWebsites();
    Website tmp = websites.get(0);
    Website websiteRetrieved = websiteService.findByDescription(tmp.getDescription());
    assertEquals(tmp.getDomain(), websiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), websiteRetrieved.getDescription());
    assertEquals(tmp.getPageRank(), websiteRetrieved.getPageRank());
    assertEquals(tmp.getPageAuthority(), websiteRetrieved.getPageAuthority());
    assertEquals(tmp.getDomainAuthority(), websiteRetrieved.getDomainAuthority());
  }

  @Test
  public void checkFindByPageRank() {
    websites = websiteService.getAllWebsites();
    Website tmp = websites.get(0);
    Website websiteRetrieved = websiteService.findByPageRank(tmp.getPageRank());
    assertEquals(tmp.getDomain(), websiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), websiteRetrieved.getDescription());
    assertEquals(tmp.getPageRank(), websiteRetrieved.getPageRank());
    assertEquals(tmp.getPageAuthority(), websiteRetrieved.getPageAuthority());
    assertEquals(tmp.getDomainAuthority(), websiteRetrieved.getDomainAuthority());
  }

  @Test
  public void checkFindByPageAuthority() {
    websites = websiteService.getAllWebsites();
    Website tmp = websites.get(0);
    Website websiteRetrieved = websiteService.findByPageAuthority(tmp.getPageAuthority());
    assertEquals(tmp.getDomain(), websiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), websiteRetrieved.getDescription());
    assertEquals(tmp.getPageRank(), websiteRetrieved.getPageRank());
    assertEquals(tmp.getPageAuthority(), websiteRetrieved.getPageAuthority());
    assertEquals(tmp.getDomainAuthority(), websiteRetrieved.getDomainAuthority());
  }

  @Test
  public void checkFindByDomainAuthority() {
    websites = websiteService.getAllWebsites();
    Website tmp = websites.get(0);
    Website websiteRetrieved = websiteService.findByDomainAuthority(tmp.getDomainAuthority());
    assertEquals(tmp.getDomain(), websiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), websiteRetrieved.getDescription());
    assertEquals(tmp.getPageRank(), websiteRetrieved.getPageRank());
    assertEquals(tmp.getPageAuthority(), websiteRetrieved.getPageAuthority());
    assertEquals(tmp.getDomainAuthority(), websiteRetrieved.getDomainAuthority());
  }
}
