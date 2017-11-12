package main.java.com.example.jdbcwebsites.domain;

public class Website {
	private long id;
	
	private String domain;
	private String description;
	private int page_rank;
	private int page_authority;
	private int domain_authority;
	
	public Website() {
	}
	
	public Website(String domain, String description, int page_rank, int page_authority, int domain_authority) {
		super();
		this.domain = domain;
		this.description = description;
		this.page_rank = page_rank;
		this.page_authority = page_authority;
		this.domain_authority = domain_authority;
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
	public int getPage_rank() {
		return page_rank;
	}
	public void setPage_rank(int page_rank) {
		this.page_rank = page_rank;
	}
	public int getPage_authority() {
		return page_authority;
	}
	public void setPage_authority(int page_authority) {
		this.page_authority = page_authority;
	}
	public int getDomain_authority() {
		return domain_authority;
	}
	public void setDomain_authority(int domain_authority) {
		this.domain_authority = domain_authority;
	}
	
	

}
