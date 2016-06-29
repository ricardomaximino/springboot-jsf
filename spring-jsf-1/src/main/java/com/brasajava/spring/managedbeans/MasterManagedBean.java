package com.brasajava.spring.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.brasajava.beans.AddressImpl;
import com.brasajava.beans.CustomerImpl;
import com.brasajava.beans.interfaces.Address;
import com.brasajava.beans.interfaces.Customer;
import com.brasajava.repositories.CustomerRepository;

@Component
@Scope("request")
public class MasterManagedBean {

	private static final Logger log = LoggerFactory.getLogger(MasterManagedBean.class);
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private Customer customer;
	
	private Customer selectedCustomer;
	@Autowired
	private Address address;

	private List<CustomerImpl> customers;
	
	private String search;

	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<CustomerImpl> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerImpl> customers) {
		this.customers = customers;
	}

	private void refreshCustomers() {
		customers =  (List<CustomerImpl>) customerRepository.findAll();
	}

	@PostConstruct
	public void init() {
		log.info("INIT MASTER");
		refreshCustomers();
	}
	public void doNothing(){
		log.info("JUST DO NOTHING");
	}

	@PreDestroy
	public void destroy(){
		log.info("Master predestroy");
	}
	public void save() {
		log.info("Starting save/update the customer");
		try{
			this.setToSave();
			customerRepository.save((CustomerImpl)customer);
			this.refreshCustomers();
			log.info("Customer saved successfully!");
		}catch(Exception ex){
			log.error("Customer not saved");
			ex.printStackTrace();
		}
	}
	private void setToSave(){
		Customer newCustomer = new CustomerImpl();
		Address newAddress = new AddressImpl();
		newCustomer.setId(customer.getId());
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newAddress.setId(address.getId());
		newAddress.setCountry(address.getCountry());
		newAddress.setCity(address.getCity());
		newAddress.setStreet(address.getStreet());
		customer = newCustomer;
		address = newAddress;
		customer.setAddress(address);
	}
	public void onRowSelect(SelectEvent event) {
		selectedCustomer =  customerRepository.findOne(((Customer) event.getObject()).getId());
	}

	public void find() {
		log.info("Find Method and the value is " + search);
		setCustomers((search == "")?(List<CustomerImpl>) customerRepository.findAll() : customerRepository.findByLastName(search));
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}
}
