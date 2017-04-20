package gov.nist.hit.resources.deploy.factory;

import gov.nist.hit.resources.deploy.client.ResourceClient;
import gov.nist.hit.resources.deploy.config.ApiConfig;

public class ResourceClientFactory {
	
	public static ResourceClient createResourceClientWithDefault(String host, String username, String password){
		ApiConfig config = ResourceClientFactory.defaultConfig(); 	
    	return new ResourceClient(host,username,password,config);
	}
	public static ResourceClient createResourceClientWithDefault(String host,String authorization){
		ApiConfig config = ResourceClientFactory.defaultConfig(); 	
    	return new ResourceClient(host,authorization,config);
	}
	
	public static ApiConfig defaultConfig(){
		ApiConfig config = new ApiConfig();
		String edit = "editResources";
		String delete = "deleteResources";
		config.setLoginEndPoint("/api/accounts/login");
    	config.setContext("");
    	config.setMainMapping("/api/");
    	config.setAddOrUpdateTestStep(edit+"/cb/addOrUpdate/testStep");
    	config.setAddOrUpdateTestPlan(edit+"/cb/addOrUpdate/testPlan");
    	config.setAddOrUpdateCFTestCase(edit+"/cf/addOrUpdate/testCase");
    	config.setAddOrUpdateProfile(edit+"/global/addOrUpdate/profile");
    	config.setAddOrUpdateConstraints(edit+"/global/addOrUpdate/constraints");
    	config.setAddOrUpdateValueSet(edit+"/global/addOrUpdate/valueSet");
    	config.setAddTestCaseG(edit+"/cb/add/testCase/to/testCaseGroup");
    	config.setAddTestGroupP(edit+"/cb/add/testCaseGroup/to/testPlan");
    	config.setAddTestCaseP(edit+"/cb/add/testCase/to/testPlan");
    	config.setAddTestGroupG(edit+"/cb/add/testCaseGroup/to/testCaseGroup");
    	config.setUpdateTestCase(edit+"/cb/update/testCase");
    	config.setUpdateTestGroup(edit+"/cb/update/testCaseGroup");
    	config.setDeleteTestPlan(delete+"/cb/delete/testPlan/");
    	config.setDeleteCBTestCase(delete+"/cb/delete/testCase/");
    	config.setDeleteCFTestCase(delete+"/cf/delete/testCase/");
    	config.setDeleteTestCaseGroup(delete+"/cb/delete/testCaseGroup/");
    	config.setDeleteTestStep(delete+"/cb/delete/testStep/");
    	return config;
	}
	
	
	public static ResourceClient createResourceClient(String host, String username, String password, ApiConfig config){
    	return new ResourceClient(host,username,password,config);
	}

}
