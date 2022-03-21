package org.dominokit.samples.account.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="AccountUI")
public class AccountUIClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountUIClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Account frontend UI module ...");
		new ModuleConfigurator().configureModule(new AccountUIModuleConfiguration());
	}
}
