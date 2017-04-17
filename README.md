# HIT Resources Client
Client for publishing resources directly into HIT MU Tools

## Maven details

```xml
		<dependency>
			<groupId>gov.nist.hit.resources.deploy</groupId>
			<artifactId>hit-resource-client</artifactId>
			<version>${version}</version>
		</dependency>
```

## Client instantiation 

You can create a new instance of the **ResourceClient** class using the **ResourceClientFactory** 

```java
ResourceClient client = ResourceClientFactory.createResourceClientWithDefault(host, user, pwd);
```
Where :

- *host* : URL of the api access point
- *user* : Username of the deployer on the tool server
- *password* : Password of the deployer on the tool server

## Client Methods

### Check Credentials 
```java
public boolean validCredentials()
```
Retuns TRUE  if the credentials used to create a ResourceClient instance are correct. (user registred in remote server)
Return FALSE if the credentials used to create a ResourceClient instance are incorrect. (user not found in remote server)

### API Methods

All methods take as parameter an instance of **RequestModel** class which is a POJO with two attributes :
- *Id*  : Id of the containing object if any
- *Url* : Url of the ziped resource **[Always Required]**

The methods result is an instance of [**ResponseEntity\<String\>**](http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html "Spring Documentation for ResponseEntity")

###1. Add or Update TestPlan
```java
public ResponseEntity<String> addOrUpdateTestPlan(RequestModel m)
```
- **Required :** RequestModel.url

###2. Add TestCaseGroup To TestPlan
```java
public ResponseEntity<String> addTestCaseGroupToPlan(RequestModel m)
```
- **Required :** RequestModel.url
- **Required :** RequestModel.id ID of the TestPlan where the TestCaseGroup should be added

###3. Add TestCaseGroup To TestCaseGroup
```java
public ResponseEntity<String> addTestCaseGroupToGroup(RequestModel m)
```
- **Required :** RequestModel.url
- **Required :** RequestModel.id ID of the TestCaseGroup where the TestCaseGroup should be added

###4. Update TestCaseGroup
```java
public ResponseEntity<String> updateTestCaseGroup(RequestModel m)
```
- **Required :** RequestModel.url

###5. Add TestCase to TestCaseGroup
```java
public ResponseEntity<String> addTestCaseToGroup(RequestModel m)
```
- **Required :** RequestModel.url
- **Required :** RequestModel.id ID of the TestCaseGroup where the TestCase should be added

###6. Add TestCase to TestPlan
```java
public ResponseEntity<String> addTestCaseToPlan(RequestModel m)
```
- **Required :** RequestModel.url
- **Required :** RequestModel.id ID of the TestPlan where the TestCase should be added

###7. Update TestCase
```java
public ResponseEntity<String> updateTestCase(RequestModel m)
```
- **Required :** RequestModel.url

###8. Add or Update TestStep
```java
public ResponseEntity<String> addOrUpdateTestStep(RequestModel m)
```
- **Required :** RequestModel.url
- **Required if it's a new TestStep :** RequestModel.id ID of the TestCase where the new TestStep should be added

###9. Add or Update Context Free TestCase
```java
public ResponseEntity<String> addOrUpdateCFTestCase(RequestModel m)
```
- **Required :** RequestModel.url
- **Required if CF TestCase is new and should be added to a TestCase :** RequestModel.id ID of the CF TestCase where the new CF TestCase should be added

###10. Add or Update Integration Profile
```java
public ResponseEntity<String> addOrUpdateProfile(RequestModel m)
```
- **Required :** RequestModel.url

###11. Add or Update Constraints File
```java
public ResponseEntity<String> addOrUpdateConstraints(RequestModel m)
```
- **Required :** RequestModel.url

###12. Add or Update Value Set Library File
```java
public ResponseEntity<String> addOrUpdateValueSet(RequestModel m)
```
- **Required :** RequestModel.url

## Command Line Client

The main class is located in **gov.nist.hit.resources.deploy.client.App**

- First generate a JAR file from the project using **App** class as entry point
- You can then launch the deployer from the command line using **java -jar [jar_name]**
- To launch the jar you must specify the host, username and password using arguments -h, -u, -p
- If you need help using the launcher you can use -help argument to get indications on the usage
- Once the deployer is launched, a list of commands reflecting the API methods will be shown
- Refer to Methods Documentation to know what arguments are required for each command
- You can use -help on a command to get indication on how to specify arguments

### Example

Launching the deployer
```shell
./deployer.jar -h [host] -u [username] -p [password]
```
```shell
====== Welcome to HIT Resource Deployer ======
To exit enter 'q'
List of possible commands :
- addOrUpdateTestPlan
- addOrUpdateTestStep
- addOrUpdateCFTestCase
- addOrUpdateProfile
- addOrUpdateConstraint
- addOrUpdateValueSet
- updateTestCase
- updateTestGroup
- addTestCaseP
- addTestCaseG
- addTestGroupP
- addTestGroupG
> 

```
Actions :

- Type 'q' to quit the deployer
- Type command name followed by arguments to execute command
- Type 'help' for indications on how to use commands

```shell
> help
usage: <command>
 -i,--id <arg>    id of the container in case of addition
 -z,--zip <arg>   url of the zip folder containing resource
```








