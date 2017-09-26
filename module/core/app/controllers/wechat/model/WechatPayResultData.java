package controllers.wechat.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class WechatPayResultData implements Serializable{
	/*
	 * {"is_subscribe":"Y",
	 * "appid":"wx7d361d5180160f41","fee_type":"CNY",
	 * "nonce_str":"qxg7k1f0bcgfxzvqw9ajdirazn9uvd9h",
	 * "out_trade_no":"0011601071085022",
	 * "transaction_id":"1006570754201601072556291304",
	 * "trade_type":"NATIVE","result_code":"SUCCESS",
	 * "sign":"F99AB1F025A8747C8E03597D1942EFE7",
	 * "mch_id":"1249501201","total_fee":"1",
	 * "time_end":"20160107123148",
	 * "openid":"o5JLtt1tiUczvQmIZAC4EcnByC1k",
	 * "bank_type":"CFT",
	 * "return_code":"SUCCESS",
	 * "cash_fee":"1"}
	 */
	private String is_subscribe;
	private String appid;
	private String fee_type;
	private String nonce_str;
	private String out_trade_no;
	private String transaction_id;
	private String trade_type;
	private String sign;
	private String result_code;
	private String mch_id;
	private String total_fee;
	private String time_end;
	private String openid;
	private String bank_type;
	private String return_code;
	private String cash_fee;
	
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}
	
	
}
