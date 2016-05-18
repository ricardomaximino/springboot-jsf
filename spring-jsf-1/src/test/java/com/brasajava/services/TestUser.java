package com.brasajava.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.brasajava.SpringTest;
import com.brasajava.beans.AddressImpl;
import com.brasajava.beans.Email;
import com.brasajava.beans.Permission;
import com.brasajava.beans.Phone;
import com.brasajava.beans.User;
import com.brasajava.beans.interfaces.Address;
import com.brasajava.repositories.UserRepository;

@Transactional
public class TestUser extends SpringTest{
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void contextLoads() {
		User user = new User();
		user.setName("Ricardo Maximino");
		user.setFirstLastName("Gonçalves");
		user.setSecondLastName("De Moraes");
		user.setActive(false);
		user.setAddress(this.generateAddress());
		user.setBirthday(LocalDate.of(1982, 11, 15));
		user.setCredit(new BigDecimal(1200));
		user.setEmail("ricardomaximino@hotmail.com");
		user.setPassword("Ricardo2");
		user.setEmails(this.emailList());
		user.setPhones(this.phoneList());
		user.setPermissions(this.permissionList());
		
		log.info("@Before save " + user.toString());
		User user2 = userRepository.save(user);
		
		log.info("@After save " + user2.toString());
		Assert.assertNotEquals("The user has a not valid id.", 0, user2.getId());
	}
	@Test
	public void manyInserts(){
		User user = null;
		List<User> list = new ArrayList<User>();
		
		for(int i = 0; i < 10; i++){
			user = new User();
			user.setName("Ricardo Maximino"+i);
			user.setFirstLastName("Gonçalves");
			user.setSecondLastName("De Moraes");
			user.setActive(false);
			user.setBirthday(LocalDate.of(1982, 11, 15));
			user.setCredit(new BigDecimal(1200));
			user.setEmail("ricardomaximino@hotmail.com"+i);
			user.setPassword("Ricardo2");
			user.setEmails(this.emailList());
			user.setPhones(this.phoneList());
			user.setPermissions(this.permissionList());
			list.add(user);
		}
		log.info("List size: " + list.size());
		Assert.assertEquals(list.size(), 10);
		for(User u : list){
			try{
				userRepository.save(u);
				log.info("user: " + u.getName() + " was successfully saved.");
			}catch(Exception ex){
				log.error(ex.getMessage());
			}
		}
	}

	private List<Phone> phoneList(){
		String[] description = {"home","mobile","job",};
		String[] contacts = {"966230267","634753562","966552211"};
		Phone phone = null;
		List<Phone> list = new ArrayList<Phone>();
		for(int i = 0; i < 3; i++){
			phone = new Phone();
			phone.setContact(contacts[i]);
			phone.setDescription(description[i]);
			phone.setMain(false);
			list.add(phone);
		}
		int random = (int) Math.round(Math.random()*2);
		list.get(random).setMain(true);
		return list;
	}
	private List<Email> emailList(){
		String[] description = {"home","whatsapp","job",};
		String[] contacts = {"ricardomaximino@hotmail.com","ricardomaximino@gmail.com","ricardomaximino@yahoo.com.br"};
		Email email = null;
		List<Email> list = new ArrayList<Email>();
		for(int i = 0; i < 3; i++){
			email = new Email();
			email.setContact(contacts[i]);
			email.setDescription(description[i]);
			email.setMain(false);
			list.add(email);
		}
		int random = (int) Math.round(Math.random()*2);
		list.get(random).setMain(true);
		return list;
	}
	private List<Permission> permissionList(){
		List<Permission> list = new ArrayList<Permission>();
		
		Permission permission = new Permission();
		permission.setName("Saver");
		permission.setPermission("save");
		
		list.add(permission);
		
		
		permission = new Permission();
		permission.setName("Updater");
		permission.setPermission("update");
		
		list.add(permission);
		
		return list;
	}
	private Address generateAddress(){
		Address address = new AddressImpl();
		address.setCountry("España");
		address.setState("Valencia");
		address.setCity("Alicante");
		address.setTown("Santa Pola");
		address.setStreet("Calle Ganaderos");
		address.setNumber("24");
		return address;
	}
}
