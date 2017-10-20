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
@Table(name = "order_statistics")
@org.hibernate.annotations.Table(comment = "订单统计", appliesTo = "order_statistics")
public class OrderStatistics extends BaseModel {

	@Column(name = "pay_date", columnDefinition = "varchar(100) comment '支付日期'")
	public String pay_date;

	@Column(columnDefinition = "bigint comment '支付宝总数'")
	public long alipay;

	@Column(columnDefinition = "bigint comment '支付宝次数'")
	public long use_alipay;

	@Column(columnDefinition = "bigint comment '手机支付宝总数'")
	public long wapalipay;

	@Column(columnDefinition = "bigint comment '手机支付宝次数'")
	public long use_wapalipay;

	@Column(columnDefinition = "bigint comment '微信扫码总数'")
	public long weixin;

	@Column(columnDefinition = "bigint comment '微信扫码次数'")
	public long use_weixin;

	@Column(columnDefinition = "bigint comment '微信公众号总数'")
	public long wxclient;

	@Column(columnDefinition = "bigint comment '微信公众号次数'")
	public long use_wxclient;

	@Column(columnDefinition = "bigint comment '收款总数'")
	public long total;

	public OrderStatistics() {
		this.pay_date = DateFormatUtils.format(new Date(), "yyyyMMdd");
	}

	public OrderStatistics sum(int tradeType, long fee) {
		
		switch (tradeType) {
		case 1: {
			this.weixin += fee;
			this.use_weixin += 1;
			this.total += fee;
		}
			break;
		case 2: {
			this.wxclient += fee;
			this.use_wxclient += 1;
			this.total += fee;
		}
			break;
		case 3: {
			this.alipay += fee;
			this.use_alipay += 1;
			this.total += fee;
		}
			break;
		case 4: {
			this.wapalipay += fee;
			this.use_wapalipay += 1;
			this.total += fee;
		}
			break;
		}

		return this;
	}

}
