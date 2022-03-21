package org.dominokit.samples.transactions.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="TransactionsUI")
public class TransactionsUIClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionsUIClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Transactions frontend UI module ...");
		new ModuleConfigurator().configureModule(new TransactionsUIModuleConfiguration());
	}
}
