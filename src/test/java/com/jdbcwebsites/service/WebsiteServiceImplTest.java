package com.website.service;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.website.domain.Website;
import com.website.service.WebsiteServiceImpl;

public class WebsiteServiceImplTest {
  private static WebsiteServiceImpl WebsiteService = new WebsiteServiceImpl();
  private static List<Website> Websites = new ArrayList<Website>();
  private static Website example_web = new Website("example.com", "The Example Website", 6, 65, 42);
  private static Website test_web = new Website("test.com", "The Test Website", 3, 35, 12);
  private static Website check_web = new Website("checkweb.com", "The Check Website", 1, 73, 82);

  @BeforeClass
  public static void checkOpenConnection() {
    assertNotNull(WebsiteService.openConnection());
  }

  // hidden
  @AfterClass
  public static void checkCloseConnection() {
    assertNotNull(WebsiteService.closeConnection());
  }

  // @BeforeClass and static method
  @Before
  public void checkAddAllWebsite() {
    List<Website> WebsitesInput = new ArrayList<Website>();
    WebsitesInput.add(example_web);
    WebsitesInput.add(test_web);
    int size = WebsiteService.addAllWebsitesFromList(WebsitesInput);
    assertThat(size, either(is(0)).or(is(2)));
  }

  // @Ignore
  @After
  public void checkRemoveAllWebsites() {
    WebsiteService.removeAllWebsites();
    assertTrue(WebsiteService.getAllWebsites().size() == 0);
  }

  @Test
  public void checkCreateTable() {
    assertNotNull(WebsiteService.createTable());
  }

  @Test
  public void checkCreateStatements() {
    assertNotNull(WebsiteService.createStatements());
  }

  @Test
  public void checkGetConnection() {
    assertNotNull(WebsiteService.getConnection());
  }

  @Test
  public void checkAddWebsite() {
    WebsiteService.addWebsite(check_web);
    assertEquals(3, WebsiteService.getAllWebsites().size());
  }

  @Test
  public void checkGetAllWebsites() {
    assertEquals(2, WebsiteService.getAllWebsites().size());
  }

  @Test
  public void checkUpdateWebsite() {
    String newDomain = "testing.com";
    String newDescription = "The new description";
    int newPageRank = 3;
    int newPageAuthority = 12;
    int newDomainAuthority = 60;
    Websites = WebsiteService.getAllWebsites();
    WebsiteService.updateWebsiteDomain(Websites.get(Websites.size() - 1), newDomain);
    WebsiteService.updateWebsiteDescription(Websites.get(Websites.size() - 1), newDescription);
    WebsiteService.updateWebsitePageRank(Websites.get(Websites.size() - 1), newPageRank);
    WebsiteService.updateWebsitePageAuthority(Websites.get(Websites.size() - 1), newPageAuthority);
    WebsiteService.updateWebsiteDomainAuthority(Websites.get(Websites.size() - 1), newDomainAuthority);
    Websites = WebsiteService.getAllWebsites();
    assertEquals(newDomain, Websites.get(Websites.size() - 1).getDomain());
    assertEquals(newDescription, Websites.get(Websites.size() - 1).getDescription());
    assertEquals(newPageRank, Websites.get(Websites.size() - 1).getPage_rank());
    assertEquals(newPageAuthority, Websites.get(Websites.size() - 1).getPage_authority());
    assertEquals(newDomainAuthority, Websites.get(Websites.size() - 1).getDomain_authority());
  }

  @Test
  public void checkUpdateWebsiteDomain() {
    String newDomain = "testingsecond.com";
    Websites = WebsiteService.getAllWebsites();
    WebsiteService.updateWebsiteDomain(Websites.get(0), newDomain);
    Websites = WebsiteService.getAllWebsites();
    assertEquals(newDomain, Websites.get(0).getDomain());
  }

  @Test
  public void checkUpdateWebsiteDescription() {
    String newDescription = "The new description for website";
    Websites = WebsiteService.getAllWebsites();
    WebsiteService.updateWebsiteDescription(Websites.get(0), newDescription);
    Websites = WebsiteService.getAllWebsites();
    assertEquals(newDescription, Websites.get(0).getDescription());
  }

  @Test
  public void checkUpdateWebsitePageRank() {
    int newPageRank = 2;
    Websites = WebsiteService.getAllWebsites();
    WebsiteService.updateWebsitePageRank(Websites.get(0), newPageRank);
    Websites = WebsiteService.getAllWebsites();
    assertEquals(newPageRank, Websites.get(0).getPage_rank());
  }
  
  @Test
  public void checkUpdateWebsitePageAuthority() {
    int newPageAuthority = 32;
    Websites = WebsiteService.getAllWebsites();
    WebsiteService.updateWebsitePageAuthority(Websites.get(0), newPageAuthority);
    Websites = WebsiteService.getAllWebsites();
    assertEquals(newPageAuthority, Websites.get(0).getPage_authority());
  }
  
  @Test
  public void checkUpdateWebsiteDomainAuthority() {
    int newDomainAuthority = 42;
    Websites = WebsiteService.getAllWebsites();
    WebsiteService.updateWebsiteDomainAuthority(Websites.get(0), newDomainAuthority);
    Websites = WebsiteService.getAllWebsites();
    assertEquals(newDomainAuthority, Websites.get(0).getDomain_authority());
  }

  @Test
  public void checkRemoveWebsiteById() {
    Websites = WebsiteService.getAllWebsites();
    Website tmp = Websites.get(0);
    WebsiteService.removeWebsiteById(Websites.get(0).getId());
    Websites = WebsiteService.getAllWebsites();
    assertEquals(1, Websites.size());
    WebsiteService.addWebsite(tmp);
  }

  @Test
  public void checkRemoveWebsiteByDomain() {
    Websites = WebsiteService.getAllWebsites();
    Website tmp = Websites.get(0);
    WebsiteService.removeWebsiteByDomain(Websites.get(0).getDomain());
    Websites = WebsiteService.getAllWebsites();
    assertEquals(1, Websites.size());
    WebsiteService.addWebsite(tmp);
  }

  @Test
  public void checkFindById() {
    Websites = WebsiteService.getAllWebsites();
    Website tmp = Websites.get(0);
    Website WebsiteRetrieved = WebsiteService.findById(tmp.getId());
    assertEquals(tmp.getDomain(), WebsiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), WebsiteRetrieved.getDescription());
    assertEquals(tmp.getPage_rank(), WebsiteRetrieved.getPage_rank());
    assertEquals(tmp.getPage_authority(), WebsiteRetrieved.getPage_authority());
    assertEquals(tmp.getDomain_authority(), WebsiteRetrieved.getDomain_authority());
  }

  @Test
  public void checkFindByDomain() {
    Websites = WebsiteService.getAllWebsites();
    Website tmp = Websites.get(0);
    Website WebsiteRetrieved = WebsiteService.findByDomain(tmp.getDomain());
    assertEquals(tmp.getDomain(), WebsiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), WebsiteRetrieved.getDescription());
    assertEquals(tmp.getPage_rank(), WebsiteRetrieved.getPage_rank());
    assertEquals(tmp.getPage_authority(), WebsiteRetrieved.getPage_authority());
    assertEquals(tmp.getDomain_authority(), WebsiteRetrieved.getDomain_authority());
  }
  
  @Test
  public void checkFindByDescription() {
    Websites = WebsiteService.getAllWebsites();
    Website tmp = Websites.get(0);
    Website WebsiteRetrieved = WebsiteService.findByDescription(tmp.getDescription());
    assertEquals(tmp.getDomain(), WebsiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), WebsiteRetrieved.getDescription());
    assertEquals(tmp.getPage_rank(), WebsiteRetrieved.getPage_rank());
    assertEquals(tmp.getPage_authority(), WebsiteRetrieved.getPage_authority());
    assertEquals(tmp.getDomain_authority(), WebsiteRetrieved.getDomain_authority());
  }
  
  @Test
  public void checkFindByPageRank() {
    Websites = WebsiteService.getAllWebsites();
    Website tmp = Websites.get(0);
    Website WebsiteRetrieved = WebsiteService.findByPageRank(tmp.getPage_rank());
    assertEquals(tmp.getDomain(), WebsiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), WebsiteRetrieved.getDescription());
    assertEquals(tmp.getPage_rank(), WebsiteRetrieved.getPage_rank());
    assertEquals(tmp.getPage_authority(), WebsiteRetrieved.getPage_authority());
    assertEquals(tmp.getDomain_authority(), WebsiteRetrieved.getDomain_authority());
  }
  
  @Test
  public void checkFindByPageAuthority() {
    Websites = WebsiteService.getAllWebsites();
    Website tmp = Websites.get(0);
    Website WebsiteRetrieved = WebsiteService.findByPageAuthority(tmp.getPage_authority());
    assertEquals(tmp.getDomain(), WebsiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), WebsiteRetrieved.getDescription());
    assertEquals(tmp.getPage_rank(), WebsiteRetrieved.getPage_rank());
    assertEquals(tmp.getPage_authority(), WebsiteRetrieved.getPage_authority());
    assertEquals(tmp.getDomain_authority(), WebsiteRetrieved.getDomain_authority());
  }
  
  @Test
  public void checkFindByDomainAuthority() {
    Websites = WebsiteService.getAllWebsites();
    Website tmp = Websites.get(0);
    Website WebsiteRetrieved = WebsiteService.findByDomainAuthority(tmp.getDomain_authority());
    assertEquals(tmp.getDomain(), WebsiteRetrieved.getDomain());
    assertEquals(tmp.getDescription(), WebsiteRetrieved.getDescription());
    assertEquals(tmp.getPage_rank(), WebsiteRetrieved.getPage_rank());
    assertEquals(tmp.getPage_authority(), WebsiteRetrieved.getPage_authority());
    assertEquals(tmp.getDomain_authority(), WebsiteRetrieved.getDomain_authority());
  }
}