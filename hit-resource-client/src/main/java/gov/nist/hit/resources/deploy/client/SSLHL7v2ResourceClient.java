package gov.nist.hit.resources.deploy.client;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import gov.nist.hist.resources.deploy.ssl.SSLRestTemplateFactory;
import gov.nist.hit.resources.deploy.api.ApiConfig;
import gov.nist.hit.resources.deploy.api.Client;
import gov.nist.hit.resources.deploy.config.HL7v2ResourcesApiConfig;
import gov.nist.hit.resources.deploy.exception.InsupportedApiMethod;
import gov.nist.hit.resources.deploy.exception.UserNotFoundException;
import gov.nist.hit.resources.deploy.model.Action;
import gov.nist.hit.resources.deploy.model.Payload;
import gov.nist.hit.resources.deploy.model.RegistredGrant;
import gov.nist.hit.resources.deploy.model.ResourceType;
import gov.nist.hit.resources.deploy.model.Scope;

@Service
public class SSLHL7v2ResourceClient implements Client {

	private RestTemplate wire;
	private ApiConfig config;
	private String token;
	private String host;
	
	public SSLHL7v2ResourceClient(String host, String token) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException{
		this.token = token;
		this.host = host;
		config = new HL7v2ResourcesApiConfig();
		wire = SSLRestTemplateFactory.createSSLRestTemplate();
	}
	
	public SSLHL7v2ResourceClient(String host, String username, String password) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException{
		this(host,Utils.tokenize(username,password));
	}
	
	private String createURL(String mapping){
		return host + mapping; 
	}

	public ResponseEntity<String> reach(HttpMethod method, Payload model, String endPoint, Scope scope) {
		HttpHeaders headers = new HttpHeaders();
	   
	    headers.add("Authorization", "Basic " + token);
	    HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = null;
	    
	    if(model != null){
	    	requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(model.asMultiPartForm(scope), headers);
	    	headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    }
	    else {
	    	requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(headers);
	    }

	    ResponseEntity<String> response = wire.exchange(endPoint, method, requestEntity, String.class);
	    return response;
	}
	
	@Override
	public Set<RegistredGrant> validCredentials() throws UserNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + token);
		HttpEntity<String> entity = new HttpEntity<String>("",headers);
		
		try {
			ResponseEntity<String> response = wire.exchange(this.createURL(config.login()), HttpMethod.GET,  entity, String.class);
	    	if(response.getStatusCode() == HttpStatus.OK){
	    		
	    		JSONObject obj = new JSONObject(response.getBody());
	    		JSONArray jsAuth = obj.getJSONArray("authorities");
	    		
	    		HashSet<RegistredGrant> authorities = new HashSet<>();
	    		for(int i = 0; i < jsAuth.length(); i++){
	    			authorities.add(RegistredGrant.fromString(jsAuth.getJSONObject(i).getString("authority")));
	    		}
	    		return authorities;
	    	}
	    	else 
	    		throw new Exception();
		}
    	catch(Exception e){
    		throw new UserNotFoundException();
    	}
	}

	@Override
	public ResponseEntity<String> update(Payload model, ResourceType entity) throws InsupportedApiMethod {
		String mapping = config.urlFor(Action.UPDATE, entity, null);
		return this.reach(HttpMethod.POST, model, this.createURL(mapping), null);
	}

	@Override
	public ResponseEntity<String> addOrUpdate(Payload model, ResourceType entity, Scope scope) throws InsupportedApiMethod {
		String mapping = config.urlFor(Action.ADD_OR_UPDATE, entity, null);
		return this.reach(HttpMethod.POST, model, this.createURL(mapping), scope);
	}

	@Override
	public ResponseEntity<String> add(Payload model, ResourceType entity, ResourceType container) throws InsupportedApiMethod {
		String mapping = config.urlFor(Action.ADD, entity, container);
		return this.reach(HttpMethod.POST, model, this.createURL(mapping), null);
	}

	@Override
	public ResponseEntity<String> delete(Long id, ResourceType entity) throws InsupportedApiMethod {
		String mapping = config.urlFor(Action.DELETE, entity, null) + id;
		return this.reach(HttpMethod.DELETE, null, this.createURL(mapping), null);
	}

	public RestTemplate getWire() {
		return wire;
	}

	public void setWire(RestTemplate wire) {
		this.wire = wire;
	}

	public ApiConfig getConfig() {
		return config;
	}

	public void setConfig(ApiConfig config) {
		this.config = config;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}
