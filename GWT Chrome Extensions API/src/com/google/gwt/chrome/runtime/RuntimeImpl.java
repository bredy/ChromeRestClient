package com.google.gwt.chrome.runtime;

import com.google.gwt.chrome.runtime.Runtime.ManifestHandler;
import com.google.gwt.chrome.runtime.Runtime.RuntimeHandler;
import com.google.gwt.chrome.runtime.Runtime.RuntimeStringHandler;
import com.google.gwt.core.client.JavaScriptException;

public class RuntimeImpl implements ChromeRuntime {

	@Override
	public final native void getLastError(RuntimeStringHandler handler) /*-{
		var err = $wnd.chrome.runtime.lastError;
		var message = null;
		if(err && err.message){
			message = err.message;
		}
		handler.@com.google.gwt.chrome.runtime.Runtime.RuntimeStringHandler::onResult(Ljava/lang/String;)(message);
	}-*/;

	@Override
	public final native void getId(final RuntimeStringHandler handler) throws JavaScriptException /*-{
		var id = $wnd.chrome.runtime.id;
		handler.@com.google.gwt.chrome.runtime.Runtime.RuntimeStringHandler::onResult(Ljava/lang/String;)(id);
	}-*/;

	@Override
	public final native void getManifest(ManifestHandler handler) throws JavaScriptException /*-{
		var manifest = $wnd.chrome.runtime.getManifest();
		handler.@com.google.gwt.chrome.runtime.Runtime.ManifestHandler::onManifest(Lcom/google/gwt/chrome/runtime/ManifestDetails;)(manifest);
	}-*/;

	@Override
	public final native void getURL(String path, RuntimeStringHandler handler) throws JavaScriptException /*-{
		var url = $wnd.chrome.runtime.getURL(path);
		handler.@com.google.gwt.chrome.runtime.Runtime.RuntimeStringHandler::onResult(Ljava/lang/String;)(url);
	}-*/;

	@Override
	public final native void addOnInstalledHandler(RuntimeHandler handler) throws JavaScriptException /*-{
		var clb = $entry(function(){
			handler.@com.google.gwt.chrome.runtime.Runtime.RuntimeHandler::onActionPerformed()();
		});
		$wnd.chrome.runtime.onInstalled.addListener(clb);
	}-*/;

	@Override
	public final native void addOnSuspendHandler(RuntimeHandler handler) throws JavaScriptException /*-{
		var clb = $entry(function(){
			handler.@com.google.gwt.chrome.runtime.Runtime.RuntimeHandler::onActionPerformed()();
		});
		$wnd.chrome.runtime.onSuspend.addListener(clb);
	}-*/;

	@Override
	public final native void addOnSuspendCanceledHandler(RuntimeHandler handler) throws JavaScriptException /*-{
		var clb = $entry(function(){
			handler.@com.google.gwt.chrome.runtime.Runtime.RuntimeHandler::onActionPerformed()();
		});
		$wnd.chrome.runtime.onSuspendCanceled.addListener(clb);
	}-*/;

}
