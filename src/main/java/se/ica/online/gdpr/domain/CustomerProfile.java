package se.ica.online.gdpr.domain;

public class CustomerProfile {
	private String customerId;
	private String profileData;

	public CustomerProfile(String customerId, String profileData) {
		this.customerId = customerId;
		this.profileData = profileData;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getProfileData() {
		return profileData;
	}
}
