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
package org.rest.client.storage.store;

import org.rest.client.storage.IndexedDbAdapter;
import org.rest.client.storage.indexeddb.IDBDatabase;
import org.rest.client.storage.indexeddb.IDBDatabaseException;
import org.rest.client.storage.indexeddb.IDBIndexParameters;
import org.rest.client.storage.indexeddb.IDBObjectStore;
import org.rest.client.storage.indexeddb.IDBObjectStoreParameters;
import org.rest.client.storage.store.objects.FormEncodingObject;

import com.allen_sauer.gwt.log.client.Log;

public class FormEncodingsStore extends IndexedDbAdapter<Long, FormEncodingObject> {
	
	public final static String ENCODING_INDEX = "encoding";
	public static final String STORE_NAME = "form_encoding";
	
	public FormEncodingsStore() {
		super("rest_client", STORE_NAME);
	}

	@SuppressWarnings("unchecked")
	public static void setVestion(IDBDatabase db) throws IDBDatabaseException {
		
		Log.debug("Set store (form_encoding) new version " + databaseVersion);
		Log.warn("This will remove all previous data.");
		
		if(db.getObjectStoreNames().contains(STORE_NAME)){
			Log.debug("Remove previous selected store: " + STORE_NAME);
			db.deleteObjectStore(STORE_NAME);
		}
		
		IDBObjectStoreParameters parameters = IDBObjectStoreParameters.create();
		parameters.setKeyPath("id");
		parameters.setAutoIncrement(true);
		Log.debug("Create new store: Store name: " + STORE_NAME);
		IDBObjectStore<Long> newStore = (IDBObjectStore<Long>) db.createObjectStore(STORE_NAME, parameters);
		
		IDBIndexParameters encodingIndexParameters = IDBIndexParameters.create();
		encodingIndexParameters.setUnique(false);
		newStore.createIndex(ENCODING_INDEX, ENCODING_INDEX, encodingIndexParameters);
		Log.debug("Create new store index (created): Store name: " + STORE_NAME);
	}
}
