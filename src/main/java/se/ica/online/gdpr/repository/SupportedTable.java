package se.ica.online.gdpr.repository;

public enum SupportedTable {

	CUSTOMER_PROFILE("customer_profile", "customer_id", "profile_data");

	public final String table;
	public final String primaryKey;
	public final String valueColumn;

	SupportedTable(String table, String primaryKey, String valueColumn) {
		this.table = table;
		this.primaryKey = primaryKey;
		this.valueColumn = valueColumn;
	}
}
