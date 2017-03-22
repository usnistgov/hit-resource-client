package gov.nist.hit.resources.deploy.client;

import gov.nist.hit.resources.deploy.config.ApiConfig;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class ResourceClient {

	private String authorization;
	private ApiConfig config;
	private String accessPoint;
	private RestTemplate tmpl;
	
	public ResourceClient(String host, String username, String password) {
		super();
		
    	this.authorization = token(username,password);
    
        SSLConnectionSocketFactory socketFactory;
		try {
			socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build());
		       HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
		       HttpComponentsClientHttpRequestFactory fct = new HttpComponentsClientHttpRequestFactory(httpClient);
		        this.tmpl = new RestTemplate(fct);
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 
	}
	public ResourceClient(String host, String authorization,ApiConfig config) {
		super();
		this.config = config;
    	this.accessPoint = host + config.getContext() + config.getMainMapping();
    	this.authorization =authorization;
    	//this.tmpl = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    	 SSLConnectionSocketFactory socketFactory;
 		try {
 			socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build());
 		       HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();

 		      HttpComponentsClientHttpRequestFactory fct = new HttpComponentsClientHttpRequestFactory(httpClient);
		        this.tmpl = new RestTemplate(fct);
 		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	}
	
	public ResourceClient(String host, String username, String password, ApiConfig config) {
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
