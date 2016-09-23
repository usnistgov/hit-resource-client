package gov.nist.hit.resources.deploy.client;

public class RequestModel {
	
	private String url;
	private Long id;
	
	public RequestModel(String url, Long id) {
		super();
		this.url = url;
		this.id = id;
	}
	
	public RequestModel(String url) {
		super();
		this.url = url;
		this.id = -1L;
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
	
	
}
