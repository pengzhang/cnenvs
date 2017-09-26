package controllers.wechat.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import exceptions.WechatPayException;

/**
 * 微信订单基本类
 * 
 * @author think
 * 
 */
@XmlRootElement(name = "xml")
public class WechatPayData implements Serializable {

	// 1) 微信同意订单生成接口

	/**
	 * 签名，必须字段
	 * 第一步，设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），使用URL键值对的格式
	 * （即key1=value1&key2=value2…）拼接成字符串stringA。 特别注意以下重要规则： ◆
	 * 参数名ASCII码从小到大排序（字典序）； ◆ 如果参数的值为空不参与签名； ◆ 参数名区分大小写； ◆
	 * 验证调用返回或微信主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。 ◆
	 * 微信接口可能增加字段，验证签名时必须支持增加的扩展字段
	 * 
	 * 第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，
	 * 再将得到的字符串所有字符转换为大写，得到sign值signValue。
	 * 
	 * key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
	 * 具体看https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_3 有详细说明
	 * ----必须参数String(32)
	 * **/
	private String sign;
	/**
	 * 
	 * 微信分配的公众账号ID（企业号corpid即为此appId）---必须参数String(32)
	 * **/
	private String appid;
	/**
	 * 微信支付分配的商户号 --- 必须参数
	 */
	private String mch_id;
	/**
	 * 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" ---非必须
	 */
	private String device_info;
	/**
	 * 支付类型 JSAPI NATIVE
	 */
	private String fee_type;
	/**
	 * 订单总金额，单位为分--必须参数
	 */
	private long total_fee;

	private String openid;
	
	private String product_id;

	/**
	 * APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。---必须参数
	 */
	private String spbill_create_ip;
	/**
	 * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。---必须参数
	 */
	private String notify_url;
	/**
	 * 取值如下：JSAPI，NATIVE，APP---必须参数
	 */
	private String trade_type;
	/**
	 * 随机字符串，不长于32位。
	 * 微信支付API接口协议中包含字段nonce_str，主要保证签名不可预测。我们推荐生成随机数算法如下：调用随机数函数生成，将得到的值转换为字符串。
	 * ---必须参数
	 */
	private String nonce_str;
	/**
	 * String(128) 商品或支付单简要描述 ---必须参数
	 */
	private String body;
	/**
	 * 商品名称明细列表--非必须
	 */
	private String detail;
	/**
	 * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据---非必须参数
	 */
	private String attach;
	/**
	 * 商户系统内部的订单号,32个字符内、可包含字母,这里可以用我们的订单号--必须参数
	 */
	private String out_trade_no;
	/**
	 * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。--非必须
	 */
	private String time_start;
	/**
	 * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
	 * 注意：最短失效时间间隔必须大于5分钟--非必须
	 */
	private String time_expire;
	/**
	 * 商品标记，代金券或立减优惠功能的参数---非必须
	 */
	private String goods_tag;
	/**
	 * no_credit--指定不能使用信用卡支付---非必须
	 */
	private String limit_pay;

	@XmlElement(name = "sign")
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@XmlElement(name = "appid")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	@XmlElement(name = "mch_id")
	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	@XmlElement(name = "device_info")
	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	@XmlElement(name = "fee_type")
	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	@XmlElement(name = "total_fee")
	public long getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(long total_fee) {
		this.total_fee = total_fee;
	}

	@XmlElement(name = "spbill_create_ip")
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	@XmlElement(name = "notify_url")
	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	@XmlElement(name = "trade_type")
	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	@XmlElement(name = "nonce_str")
	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	@XmlElement(name ="body")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@XmlElement(name = "detail")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@XmlElement(name = "attach")
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	@XmlElement(name = "out_trade_no")
	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	@XmlElement(name = "time_start")
	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	@XmlElement(name = "time_expire")
	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	@XmlElement(name = "goods_tag")
	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	@XmlElement(name = "limit_pay")
	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}
	
	@XmlElement(name = "openid")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@XmlElement(name = "product_id")
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "WechatPayDatabase [sign=" + sign + ", appid=" + appid
				+ ", mch_id=" + mch_id + ", device_info=" + device_info
				+ ", fee_type=" + fee_type + ", total_fee=" + total_fee
				+ ", openid=" + openid + ", product_id=" + product_id
				+ ", spbill_create_ip=" + spbill_create_ip + ", notify_url="
				+ notify_url + ", trade_type=" + trade_type + ", nonce_str="
				+ nonce_str + ", body=" + body + ", detail=" + detail
				+ ", attach=" + attach + ", out_trade_no=" + out_trade_no
				+ ", time_start=" + time_start + ", time_expire=" + time_expire
				+ ", goods_tag=" + goods_tag + ", limit_pay=" + limit_pay + "]";
	}

	public boolean checkAttribute(WechatPayData data) throws WechatPayException {
		boolean result = true;
		if (!data.checkTrade_type(data)) {
			result = false;
			return result;
		}
		// 检验对象必要条件是否齐全
		switch (data.getTrade_type()) {
		case "NATIVE":
			if (!data.checkProduct_id(data)) {
				result = false;
				throw new WechatPayException("缺少必须的product_id");
			}
			break;
		case "JSAPI":
			if (!data.checkAppid(data)) {
				result = false;
				throw new WechatPayException("缺少必须的openid");
			}

			break;
		default:
			break;
		}
		if (!data.checkMch_id(data)) {
			result = false;
			throw new WechatPayException("缺少必须的mch_id");
		}
		if (!data.checkNonce_str(data)) {
			result = false;
			throw new WechatPayException("缺少必须的nonce_str");
		}
		if (!data.checkNotify_url(data)) {
			result = false;
			throw new WechatPayException("缺少必须的notify_url");
		}
		if (!data.checkTotal_fee(data)) {
			result = false;
			throw new WechatPayException("缺少必须的total_fee");
		}
		if (!data.checkSign(data)) {
			result = false;
			throw new WechatPayException("缺少必须的sign");
		}
		if (!data.checkSpbill_create_ip(data)) {
			result = false;
			throw new WechatPayException("缺少必须的spbill_create_ip");
		}
		return result;
	}

	// 检查openid是否为空，fee_type=jsapi时必须属性
	public boolean checkOpenid(WechatPayData data) {
		if (data.getOpenid() == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkSign(WechatPayData data) {
		if (data.getSign() == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkNonce_str(WechatPayData data) {
		if (data.getNonce_str() == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkMch_id(WechatPayData data) {
		if (data.getMch_id() == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkTotal_fee(WechatPayData data) {
		if (data.getTotal_fee() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkProduct_id(WechatPayData data) {
		if (data.getProduct_id() == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkAppid(WechatPayData data) {
		if (data.getAppid() == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkOut_trade_no(WechatPayData data) {
		if (data.getOut_trade_no() == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkSpbill_create_ip(WechatPayData data) {
		if (data.getSpbill_create_ip() == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkNotify_url(WechatPayData data) {
		if (data.getNotify_url() == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkTrade_type(WechatPayData data) {

		if (data.getTrade_type() == null) {
			return false;
		} else {
			return true;
		}
	}
}
