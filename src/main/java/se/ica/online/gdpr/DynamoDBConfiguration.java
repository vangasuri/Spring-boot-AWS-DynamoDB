package se.ica.online.gdpr;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfiguration {
	@Value("${amazon.dynamodb.endpoint}")
	private String amazonDynamoDBEndpoint;

	@Value("${amazon.aws.accesskey}")
	private String amazonAWSAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String amazonAWSSecretKey;

	@Value("${amazon.aws.proxy.host}")
	private String proxyHost;

	@Value("${amazon.aws.proxy.port}")
	private Integer proxyPort;

	@Bean
	public AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider credentialsProvider) {
		AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();

		if (StringUtils.isNotEmpty(amazonAWSAccessKey)) {
			builder = builder.withCredentials(credentialsProvider);
		}

		if (StringUtils.isNotEmpty(amazonDynamoDBEndpoint)) {
			builder = builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "eu-central-1"));
		}

		if (StringUtils.isNotEmpty(proxyHost)) {
			ClientConfiguration cc = new ClientConfiguration();
			cc.setProxyHost(proxyHost);
			cc.setProxyPort(proxyPort);
			builder = builder.withClientConfiguration(cc);
		}

		return builder.build();
	}

	@Bean
	public AWSCredentialsProvider amazonAWSCredentials() {
		// Or use an AWSCredentialsProvider/AWSCredentialsProviderChain
		AWSCredentials credentials = new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);

		return new AWSStaticCredentialsProvider(credentials);
	}
}
