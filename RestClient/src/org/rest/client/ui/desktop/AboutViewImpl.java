package org.rest.client.ui.desktop;

import org.rest.client.tutorial.TutorialFactory;
import org.rest.client.ui.AboutView;
import org.rest.client.ui.TutorialDialog;
import org.rest.client.ui.TutorialDialog.Direction;

import com.google.gwt.chrome.runtime.ManifestDetails;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AboutViewImpl extends Composite implements AboutView {
	
	private static AboutViewImplUiBinder uiBinder = GWT
			.create(AboutViewImplUiBinder.class);

	interface AboutViewImplUiBinder extends UiBinder<Widget, AboutViewImpl> {
	}
	
	
	@UiField SpanElement versionField;
	@UiField Element licensing;
	@UiField Element licensingDialog;
	@UiField Element donateDialog;
	
	
	public AboutViewImpl(){
		initWidget(uiBinder.createAndBindUi(this));
		observeLicensingButton(this);
	}
	
	@Override
	public void setPresenter(Presenter listener) {
		
	}

	@Override
	public void setManifest(ManifestDetails manifest) {
		if(manifest == null) return;
		String version = manifest.getVersion();
		if(version == null) return;
		versionField.setInnerText("Application version: " + version);
	}

	@Override
	public void setUpTutorial(TutorialFactory factory) {
		TutorialDialog plusone = TutorialFactory.createItem();
		plusone.setAbsolutePosition(175, 123);
		plusone.setHTML("<strong>+1 me! :)</strong><br/>You may also want to review my application in <a target=\"_blank\" href=\"https://chrome.google.com/webstore/detail/advanced-rest-client-appl/hgmloofddffdnphfgcellkdfbfbjeloo/reviews?hl=en-US\">Chrome Web Store</a>");
		plusone.showArrow(Direction.BOTTOM);
		factory.addItem(plusone);
		
		TutorialDialog donate = TutorialFactory.createItem();
		donate.setAbsolutePosition(228, 204);
		donate.setHTML("<h1>Donate any value! :)</h1><br/>Please, express your gratitude donating my work. &euro; 1 is just fine if each of you donate :)");
		donate.showArrow(Direction.BOTTOM);
		factory.addItem(donate);
		
		factory.start();
	}

	@Override
	public void showDonateDialog() {
		_showDonateDialog(this);
		/*DonateDialogViewImpl dialog = new DonateDialogViewImpl();
		dialog.show();*/
	}
	
	public final native void _showDonateDialog(AboutViewImpl context) /*-{
		var dialog = this.@org.rest.client.ui.desktop.AboutViewImpl::donateDialog;
		if(!dialog) return;
		dialog.open();
	}-*/;
	
	private final native void observeLicensingButton(AboutViewImpl context) /*-{
		var button = this.@org.rest.client.ui.desktop.AboutViewImpl::licensing;
		if(!button) return;
		button.addEventListener('tap', function(){
			(context.@org.rest.client.ui.desktop.AboutViewImpl::licensingDialog).open();
		});
	}-*/;
}
