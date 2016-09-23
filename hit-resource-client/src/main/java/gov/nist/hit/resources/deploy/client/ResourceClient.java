package gov.nist.hit.resources.deploy.client;

import gov.nist.hit.resources.deploy.config.ApiConfig;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Client {

	private String authorization;
	private ApiConfig config;
	private String accessPoint;
	private RestTemplate tmpl;
	
	public Client(String host, String username, String password) {
		super();
    	this.authorization = token(username,password);
    	this.tmpl = new RestTemplate();
	}
	
	public Client(String host, String username, String password, ApiConfig config) {
		this(host,username,password);
    	this.config = config;
    	this.accessPoint = host + config.getContext() + config.getMainMapping();
	}

	public ResponseEntity<String> addOrUpdateTestStep(RequestModel m) {
    	String URL = URL(config.getAddOrUpdateTestStep());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}

	public ResponseEntity<String> addOrUpdateTestPlan(RequestModel m) {
    	String URL = URL(config.getAddOrUpdateTestPlan());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}
	
	public ResponseEntity<String> addOrUpdateCFTestCase(RequestModel m) {
    	String URL = URL(config.getAddOrUpdateCFTestCase());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}
	
	public ResponseEntity<String> addOrUpdateProfile(RequestModel m) {  	
    	String URL = URL(config.getAddOrUpdateProfile());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}
	
	public ResponseEntity<String> addOrUpdateConstraints(RequestModel m) {
		String URL = URL(config.getAddOrUpdateConstraints());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}

	public ResponseEntity<String> addOrUpdateValueSet(RequestModel m) {
    	String URL = URL(config.getAddOrUpdateValueSet());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}

	public ResponseEntity<String> addTestCaseToGroup(RequestModel m) {
		String URL = URL(config.getAddTestCaseG());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}
	
	public ResponseEntity<String> addTestCaseToPlan(RequestModel m) {
		String URL = URL(config.getAddTestCaseP());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}
	
	public ResponseEntity<String> addTestCaseGroupToGroup(RequestModel m) {
		String URL = URL(config.getAddTestGroupG());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}
	
	public ResponseEntity<String> addTestCaseGroupToPlan(RequestModel m) {
    	String URL = URL(config.getAddTestGroupP());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}
	
	public ResponseEntity<String> updateTestCase(RequestModel m) {
    	String URL = URL(config.getUpdateTestCase());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}
	
	public ResponseEntity<String> updateTestCaseGroup(RequestModel m) {
    	String URL = URL(config.getUpdateTestGroup());
    	ResponseEntity<String> response = tmpl.exchange(URL, HttpMethod.POST, this.createHttpEntity(m), String.class);
    	return response;
	}
	
	public HttpEntity<RequestModel> createHttpEntity(RequestModel m){
		HttpHeaders headers = new HttpHeaders();
    	headers.add("Authorization", "Basic " + authorization);
    	return new HttpEntity<>(m,headers);
	}
	
	public String URL(String resource){
		StringBuilder strB = new StringBuilder("");
		strB.append(this.accessPoint).append(resource);
		return strB.toString();
	}
	
	public String token(String username, String password){
		String plainCreds = username+":"+password;
    	byte[] plainCredsBytes = plainCreds.getBytes();
    	byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
    	return new String(base64CredsBytes);
	}

}
