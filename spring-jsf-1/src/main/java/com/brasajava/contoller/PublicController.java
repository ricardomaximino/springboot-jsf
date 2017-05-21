package com.brasajava.contoller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brasajava.beans.AddressImpl;
import com.brasajava.beans.CustomerImpl;
import com.brasajava.beans.Email;
import com.brasajava.beans.Permission;
import com.brasajava.beans.Phone;
import com.brasajava.beans.SMTPMailSender;
import com.brasajava.beans.User;
import com.brasajava.beans.interfaces.Address;
import com.brasajava.beans.interfaces.Customer;
import com.brasajava.repositories.CustomerRepository;
import com.brasajava.repositories.UserRepository;

@Controller
public class PublicController {

	private static final Logger log = LoggerFactory.getLogger(PublicController.class);
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SMTPMailSender sender;
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping("/spring/*")
	@ResponseBody
	public String creator(Locale locale) {
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

		return  messageSource.getMessage("hi", null, locale) +" this is an answer from Spring ResponseBody";
	}
	
	@RequestMapping("/mail")
	@ResponseBody
	public String sendEmail(@RequestParam("to")String to,@RequestParam("subject")String subject,@RequestParam("text")String text){
		String result = "The email was sent successfully.";
		System.out.println("To: " + to + " Subject: " + subject + " Text: " + text);
		try {
			sender.send(to, subject, text);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "An error occure, the email was not sent.";
		}
		return result;
	}
	@RequestMapping("/add")
	@ResponseBody
	public String createUser(@RequestParam("name")String param){
		Address address = new AddressImpl();
		User user = new User();
		user.setActive(true);		
		user.setAddress(address);
		user.setBirthday(LocalDate.now());
		user.setCredit(new BigDecimal("2000"));
		List<Email> emails = new ArrayList<>();
		Email email = new Email();
		email.setContact("email@"+param+".com");
		emails.add(email);
		user.setEmails(emails);
		user.setFirstLastName(param);
		user.setName(param);
		user.setPassword(param);
		List<Permission> permissions =new ArrayList<>();
		Permission permission =new Permission();
		permission.setName("ROLE_USER");
		permission.setPermission("ROLE_USER");
		permissions.add(permission);
		user.setPermissions(permissions);
		List<Phone> phones =new ArrayList<>();
		Phone phone =new Phone();
		phone.setContact("777777777777777");
		phone.setDescription("Movíl");
		phone.setMain(true);
		phones.add(phone);
		user.setPhones(phones);
		user.setSecondLastName(param);
		user.setUsername(param);
		user = userRepository.save(user);
		return user.getId() + " was saved successfully";	
	}

}
