package com.websites.service;

import java.util.List;

import com.websites.domain.Website;

public interface WebsiteService {
	public int addWebsite(Website website);

	public int addAllWebsitesFromList(List<Website> websites);

	public List<Website> getAllWebsites();

	public int updateWebsite(Website website, String domain, String description, int pageRank, int pageAuthority, int domainAuthority);

	public int updateWebsiteDomain(Website website, String domain);

	public int updateWebsiteDescription(Website website, String description);

	public int updateWebsitePageRank(Website website, int pageRank);

	public int updateWebsitePageAuthority(Website website, int pageAuthority);

	public int updateWebsiteDomainAuthority(Website website, int domainAuthority);

	public int removeAllWebsites();

	public int removeWebsiteById(long id);

	public int removeWebsiteByDomain(String domain);

	public Website findById(long id);

	public Website findByDomain(String domain);

	public Website findByDescription(String description);

	public Website findByPageRank(int pageRank);

	public Website findByPageAuthority(int pageAuthority);

	public Website findByDomainAuthority(int domainAuthority);
}
