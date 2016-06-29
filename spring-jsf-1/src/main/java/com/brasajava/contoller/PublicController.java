package com.brasajava.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brasajava.beans.AddressImpl;
import com.brasajava.beans.CustomerImpl;
import com.brasajava.beans.interfaces.Customer;
import com.brasajava.repositories.CustomerRepository;

@Controller
public class PublicController {

	private static final Logger log = LoggerFactory.getLogger(PublicController.class);
	@Autowired
	private CustomerRepository repository;
	
	@RequestMapping("/spring/*")
	@ResponseBody
	public String creator(MessageSource messageSource) {
		try{
			CustomerImpl cust = new CustomerImpl();
			cust.setFirstName("test");
			cust.setLastName("test");
			AddressImpl address = new AddressImpl();
			address.setCountry("España");
			address.setCity("Santa Pola");
			address.setStreet("Calle Ganaderos, 24");
			cust.setAddress(address);
			repository.save(cust);
			
			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			CustomerImpl customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Test'):");
			log.info("--------------------------------------------");
			for (CustomerImpl test : repository.findByLastName("Test")) {
				log.info(test.toString());
				log.info(test.getAddress().toString());
			}
			log.info("");
			
			}catch(Exception ex){
				log.error(ex.getMessage());
			}
		
		/*try{
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));

		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Customer customer : repository.findAll()) {
			log.info(customer.toString());
		}
		log.info("");

		// fetch an individual customer by ID
		Customer customer = repository.findOne(1L);
		log.info("Customer found with findOne(1L):");
		log.info("--------------------------------");
		log.info(customer.toString());
		log.info("");

		// fetch customers by last name
		log.info("Customer found with findByLastName('Bauer'):");
		log.info("--------------------------------------------");
		for (Customer bauer : repository.findByLastName("Bauer")) {
			log.info(bauer.toString());
		}
		log.info("");
		}catch(Exception ex){
			log.error(ex.getMessage());
		}*/

		return "Answer from Spring ResponseBody";
	}

}
