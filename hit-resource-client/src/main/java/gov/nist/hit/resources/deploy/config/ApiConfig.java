package gov.nist.hit.resources.deploy.config;

public class ApiConfig {
	
	  private String context;
	  private String mainMapping;
	  private String addOrUpdateTestStep;
	  private String addTestCaseP;
	  private String addTestCaseG;
	  private String updateTestCase;
	  private String addTestGroupP;
	  private String addTestGroupG;
	  private String updateTestGroup;
	  private String addOrUpdateTestPlan;
	  private String addOrUpdateCFTestCase;
	  private String addOrUpdateProfile;
	  private String addOrUpdateValueSet;
	  private String addOrUpdateConstraints;
	  private String loginEndPoint;
	  private String deleteTestPlan;
	  private String deleteCFTestCase;
	  private String deleteCBTestCase;
	  private String deleteTestCaseGroup;
	  private String deleteTestStep;
	  
	  
	public ApiConfig() {
		super();
	}
	
	
	public String getAddTestCaseP() {
		return addTestCaseP;
	}


	public void setAddTestCaseP(String addTestCaseP) {
		this.addTestCaseP = addTestCaseP;
	}


	public String getAddTestCaseG() {
		return addTestCaseG;
	}


	public void setAddTestCaseG(String addTestCaseG) {
		this.addTestCaseG = addTestCaseG;
	}


	public String getAddTestGroupP() {
		return addTestGroupP;
	}


	public void setAddTestGroupP(String addTestGroupP) {
		this.addTestGroupP = addTestGroupP;
	}


	public String getAddTestGroupG() {
		return addTestGroupG;
	}


	public void setAddTestGroupG(String addTestGroupG) {
		this.addTestGroupG = addTestGroupG;
	}


	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getMainMapping() {
		return mainMapping;
	}
	public void setMainMapping(String mainMapping) {
		this.mainMapping = mainMapping;
	}
	public String getAddOrUpdateTestStep() {
		return addOrUpdateTestStep;
	}
	public void setAddOrUpdateTestStep(String addOrUpdateTestStep) {
		this.addOrUpdateTestStep = addOrUpdateTestStep;
	}
	public String getUpdateTestCase() {
		return updateTestCase;
	}
	public void setUpdateTestCase(String updateTestCase) {
		this.updateTestCase = updateTestCase;
	}
	public String getUpdateTestGroup() {
		return updateTestGroup;
	}
	public void setUpdateTestGroup(String updateTestGroup) {
		this.updateTestGroup = updateTestGroup;
	}
	public String getAddOrUpdateTestPlan() {
		return addOrUpdateTestPlan;
	}
	public void setAddOrUpdateTestPlan(String addOrUpdateTestPlan) {
		this.addOrUpdateTestPlan = addOrUpdateTestPlan;
	}
	public String getAddOrUpdateCFTestCase() {
		return addOrUpdateCFTestCase;
	}
	public void setAddOrUpdateCFTestCase(String addOrUpdateCFTestCase) {
		this.addOrUpdateCFTestCase = addOrUpdateCFTestCase;
	}
	public String getAddOrUpdateProfile() {
		return addOrUpdateProfile;
	}
	public void setAddOrUpdateProfile(String addOrUpdateProfile) {
		this.addOrUpdateProfile = addOrUpdateProfile;
	}
	public String getAddOrUpdateValueSet() {
		return addOrUpdateValueSet;
	}
	public void setAddOrUpdateValueSet(String addOrUpdateValueSet) {
		this.addOrUpdateValueSet = addOrUpdateValueSet;
	}
	public String getAddOrUpdateConstraints() {
		return addOrUpdateConstraints;
	}
	public void setAddOrUpdateConstraints(String addOrUpdateConstraints) {
		this.addOrUpdateConstraints = addOrUpdateConstraints;
	}


	/**
	 * @return the loginEndPoint
	 */
	public String getLoginEndPoint() {
		return loginEndPoint;
	}


	/**
	 * @param loginEndPoint the loginEndPoint to set
	 */
	public void setLoginEndPoint(String loginEndPoint) {
		this.loginEndPoint = loginEndPoint;
	}


	public String getDeleteTestPlan(Long id) {
		return deleteTestPlan+id;
	}


	public void setDeleteTestPlan(String deleteTestPlan) {
		this.deleteTestPlan = deleteTestPlan;
	}


	public String getDeleteCFTestCase(Long id) {
		return deleteCFTestCase+id;
	}


	public void setDeleteCFTestCase(String deleteCFTestCase) {
		this.deleteCFTestCase = deleteCFTestCase;
	}


	public String getDeleteCBTestCase(Long id) {
		return deleteCBTestCase+id;
	}


	public void setDeleteCBTestCase(String deleteCBTestCase) {
		this.deleteCBTestCase = deleteCBTestCase;
	}


	public String getDeleteTestCaseGroup(Long id) {
		return deleteTestCaseGroup+id;
	}


	public void setDeleteTestCaseGroup(String deleteTestCaseGroup) {
		this.deleteTestCaseGroup = deleteTestCaseGroup;
	}


	public String getDeleteTestStep(Long id) {
		return deleteTestStep+id;
	}


	public void setDeleteTestStep(String deleteTestStep) {
		this.deleteTestStep = deleteTestStep;
	}	


}
