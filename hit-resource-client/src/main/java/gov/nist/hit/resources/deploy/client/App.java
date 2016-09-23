package gov.nist.hit.resources.deploy.client;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.http.ResponseEntity;

import gov.nist.hit.resources.deploy.factory.ResourceClientFactory;

/**
 * Hello world!
 *
 */
public class App {
	
	private static Scanner sc;
	
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		Options options = new Options();
		options.addOption("help", false, "print help");
		options.addOption("h", "host", true, "target tool host address");
		options.addOption("u", "username", true, "username");
		options.addOption("p", "password", true, "password");
		

		CommandLineParser parser = new DefaultParser();
		try {
			String host = "";
			String user = "";
			String pwd  = "";
			
			CommandLine cmd = parser.parse(options, args);
			if(cmd.hasOption("help")){
				HelpFormatter formater = new HelpFormatter();
				formater.printHelp("deployer", options);
				System.exit(0);
			}
			
			if(!exists(cmd, "h", "Host") | !exists(cmd, "u", "Username") | !exists(cmd, "p", "Password")){
				System.out.println("Use -help for details");
				System.exit(0);
			}
			else {
				host = cmd.getOptionValue("h");
				user = cmd.getOptionValue("u");
				pwd  = cmd.getOptionValue("p");
				ResourceClient client = ResourceClientFactory.createResourceClientWithDefault(host, user, pwd);
				System.out.println("====== Welcome to HIT Resource Deployer ======");
				System.out.println("To exit enter 'q'");
				System.out.println("List of possible commands :");
				String[] ak = new String[] { "addOrUpdateTestPlan", "addOrUpdateTestStep",
						"addOrUpdateCFTestCase", "addOrUpdateProfile", "addOrUpdateConstraint", "addOrUpdateValueSet",
						"updateTestCase", "updateTestGroup", "addTestCaseP", "addTestCaseG",
						"addTestGroupP", "addTestGroupG" };
				List<String> apiKeys = Arrays.asList(ak);
				for (String k : apiKeys) {
					System.out.println("- " + k);
				}
				
				Options optionsIn = new Options();
				optionsIn.addOption("z", "zip", true, "url of the zip folder containing resource");
				optionsIn.addOption("i", "id", true, "id of the container in case of addition");
				
				while(true){
					System.out.print("> ");
					String line = sc.nextLine();
					
					//Quit
					if(line.toLowerCase().equals("q"))
						break;
					
					//Print Help
					if(line.toLowerCase().equals("help")){
						HelpFormatter formater = new HelpFormatter();
						formater.printHelp("<command>", optionsIn);
						continue;
					}
					
					//Get arguments
					String[] argsL = line.split(" ");
					CommandLine instruction = parser.parse(optionsIn, argsL);
					
					if(apiKeys.contains(argsL[0])){
						
						RequestModel rm = new RequestModel();
						if(exists(instruction,"z","Resource Url")){
							rm.setUrl(instruction.getOptionValue("z"));
							if(instruction.hasOption("i")){
								rm.setId(Long.valueOf(instruction.getOptionValue("i")));
							}
							
							ResponseEntity<String> resp = null;
							switch (apiKeys.indexOf(argsL[0])) {
							case 0:
								resp = client.addOrUpdateTestPlan(rm);
								break;
							case 1:
								resp = client.addOrUpdateTestStep(rm);
								break;
							case 2:
								resp = client.addOrUpdateCFTestCase(rm);
								break;
							case 3:
								resp = client.addOrUpdateProfile(rm);
								break;
							case 4:
								resp = client.addOrUpdateConstraints(rm);
								break;
							case 5:
								resp = client.addOrUpdateValueSet(rm);
								break;
							case 6:
								resp = client.updateTestCase(rm);
								break;
							case 7:
								resp = client.updateTestCaseGroup(rm);
								break;
							case 8:
								resp = client.addTestCaseToPlan(rm);
								break;
							case 9:
								resp = client.addTestCaseToGroup(rm);
								break;
							case 10:
								resp = client.addTestCaseGroupToPlan(rm);
								break;
							case 11:
								resp = client.addTestCaseGroupToGroup(rm);
								break;
							}
							
							if (resp != null) {
								System.out.println("\n\n");
								System.out.println("Status : "
										+ resp.getStatusCodeValue());
							}
						}
					}
					else {
						System.out.println("Command '"+argsL[0]+"' not recognized");
						System.out.println("Please use one of the following commands :");
						for (String k : apiKeys) {
							System.out.println("- " + k);
						}
					}
				}
				
			}
		} catch (ParseException exp) {
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
		}
	}
	
	private static boolean exists(CommandLine cmd, String opt, String name){
		if(!cmd.hasOption(opt)){
			System.out.println(name+" is required");
			return false;
		}
		return true;
	}
	
}
