package controllers.wechat.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class WechatRefund implements Serializable{
	
	private String appid;
	private String mch_id;
	private String nonce_str;
	private String sign;
	private String transaction_id;
	private String out_trade_no;
	private String total_fee;
	private String refund_fee;
	private String refund_fee_type;
	private String op_user_id;
	private String device_info;
	private String out_refund_no;
	
	@XmlElement(name="out_refund_no")
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
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
	@XmlElement(name="transaction_id")
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	@XmlElement(name="out_trade_no")
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	@XmlElement(name="total_fee")
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	@XmlElement(name="refund_fee")
	public String getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}
	@XmlElement(name="refund_fee_type")
	public String getRefund_fee_type() {
		return refund_fee_type;
	}
	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}
	@XmlElement(name="op_user_id")
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
	@XmlElement(name="device_info")
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	@Override
	public String toString() {
		return "WechatRefund [appid=" + appid + ", mch_id=" + mch_id
				+ ", nonce_str=" + nonce_str + ", sign=" + sign
				+ ", transaction_id=" + transaction_id + ", out_trade_no="
				+ out_trade_no + ", total_fee=" + total_fee + ", refund_fee="
				+ refund_fee + ", refund_fee_type=" + refund_fee_type
				+ ", op_user_id=" + op_user_id + ", device_info=" + device_info
				+ "]";
	}
	
}
