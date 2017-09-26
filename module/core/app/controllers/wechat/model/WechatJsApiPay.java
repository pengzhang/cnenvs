package controllers.wechat.model;

import com.google.gson.annotations.SerializedName;

public class WechatJsApiPay {
	/**
	 * {
	 * 		"appId":"wx7d361d5180160f41",
	 * 		"nonceStr":"d44ya233f79hc6zv7tohikcmmyfsd51b",
	 * 		"package":"prepay_id=wx20160106110335a35e1d8fed0009438724",
	 * 		"signType":"MD5",
	 * 		"timeStamp":"1452049415",
	 * 		"paySign":"8E7056E2445EF540759135C6E2E4C22C"
	 * }
	 * jsapi支付的参数格式
	 */
	
	private String appId;
	private String nonceStr;
	@SerializedName("package")
	private String Wxpackage;
	private String signType;
	private String paySign;
	private String timeStamp;
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getWxpackage() {
		return Wxpackage;
	}
	public void setWxpackage(String wxpackage) {
		Wxpackage = wxpackage;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	@Override
	public String toString() {
		return "{appId:" + this.appId + ", nonceStr:" + this.nonceStr
				+ ", package:" + this.Wxpackage + ", signType:" + this.signType
				+ ", timeStamp:"+this.timeStamp+", paySign:" + this.paySign + "}";
	}
	
	
}
