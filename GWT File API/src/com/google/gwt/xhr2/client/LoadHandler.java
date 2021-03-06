package com.google.gwt.xhr2.client;
/**
 * A ready-state callback for an {@link XMLHttpRequest2} object.
 */
public interface LoadHandler {
	/**
	 * This is called when the request has successfully completed.
	 * {@link XMLHttpRequest2#setOnLoad}.
	 * @param event 
	 */
	void onLoaded(Response response, ProgressEvent event);

	void onError(Response r, Throwable exception);
}
