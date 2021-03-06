package org.rest.client.ui.desktop.widget;

import org.rest.client.RestClient;
import org.rest.client.storage.StoreResultCallback;
import org.rest.client.storage.websql.StatusCodeRow;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.Image;

public class StatusCodeImage extends Image implements ClickHandler {
	
	int statusCode;
	StatusCodeRow requestedCodeData = null;;
	
	public StatusCodeImage(){
		super();
		SafeUri url = new SafeUri() {
			@Override
			public String asString() {
				return "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAABGdBTUEAAK/INwWK6QAAAAZiS0dEAAAAAAAA+UO7fwAAAAlwSFlzAAAASAAAAEgARslrPgAAAAl2cEFnAAAAGAAAABgAeEylpgAABhdJREFUSMeNlktsXUcdxn9zzrnnnHvta18/Ysd1HNtJnNgJlpMUUGlQI2gSUChig1Q2SCxZsGTHpmoBkVIkGjbsQEi8xKJEIDVtRClCkLQ0QNO4OA/nee34Xj/u67zmnDMzLOImplSBvzSa0Wjm++ab+c83A4+KmZdh+mUKn/453zAGQADilDH4R38N4y/B6IuPhBAf2bv/R2wbf4zVS1ccSqURq6u4r6voT7i2PWCMEVmaN+MovpO3g6uEyd3h2QOydu0GpCmsfes/oJz/XvVpeP+cWNXHxitT489sH+k/uWNs6MBgf7niFuxinhsRRCpZXw9a9+6sXF9fXn29vnjjDMuLCwxPqkcrmDwFae4624eO7tk79s2PP77rU/v3bi/395bQGmSqkKkhySCINWsNye1b9eTG/PVLK9dunJa19TNAQOe5B5D2g9bYi2C01z0x+qW5Q3ue/8LnZp/85NyoV+lyaTQC2o0mSoZEQUCzEZDlCsexKfX0OsW+baO5tg7LNI2zVvtf+MdT0jc+RNDuFs6euafmHp964YsnZg9NTw4IrQ13q+v0Wm2emPKZ3ekxs6NAxc+5u9xmvaPQKkfYDm7vcCVN8wNxu3VPrd58HzINtU2C4W/DY3tHdx+YfOHkidmjM7sGhNawUu/QY3V49ugwGjh3cYN2pDgy28/MziLvXmuy1jFkaUpubOgaqMgw2pkE8rxxnq2R/goLAINV3j5w/ODc5FP7JgeF0iBTTTtIGOl3qZRdltYk/7yjOPdeytsLbaZ3dnPkQDdxlBAniiRsgeXQNTb1MbfS/2XSC0Vgk6CnOFgZ6Ds5Pr6917EsZKqIZY5tF7h0O+H35+v8dSHAcnyMcFneyNBas3dHCdcxRIkilhlJ2MLuKnteX/8JHH/XgzS1iv7uyraBuaXIF+VaxlCXIU5ybMdhI+jizDsxBheETRjHFBwXMAhLkCtDLHPSTBPGHYwd41V6JpxicTqPmHcA/GJxR7m3p+96XXNtOeX4tE3ZNUipsW0Hv1ik1ZFIGTFaMRzeU0IIuL6UsNZSyNQiTjIanQ62Y+N7brfteyP5BwoKtl2xbMcPYsW1pYQkFjwxadPrGdJU0wkko30ZR2a6mR71GNvmcnc15ezFFkEMMs3Y6AREcYTnubjYrnAKZUA4AMYYtNIYW5GrnPlqzmrDsG/IZqgs0LliZqzIsYNlwkTz1tWIX7y5zl/mIzpRRicMkanE6AyjbbQWGL3FKrI0a6cylY6ny5ZRaKOotXI22ppez9Dva47Puaw0FD945R5/vtymupoiZUaSSrTKQCvQCoFApVmmpQwA4wDIIF4KG61WV2VksCA0UiswmkzlrCU5YaAIpWapkfHbCxss12MQ6gEoWt8H1xrbslFRFKgwXnmQpjqMFpsrq+8VVGLKvgCVg94sRqFUzjuLEa+9G5BmGtgKvjlO5diWhaUtso3GHR3FVx7eg1jW26uN18J6vTPYbVEQ+j6JUqBydg17HJvr5jP7izx9sELBMlvA79cCTcErYcI4yzeab2Dk4kMCIVRcX3+1dv3WhWIemqFeF2E2JxrFoT0lnjncxZNTDic/0Ue3a2+qfKjCKXjY2iFdWllQ7dZv4FT40OziP2KCnk5m9W/YlnW4f7CyzQiLJJHoPMcrWEyOFElywe/ebvK3+VVUlm4eqqbg+niFMvly/V5Wrb5k5L2z8IqCf2xx076vGhUEVZmkLaPy/ZWB3gHXc8nzjJW1kLcWOrx+scmf/r5CFEYIo7FtG69YxrWK5Mv1lfRu9bQOGj+FnhB++CG7Tv4Apc/mebNzJYniWzqWI75bGO4udzm249AKMlY3Yow2FFwPzyvhFUqIMM2y6vJ8Vq1+XweNn4Fownc/4sEBSN6Ens/nunbpahRYF5JGc011Qt/R2i2AcIVtOUZoSypp2mErq9auyNvVX2a12veMrL6K6AvhO//j0e97HoRDz84dtC8v+LjehFP0p23PHxUFt2y0ETqRoYqTFR1GVzDport7X5DevAk6BU79H7+KD6L0HAgLf2KKr13+Cj8WQgD8xBjz9b1nkYvn76cqsHVbtsa/AUxvYpal/D8YAAAAJXRFWHRjcmVhdGUtZGF0ZQAyMDA5LTEyLTA4VDEyOjUzOjEyLTA3OjAwORLHiQAAACV0RVh0ZGF0ZTpjcmVhdGUAMjAxMC0wMi0yMFQyMzoyNjoxNy0wNzowMJGkTagAAAAldEVYdGRhdGU6bW9kaWZ5ADIwMTAtMDEtMTFUMDk6MjA6MTEtMDc6MDALol5eAAAANXRFWHRMaWNlbnNlAGh0dHA6Ly9jcmVhdGl2ZWNvbW1vbnMub3JnL2xpY2Vuc2VzL0xHUEwvMi4xLzvBtBgAAAAldEVYdG1vZGlmeS1kYXRlADIwMDktMTItMDhUMTI6NTM6MTItMDc6MDBmo7G9AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAA10RVh0U291cmNlAE51dm9sYaxPNfEAAAA0dEVYdFNvdXJjZV9VUkwAaHR0cDovL3d3dy5pY29uLWtpbmcuY29tL3Byb2plY3RzL251dm9sYS92PbRSAAAAAElFTkSuQmCC";
			}
		};
		setUrl(url);
		setTitle("Show explanation");
		setStyleName("statusCodeHintImage");
		setWidth("16px");
		setHeight("16px");
		setVisible(false);
	}
	
	public void setCode(int code){
		this.statusCode = code;
		addClickHandler(this);
		setVisible(true);
	}

	@Override
	public void onClick(ClickEvent event) {
		if (statusCode == 0) {
			StatusCodeInfo dialog = new StatusCodeInfo();
			dialog.setHTML("Status Code: " + statusCode);
			dialog.setInfoText("No response data.");
			dialog.center();
			dialog.show();
			return;
		}
		
		if(requestedCodeData != null){
			buildDataDialog();
			return;
		}
		RestClient.getClientFactory().getStatusesStore().getByKey(statusCode, new StoreResultCallback<StatusCodeRow>(){
			@Override
			public void onSuccess(StatusCodeRow code) {
				requestedCodeData = code;
				buildDataDialog();
			}

			@Override
			public void onError(Throwable e) {
				StatusCodeInfo dialog = new StatusCodeInfo();
				dialog.setHTML("Status Code: " + statusCode);
				dialog.setInfoText("Unable to find explanation");
				dialog.center();
				dialog.show();
			}});
	}
	
	void buildDataDialog(){
		String html = "<b>Status Code: " + statusCode;
		if(requestedCodeData.getLabel() != null){
			html += " - " + requestedCodeData.getLabel() + " ";
		}
		html += "</b><br/><br/>"+requestedCodeData.getDesc();
		StatusCodeInfo dialog = new StatusCodeInfo();
		dialog.setInfoText(html);
		dialog.center();
		dialog.show();
	}
}
