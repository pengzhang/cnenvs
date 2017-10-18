package models.mall;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import models.BaseModel;

@Entity
@Table(name="discount")
@org.hibernate.annotations.Table(comment="折扣管理", appliesTo = "discount")
public class Discount extends BaseModel implements Serializable{
	
	@Column(columnDefinition="varchar(255) comment '口令'")
	public String diskey;
	
	@Column(columnDefinition="int comment '折扣金额'")
	public int discount;
	
	@Column(columnDefinition="int comment '生成数量'")
	public int generate_num;
	
	@Column(columnDefinition="bigint comment '有效期'")
	public long expire_date;

	public Discount() {
	}

	public Discount(String diskey, int discount, int generate_num, long expire_date) {
		super();
		this.diskey = diskey;
		this.discount = discount;
		this.generate_num = generate_num;
		this.expire_date = expire_date;
	}

	@Override
	public String toString() {
		return "折扣券";
	}

}
