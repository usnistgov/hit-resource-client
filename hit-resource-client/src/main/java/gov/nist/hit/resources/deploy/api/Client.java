package gov.nist.hit.resources.deploy.api;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import gov.nist.hit.resources.deploy.exception.InsupportedApiMethod;
import gov.nist.hit.resources.deploy.model.Payload;
import gov.nist.hit.resources.deploy.model.ResourceType;

public interface Client {

	public ResponseEntity<String> reach(HttpMethod method, Payload model, String endPoint);
	public ResponseEntity<String> update(Payload model, ResourceType entity) throws InsupportedApiMethod;
	public ResponseEntity<String> addOrUpdate(Payload model, ResourceType entity) throws InsupportedApiMethod;
	public ResponseEntity<String> add(Payload model, ResourceType entity, ResourceType container) throws InsupportedApiMethod;
	public ResponseEntity<String> delete(Long id, ResourceType entity) throws InsupportedApiMethod;
	public boolean validCredentials();
	
}
