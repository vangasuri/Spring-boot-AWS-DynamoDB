package se.ica.online.gdpr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ica.online.gdpr.domain.CustomerProfile;
import se.ica.online.gdpr.repository.CustomerProfileRepository;

import java.util.Optional;

@Service
public class CustomerService {
	private CustomerProfileRepository customerRepository;

	@Autowired
	public CustomerService(CustomerProfileRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void saveCustomer(String customerId, String data) {
		CustomerProfile profile = new CustomerProfile(customerId, data);
		customerRepository.save(profile);
	}

	public Optional<CustomerProfile> findById(String customerId) {
		return customerRepository.findByCustomerId(customerId);
	}

}
