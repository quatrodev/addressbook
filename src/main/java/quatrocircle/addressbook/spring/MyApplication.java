package quatrocircle.addressbook.spring;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import quatrocircle.addressbook.controller.AddressBookAmqpRestfullController;
import quatrocircle.addressbook.controller.AddressBookRestfullController;

public class MyApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public MyApplication() {
		register(RequestContextFilter.class);
		register(AddressBookRestfullController.class);
		register(AddressBookAmqpRestfullController.class);
		register(CustomExceptionMapper.class);
	}
}