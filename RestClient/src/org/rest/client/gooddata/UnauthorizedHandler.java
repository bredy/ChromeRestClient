package org.rest.client.gooddata;

import java.util.Date;

import org.rest.client.RestClient;
import org.rest.client.event.RequestStartActionEvent;
import org.rest.client.event.UnauthorizedResponseEvent;
import org.rest.client.storage.store.objects.RequestObject;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.Callback;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.xhr2.client.LoadHandler;
import com.google.gwt.xhr2.client.ProgressEvent;
import com.google.gwt.xhr2.client.RequestBuilder;
import com.google.gwt.xhr2.client.Response;
import com.google.web.bindery.event.shared.EventBus;

public class UnauthorizedHandler implements UnauthorizedResponseEvent.Handler {
	
	private final String GDC_PREFIX = "/gdc/";
	private final String GDC_TOKEN_PATH = GDC_PREFIX + "account/token";
	
	private final EventBus eventBus;
	
	public UnauthorizedHandler(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@Override
	public void onUnauthorizedResponse() {
		RestClient.collectRequestData(new Callback<RequestObject, Throwable>() {
			@Override
			public void onSuccess(final RequestObject data) {
				if (isGdcUri(data)) {
					authorize(data);
				} else {
					Log.info("Not GD URI in UnauthorizedHandler: " + data.getURL());
				}
			}
			
			@Override
			public void onFailure(Throwable reason) {
				Log.error("Cannot load request data in UnauthorizedHandler");
			}
		});
	}
	
	private void authorize(final RequestObject data) {
		RequestBuilder requestBuilder = new RequestBuilder(getTokenUri(data), "GET");
		requestBuilder.setLoadHandler(new LoadHandler() {			
			@Override
			public void onLoaded(Response response, ProgressEvent event) {
				eventBus.fireEvent(new RequestStartActionEvent(new Date()));
			}
			
			@Override
			public void onError(Response response, Throwable e) {
				Log.error("Cannot refresh token", e);
			}
		});
		
		try {
			requestBuilder.send();
		} catch(Throwable e) {
			Log.error("Refresh token failed", e);
		}

	}
	
	private boolean isGdcUri(final RequestObject data) {
		AnchorElement a = Document.get().createAnchorElement();
		a.setHref(data.getURL());
		return a.getPropertyString("pathname").startsWith(GDC_PREFIX);
	}
	
	private String getTokenUri(final RequestObject data) {
		AnchorElement a = Document.get().createAnchorElement();
		a.setHref(data.getURL());
		return a.getPropertyString("protocol") + "//" + a.getPropertyString("host") + GDC_TOKEN_PATH;
	}
}

