package controllers.wechat.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class WechatRedpack implements Serializable{
	/**
	 * 随机字符串，不长于32位。
		微信支付API接口协议中包含字段nonce_str，主要保证签名不可预测。我们推荐生成随机数算法如下：调用随机数函数生成，将得到的值转换为字符串。
		---必须参数 
	 */
	private String nonce_str;
	//	订单号
	private String mch_billno;
	private String mch_id;
	private String wxappid;
	private String nick_name;
	private String send_name;
	private String re_openid;
	private int total_amount;
	private String min_value;
	private String max_value;
	private int total_num;
	private String wishing;
	private String client_ip;
	private String act_name;
	private String remark;
	private String sign;
	
	@XmlElement(name="sign")
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@XmlElement(name="nonce_str")
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	@XmlElement(name="mch_billno")
	public String getMch_billno() {
		return mch_billno;
	}
	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}
	
	@XmlElement(name="mch_id")
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	@XmlElement(name="wxappid")
	public String getWxappid() {
		return wxappid;
	}
	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}
	@XmlElement(name="nick_name")
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	@XmlElement(name="send_name")
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	@XmlElement(name="re_openid")
	public String getRe_openid() {
		return re_openid;
	}
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}
	@XmlElement(name="total_amount")
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	@XmlElement(name="min_value")
	public String getMin_value() {
		return min_value;
	}
	public void setMin_value(String min_value) {
		this.min_value = min_value;
	}
	@XmlElement(name="max_value")
	public String getMax_value() {
		return max_value;
	}
	public void setMax_value(String max_value) {
		this.max_value = max_value;
	}
	@XmlElement(name="total_num")
	public int getTotal_num() {
		return total_num;
	}
	public void setTotal_num(int i) {
		this.total_num = i;
	}
	@XmlElement(name="wishing")
	public String getWishing() {
		return wishing;
	}
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	@XmlElement(name="client_ip")
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	@XmlElement(name="act_name")
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	@XmlElement(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
