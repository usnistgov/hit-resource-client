package gov.nist.hit.resources.deploy.factory;

import gov.nist.hit.resources.deploy.client.Client;
import gov.nist.hit.resources.deploy.config.ApiConfig;

public class ResourceClientFactory {
	
	public static Client createResourceClientWithDefault(String host, String username, String password){
		ApiConfig config = new ApiConfig();
    	config.setContext("/iztool");
    	config.setMainMapping("/api/editResources");
    	config.setAddOrUpdateTestStep("/cb/addOrUpdate/testStep");
    	config.setAddOrUpdateTestPlan("/cb/addOrUpdate/testPlan");
    	config.setAddOrUpdateCFTestCase("/cf/addOrUpdate/testCase");
    	config.setAddOrUpdateProfile("/global/addOrUpdate/profile");
    	config.setAddOrUpdateConstraints("/global/addOrUpdate/constraints");
    	config.setAddOrUpdateValueSet("/global/addOrUpdate/valueSet");
    	config.setAddTestCaseG("/cb/add/testCase/to/testCaseGroup");
    	config.setAddTestGroupP("/cb/add/testCaseGroup/to/testPlan");
    	config.setAddTestCaseP("/cb/add/testCase/to/testPlan");
    	config.setAddTestGroupG("/cb/add/testCaseGroup/to/testCaseGroup");
    	config.setUpdateTestCase("/cb/update/testCase");
    	config.setUpdateTestGroup("/cb/update/testCaseGroup");
    	
    	return new Client(host,username,password,config);
	}
	
	public static Client createResourceClient(String host, String username, String password, ApiConfig config){
    	return new Client(host,username,password,config);
	}

}
