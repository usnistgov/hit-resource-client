package gov.nist.hit.resources.deploy.client;

public class RequestModel {
	
	private String url;
	private Long id;
	private String zip;
	
	public RequestModel(String url, Long id) {
		super();
		this.url = url;
		this.id = id;
		this.zip = "";
	}
	
	public RequestModel(String url) {
		super();
		this.url = url;
		this.id = -1L;
		this.zip = "";
	}
	
	public RequestModel() {
		super();
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
}
