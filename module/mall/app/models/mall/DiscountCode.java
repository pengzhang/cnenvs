package models.mall;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import models.BaseModel;

@Entity
@Table(name="user_discount")
@org.hibernate.annotations.Table(comment="用户折扣", appliesTo = "user_discount")
public class DiscountCode extends BaseModel implements Serializable {

	@Column(columnDefinition="bigint comment '折扣券ID'")
	public long did;
	
	@Column(columnDefinition="varchar(50) comment '优惠码'")
	public String discount_code;
	
	@Column(columnDefinition="int comment '折扣金额'")
	public int discount;
	
	@Column(columnDefinition="bigint comment '有效期'")
	public long expire_date;
	
	@Column(columnDefinition="tinyint comment '优惠券状态:0:正常, 1: 被占用, 2: 被使用'")
	public int dis_type=0;
	
	@Column(columnDefinition="bigint comment '使用者'")
	public long uid;

	public DiscountCode() {
	}

	public DiscountCode(String discount_code,Discount discount) {
		this.did = discount.id;
		this.discount_code = discount_code;
		this.discount = discount.discount;
		this.expire_date = discount.expire_date;
	}
	
	/**
	 * 占用该优惠码
	 * @param id
	 */
	public static void takeUpCode(long id){
		DiscountCode code = DiscountCode.findById(id);
		code.dis_type = 1;
		code.save();
	}
	
	/**
	 * 使用该优惠码
	 * @param id
	 */
	public static void useCode(long id, long uid){
		DiscountCode code = DiscountCode.findById(id);
		code.dis_type = 1;
		code.uid = uid;
		code.save();
	}
}
