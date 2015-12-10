package org.rest.client;

import org.rest.client.event.UnauthorizedResponseEvent;
import org.rest.client.gooddata.UnauthorizedHandler;

import com.google.web.bindery.event.shared.EventBus;

public class AuthorizationFactory {

	/**
	 * Initialize class.
	 * @param eb
	 */
	public static void initialize(EventBus eb) {
		UnauthorizedResponseEvent.register(eb, new UnauthorizedHandler(eb));
	}
}
