package gov.nist.hit.resources.deploy.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import gov.nist.hit.resources.deploy.exception.InsupportedApiMethod;
import gov.nist.hit.resources.deploy.exception.UserNotFoundException;
import gov.nist.hit.resources.deploy.model.Payload;
import gov.nist.hit.resources.deploy.model.RegistredGrant;
import gov.nist.hit.resources.deploy.model.ResourceType;
import gov.nist.hit.resources.deploy.model.Scope;

public interface Client {

	public ResponseEntity<String> update(Payload model, ResourceType entity) throws InsupportedApiMethod;
	public ResponseEntity<String> addOrUpdate(Payload model, ResourceType entity, Scope scope) throws InsupportedApiMethod;
	public ResponseEntity<String> add(Payload model, ResourceType entity, ResourceType container) throws InsupportedApiMethod;
	public ResponseEntity<String> delete(Long id, ResourceType entity) throws InsupportedApiMethod;
	public Set<RegistredGrant> validCredentials() throws UserNotFoundException;
	
}
