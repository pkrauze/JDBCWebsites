package com.websites.domain;

public class Website {
	private long id;

	private static String domain;
	private static String description;
	private static int pageRank;
	private static int pageAuthority;
	private static int domainAuthority;

	public Website() {
	}

	public Website(String domain, String description, int pageRank, int pageAuthority, int domainAuthority) {
		super();
		this.domain = domain;
		this.description = description;
		this.pageRank = pageRank;
		this.pageAuthority = pageAuthority;
		this.domainAuthority = domainAuthority;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPageRank() {
		return pageRank;
	}
	public void setPageRank(int pageRank) {
		this.pageRank = pageRank;
	}
	public int getPageAuthority() {
		return pageAuthority;
	}
	public void setPageAuthority(int pageAuthority) {
		this.pageAuthority = pageAuthority;
	}
	public int getDomainAuthority() {
		return domainAuthority;
	}
	public void setDomainAuthority(int domainAuthority) {
		this.domainAuthority = domainAuthority;
	}
}
