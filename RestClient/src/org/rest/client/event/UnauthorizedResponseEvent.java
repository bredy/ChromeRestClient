package org.rest.client.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class UnauthorizedResponseEvent extends Event<UnauthorizedResponseEvent.Handler> {
	
	public static final Type<Handler> TYPE = new Type<Handler>();
	
	public static HandlerRegistration register(EventBus eventBus, Handler handler) {
		return eventBus.addHandler(TYPE, handler);
	}
	
	/**
	 * Handles {@link UnauthorizedResponseEvent}.
	 */
	public interface Handler {
		void onUnauthorizedResponse();
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onUnauthorizedResponse();
	}

	@Override
	public Event.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
