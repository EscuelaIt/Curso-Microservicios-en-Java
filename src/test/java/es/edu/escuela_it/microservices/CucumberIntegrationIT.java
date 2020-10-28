package es.edu.escuela_it.microservices;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","json:target/integration_test/cucumber.json","html:target/integration_test"}, features = "src/test/resources/stories")
public class CucumberIntegrationIT {


	@Test
	public void contextLoads() {
		System.out.println("ejecutando contextLoads");
	}
	

}
