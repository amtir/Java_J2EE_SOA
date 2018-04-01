package postRestWebServiceClient;

/**
 * 
 * 
 * Client RESTful Web Service Jersey framework
 * Post request, JSON Objects
 * https://jersey.github.io/
 * 
 * @Programmer: Akram M'Tir
 * @Date : 01-01-2018
 * 
 */

//import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import org.glassfish.jersey.client.ClientConfig;
import model.CreditCardDetails;


public class JerseyClientPost {

  public static void main(String[] args) {

	  try {
		 /*
		  ClientConfig clientConfig = new ClientConfig();
		  Client client = ClientBuilder.newClient(clientConfig);
		  WebTarget webTarget = client.target("http://localhost:8080/CreditCards_Web-Service/");
		  WebTarget resourceWebTarget = webTarget.path("rest");
		  WebTarget helloworldWebTarget = resourceWebTarget.path("isValidCreditCardUser");
		  */
		  
	      CreditCardDetails cc = new CreditCardDetails();
	      cc.setCreditCardNumber("4012345678912124");
	      cc.setCC_CVV("349");
	      cc.setCC_Email("akam@gmx.de");
	      cc.setCC_validity("false");
	      
	      
	   /*  Response resp = ClientBuilder.newClient()
	      .target("http://localhost:8080/CreditCards_Web-Service/rest/isValidCreditCardUser/")
	      .request(MediaType.APPLICATION_JSON)
	      .post(Entity.entity(cc, MediaType.APPLICATION_JSON)); 
	   */
	   
	     //Response response = helloworldWebTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(cc, MediaType.APPLICATION_JSON));
	      
	      
		  Response response = ClientBuilder.newClient()
			      .target("http://localhost:8080/CreditCards_Web-Service/rest/isValidCreditCardUser/")
			     // .request(MediaType.APPLICATION_JSON)
			     // .post(Entity.entity(cc, MediaType.APPLICATION_JSON));
                  .request()
                  .post(Entity.json(cc));
		  
		  
		 
				  
		  System.out.println("Status response code: " + response.getStatus());
	      
	      if (response.getStatus() == 200) {

		      CreditCardDetails ccResponse = response.readEntity(CreditCardDetails.class);
		      System.out.println(ccResponse);
		      System.out.println("Is the Credit Card valid ?: " + ccResponse.getCC_validity());
	     
	      }else {
	    	  System.out.println("Status response code: " + response.getStatus());
	      }
	      response.close();
	  }
	  catch(Exception e) {
		  System.out.println(e);
		  System.out.println(e.getMessage());
		  //e.printStackTrace(new PrintStream(yourOutputStream));
		  e.printStackTrace(System.out);
	  }finally {
		  
	  }
  }
  
  
  
}