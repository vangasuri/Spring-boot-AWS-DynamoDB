package se.ica.online.gdpr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.ica.online.gdpr.service.CustomerService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping(value = "/{customerId}", method = POST)
	public void saveCustomer(@PathVariable("customerId") String customerId, @RequestBody String data) {
		customerService.saveCustomer(customerId,data);
	}

	@RequestMapping(value = "/{customerId}", method = GET)
	public String getCustomer(@PathVariable("customerId") String customerId) {
		return customerService.findById(customerId)
				.orElseThrow(IllegalArgumentException::new)
				.getProfileData();
	}
}