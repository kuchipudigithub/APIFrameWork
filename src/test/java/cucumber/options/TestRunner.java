package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features="src/test/java/features",
				 
		glue= {"stepDefinations"},
		
			   
		plugin={"pretty",
	    		   "json:target/cucumber-reports/cucumber.json", //cucumber report takes i/p as json to generate report & this json is prepared in run time
	    		   "html:target/cucumber-reports/cucumber.html"     // path where report is generated
     	}

    
	       
	
	
		)
public class TestRunner {

	
	//tags = "@Addplace"
}
