package models.pay;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import annotations.Hidden;
import annotations.Price;
import models.BaseModel;
import models.core.user.User;
import play.db.jpa.Transactional;

@Entity
@Table(name="order_pay")
@org.hibernate.annotations.Table(comment="订单管理", appliesTo = "order_pay")
public class Order extends BaseModel{
	
	//支付信息
	
	@Column(nullable=false,columnDefinition="varchar(50) comment '商户订单号'")
	public String out_trade_no;
	
	@Column(columnDefinition="varchar(50) comment '银行流水号'")
	public String trade_no;
	
	@Price
	@Column(columnDefinition="int default 0 comment '金额'")
	public long total_fee;
	
	@Column(columnDefinition="varchar(20) comment '支付时间'")
	public String pay_date;
	
	@Column(columnDefinition="tinyint comment '支付方式:1_微信二维码,2:微信公众号,3_支付宝即时,4_支付手机网页'")
	public int trade_type;
	
	@Column(columnDefinition="tinyint comment '申请退款状态:0-假 ,1-真'")
	public boolean refund_status=false;
	
	@Column(columnDefinition="tinyint comment '退款状态:0-假 ,1-真'")
	public boolean isrefund=false;
	
	@Column(columnDefinition="varchar(50) comment '预支付订单号'")
	public String prepay_id;
	
	@Column(columnDefinition="bigint(20) comment '商品ID'")
	public long product_id;
	//购买人信息
	
	@Column(columnDefinition="varchar(100) comment '付款人'")
	public String openid;
	
	@Column(columnDefinition="varchar(100) comment '购买人姓名'")
	public String buyer_name;
	
	@Column(columnDefinition="varchar(100) comment '购买人电话'")
	public String buyer_mobile;
	
	@Column(columnDefinition="varchar(500) comment '购买人地址'")
	public String buyer_address;
	
	@OneToOne
	@JoinColumn(name="user_id", columnDefinition = "bigint comment '用户ID'")
	public User user;
	
	//订单信息
	@Column(columnDefinition="varchar(255) comment '订单名称'")
	public String subject;
	
	@Column(columnDefinition="varchar(255) comment '订单说明'")
	public String body;
	
	//物流快递信息
	@Column(columnDefinition="varchar(255) comment '快递单号'")
	public String express_no;
	
	//支付返回地址
	@Column(nullable=false,columnDefinition="varchar(2000) comment '商品展示地址'")
	public String show_url;
	
	@Column(nullable=false,columnDefinition="varchar(2000) comment '返回地址'")
	public String return_url;
	
	@Column(columnDefinition="varchar(2000) comment '通知地址'")
	public String notify_url;
	
	@Column(columnDefinition="varchar(50) default 0 comment '优惠码' ")
	public String discount_code;
	
	@Column(columnDefinition="int(32) default 0 comment '优惠金额' ")
	public int discount_fee;
	
	public Order() {
		String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		String serialNo = RandomStringUtils.randomNumeric(6);
		this.out_trade_no =  date  + serialNo;
	}
	
	public void statis() {
		String today = DateFormatUtils.format(new Date(), "yyyyMMdd");
		Order order = Order.find("out_trade_no", this.out_trade_no).first();
		OrderStatistics statis = OrderStatistics.find("pay_date", today).first();
		if(statis == null) {
			statis = new OrderStatistics();
		}
		statis.sum(order.trade_type, order.total_fee).save();
	}

}
