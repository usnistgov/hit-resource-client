package gov.nist.hit.resources.deploy.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import gov.nist.hit.resources.deploy.api.ApiConfig;
import gov.nist.hit.resources.deploy.exception.InsupportedApiMethod;
import gov.nist.hit.resources.deploy.model.Action;
import gov.nist.hit.resources.deploy.model.ResourceType;

public class HL7v2ResourcesApiConfig implements ApiConfig {

	public Properties env;
	
	@Value("${prefix}")
	private String prefix;
	@Value("${edit}")
	private String edit;
	@Value("${delete}")
	private String delete;

	public HL7v2ResourcesApiConfig() throws IOException{
		env = new Properties();
		env.load(HL7v2ResourcesApiConfig.class.getResourceAsStream("/api-config.properties"));
		this.prefix = env.getProperty("prefix");
		this.edit = env.getProperty("edit");
		this.delete = env.getProperty("delete");
	}
	
	@Override
	public String urlFor(Action action, ResourceType entity, ResourceType container) throws InsupportedApiMethod {
		return getURL(action,entity,container);
	}
	
	public String getURL(Action action, ResourceType entity, ResourceType container) throws InsupportedApiMethod {
		String code = action.getCode() +"_"+ entity.getCode() + (container != null ? "_"+ container.getCode() : "");
		if(env.containsKey(code)){
			String mapping = env.getProperty(code);
			if(action.equals(Action.DELETE)){
				return this.prefix + this.delete + mapping;
			}
			else {
				return this.prefix + this.edit + mapping;
			}
		}
		else {
			throw new InsupportedApiMethod();
		}
	}

	@Override
	public String login() {
		return prefix+env.getProperty("login");
	}
	
//	public String delete(ResourceType entity) throws InsupportedApiMethod {
//		String main = this.prefix + this.delete;
//		switch(entity){
//		case TEST_CASE : return main + env.getProperty("delete_cbtc");
//		case CF_TEST_CASE : return main + env.getProperty("delete_cftc");
//		case TEST_PLAN : return main + env.getProperty("delete_tp");
//		case TEST_CASE_GROUP : return main + env.getProperty("delete_tg");
//		case TEST_STEP : return main + env.getProperty("delete_ts");
//		default : throw new InsupportedApiMethod();
//		}
//	}
//	
//	public String add(ResourceType entity, ResourceType container) throws InsupportedApiMethod {
//		String main = this.prefix + this.delete;
//		
//		switch(entity){
//		case TEST_CASE : container.equals(ResourceType.TEST_CASE_GROUP) ? main + env.getProperty("add_tc_tg");
//		case TEST_CASE_GROUP : return main + env.getProperty("delete_tg");
//		default : throw new InsupportedApiMethod();
//		}
//	}
//	
//	public String addOrUpdate(ResourceType entity)
	
	

}
