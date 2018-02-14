package se.ica.online.gdpr.repository;

import se.ica.online.gdpr.domain.CustomerProfile;

import java.util.Optional;

public interface CustomerProfileRepository {
	Optional<CustomerProfile> findByCustomerId(String customerId);

	void save(CustomerProfile profile);
}