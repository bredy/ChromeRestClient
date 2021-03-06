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
 * This event is fired when user change HTTP payload encoding value.
 * <p>
 * It will be propagated through the applications {@link EventBus} and fired on
 * browser's document object as custom event {@link CustomEvent#HTTP_ENCODING_CHANGE} so
 * external extensions can handle action.
 * </p>
 * <p>
 * For example:
 * 
 * <pre>
 * document.addEventListener('arc:encodingchange', function(e){ var currentHttpEncoding = e.data; //do something... });
 * </pre>
 * 
 * </p>
 */
public class HttpEncodingChangeEvent extends Event<HttpEncodingChangeEvent.Handler> {
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
	 * Handles {@link HttpEncodingChangeEvent}.
	 */
	public interface Handler {
		/**
		 * @param current payload encoding
		 */
		void onChange(String method);
	}
	
	public final String encoding;
	
	public HttpEncodingChangeEvent(String encoding){
		this.encoding = encoding;
		
	}
	
	@Override
	protected void dispatch(Handler handler) {
		handler.onChange(encoding);
	}

	@Override
	public Event.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
