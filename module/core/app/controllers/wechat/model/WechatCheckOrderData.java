package controllers.wechat.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class WechatCheckOrderData implements Serializable{
	
	private String appid;
	private String mch_id;
	private String nonce_str;
	private String sign;
	private String out_trade_no;
	private String sign_type;
	
	@XmlElement(name="appid")
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	@XmlElement(name="mch_id")
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	@XmlElement(name="nonce_str")
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	@XmlElement(name="sign")
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@XmlElement(name="out_trade_no")
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	@Override
	public String toString() {
		return "WechatCheckOrderData [appid=" + appid + ", mch_id=" + mch_id
				+ ", nonce_str=" + nonce_str + ", sign=" + sign
				+ ", out_trade_no=" + out_trade_no + ", sign_type=" + sign_type
				+ "]";
	}
	
}
