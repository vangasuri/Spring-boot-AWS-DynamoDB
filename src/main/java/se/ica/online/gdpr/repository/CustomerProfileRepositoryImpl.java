package se.ica.online.gdpr.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ica.online.gdpr.domain.CustomerProfile;

import java.util.Optional;

import static se.ica.online.gdpr.repository.SupportedTable.CUSTOMER_PROFILE;

@Component
public class CustomerProfileRepositoryImpl implements CustomerProfileRepository {
	private final static Logger log = LoggerFactory.getLogger(CustomerProfileRepositoryImpl.class);

	private DynamoDB dynamoDb;

	@Autowired
	public CustomerProfileRepositoryImpl(AmazonDynamoDB client) {
		dynamoDb = new DynamoDB(client);
	}

	@Override
	public Optional<CustomerProfile> findByCustomerId(String customerId) {
		final Table table = dynamoDb.getTable(CUSTOMER_PROFILE.table);
		final GetItemSpec spec = new GetItemSpec().withPrimaryKey(CUSTOMER_PROFILE.primaryKey, customerId);
		final Item item = table.getItem(spec);

		if (item == null) {
			return Optional.empty();
		}

		String data = item.getJSON(CUSTOMER_PROFILE.valueColumn);
		return Optional.of(new CustomerProfile(customerId, data));
	}

	@Override
	public void save(CustomerProfile profile) {
		try {
			final Item item = new Item()
					.withPrimaryKey(CUSTOMER_PROFILE.primaryKey, profile.getCustomerId())
					.withJSON(CUSTOMER_PROFILE.valueColumn, profile.getProfileData());

			final Table table = dynamoDb.getTable(CUSTOMER_PROFILE.table);
			table.putItem(item);
		} catch (Exception e) {
			log.error("Failed to save customer data ", e);
		}
	}

}
