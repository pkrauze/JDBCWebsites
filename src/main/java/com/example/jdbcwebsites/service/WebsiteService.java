package main.java.com.example.jdbcwebsites.service;

import java.util.List;
import main.java.com.example.jdbcwebsites.domain.Website;

public interface WebsiteService {
	public int addWebsite(Website website);
	
	public int addAllWebsitesFromList(List<Website> websites);
	
	public List<Website> getAllWebsites();
	
	public int updateWebsite(Website website, String domain, String description, int page_rank, int page_authority, int domain_authority);
	
	public int updateWebsiteDomain(Website website, String domain);
	
	public int updateWebsiteDescription(Website website, String description);
	
	public int updateWebsitePageRank(Website website, int page_rank);
	
	public int updateWebsitePageAuthority(Website website, int page_authority);
	
	public int updateWebsiteDomainAuthority(Website website, int domain_authority);
	
	public int removeAllWebsites();
	
	public int removeWebsiteById(long id);
	
	public int removeWebsiteByDomain(String domain);
	
	public Website findById(long id);
	
	public Website findByDomain(String domain);
	
	public Website findbyDescription(String description);
	
	public Website findbyPageRank(int page_rank);
	
	public Website findbyPageAuthority(int page_authority);
	
	public Website findbyDomainAuthority(int domain_authority);
}
