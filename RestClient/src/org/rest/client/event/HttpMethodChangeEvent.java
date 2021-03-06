/*******************************************************************************
 * Copyright 2012 Paweł Psztyć
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.rest.client.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * This event is fired when user change HTTP method value.
 * <p>
 * It will be propagated through the applications {@link EventBus} and fired on
 * browser's document object as custom event {@link CustomEvent#CLEAR_ALL} so
 * external extensions can handle action.
 * </p>
 * <p>
 * For example:
 * 
 * <pre>
 * document.addEventListener('arc:metodchange', function(e){ //do something... });
 * </pre>
 * 
 * </p>
 */
public class HttpMethodChangeEvent extends Event<HttpMethodChangeEvent.Handler> {
	public static final Type<Handler> TYPE = new Type<Handler>();

	/**
	 * Register an handler for this event.
	 * 
	 * @param eventBus
	 * @param handler
	 * @return
	 */
	public static HandlerRegistration register(EventBus eventBus,
			Handler handler) {
		return eventBus.addHandler(TYPE, handler);
	}

	/**
	 * Handles {@link HttpMethodChangeEvent}.
	 */
	public interface Handler {
		/**
		 * @param current selected method
		 */
		void onMethodChange(String method);
	}
	
	public final String method;
	
	public HttpMethodChangeEvent(String method){
		this.method = method;
		
	}
	
	@Override
	protected void dispatch(Handler handler) {
		handler.onMethodChange(method);
	}

	@Override
	public Event.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
