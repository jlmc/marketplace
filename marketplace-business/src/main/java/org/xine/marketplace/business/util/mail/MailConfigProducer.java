package org.xine.marketplace.business.util.mail;

import java.io.IOException;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.SimpleMailConfig;

/**
 * The Class MailConfigProducer.
 */
public class MailConfigProducer {

	/**
	 * Gets the mail config.
	 *
	 * @return the mail config
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Produces
	@ApplicationScoped
	public SessionConfig getMailConfig() throws IOException{
		Properties props = new Properties();
		props.load(getClass().getResourceAsStream("/mail.properties"));
		
		SimpleMailConfig config = new SimpleMailConfig();
		
		config.setServerHost(props.getProperty("mail.server.host"));
		config.setServerPort(Integer.parseInt(props.getProperty("mail.server.port")));
		config.setEnableSsl(Boolean.parseBoolean(props.getProperty("mail.enable.ssl")));
		config.setAuth(Boolean.parseBoolean(props.getProperty("mail.auth")));
		config.setUsername(props.getProperty("mail.username"));
		config.setPassword(props.getProperty("mail.password"));
		
		return config;
	}
}
