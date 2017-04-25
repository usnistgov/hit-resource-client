package gov.nist.hit.resources.deploy.client;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import gov.nist.hit.resources.deploy.exception.InsupportedApiMethod;
import gov.nist.hit.resources.deploy.model.Payload;
import gov.nist.hit.resources.deploy.model.ResourceType;



public class Main {

	public static void main(String[] args) throws InsupportedApiMethod, IOException {
		try {
			SSLHL7v2ResourceClient client = new SSLHL7v2ResourceClient("http://localhost:8080/iztool","");
//			Payload pl = new Payload(Main.class.getResourceAsStream("/Contextbased.zip"));
//			client.addOrUpdate(pl, ResourceType.TEST_PLAN);
			client.delete(193745134L, ResourceType.TEST_PLAN);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
