package com.qa.intergration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest") //this is the starting access point of this application (http://localhost:8080/finalaccount1/rest/account/json)
public class JAXActivator extends Application {
}

/*

	http://localhost:8080/finalaccount1/rest/account/json
	http protocol
		   server name
		   			:port name
		   				  war file name (located in target folder in project)
		   				  			    starting access point for this application, located in @ApplicationPath("rest")
		   				  			    	 looks for the path name 'account' as @Path annotaiton, it will look through all the classes in the heirarchy until it finds it
		   				  			    	 		 looks for path name 'json' in /account path

*/