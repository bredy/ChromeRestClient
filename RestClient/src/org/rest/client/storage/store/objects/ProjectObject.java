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
package org.rest.client.storage.store.objects;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * This class represents form encoding data row object.
 * 
 * @author Paweł Psztyć
 * 
 */
public final class ProjectObject extends JavaScriptObject {
	protected ProjectObject() {
	}
	
	/**
	 * Create Request History object with predefined values.
	 * @return
	 */
	public static native ProjectObject create() /*-{
		return {
			id: -1,
			name: null,
			time: new Date().getTime()
		}
	}-*/;
	
	/**
	 * @return IDB ID.
	 */
	public final native int getId() /*-{
		return this.id;
	}-*/;
	
	public final native void setId(int id) /*-{
		this.id = id;
	}-*/;
	
	public final native void setCreated(double created) /*-{
		this.time = created;
	}-*/;

	public final native double getCreated() /*-{
		if(!this.time){
			this.time = new Date().getTime();
		}
		return this.time;
	}-*/;
	/**
	 * Sets project name
	 * 
	 * @param name
	 *            Project name to set.
	 */
	public final native void setName(String name) /*-{
		this.name = name;
		$wnd._a = this;
	}-*/;

	/**
	 * @return Saved project name
	 */
	public final native String getName() /*-{
		return this.name;
	}-*/;
	
	
	public final static native ProjectObject redefineObject(ProjectObject obj) /*-{
		return {
			id: obj.id,
			name: obj.name,
			time: obj.time
		}
	}-*/;
	
	/**
	 * @return {@link RequestObject} as a {@link JSONObject}
	 */
	public final JSONObject toJSONObject(){
		JSONObject obj = new JSONObject();
		obj.put("id", new JSONNumber(getId()));
		obj.put("name", new JSONString(getName() == null ? "" : getName()));
		obj.put("time", new JSONNumber(getCreated()));
		return obj;
	}
}
