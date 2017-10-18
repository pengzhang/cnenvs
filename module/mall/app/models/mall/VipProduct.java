package models.mall;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import models.BaseModel;
import play.data.validation.Required;

@Entity
@Table(name="vip_product")
@org.hibernate.annotations.Table(comment="一年会员专属商品商品", appliesTo = "vip_product")
public class VipProduct extends BaseModel implements Serializable{

	//商品基本信息
	
	@Required(message="商品价格")
	@Column(columnDefinition = "int(100) comment '商品价格'")
	public double fee;
	
	@Required(message="会员有效期时间")
	@Column(columnDefinition = "int(100) comment '会员有效期时间'")
	public int days;

	public VipProduct(double fee, int days) {
		super();
		this.fee = fee;
		this.days = days;
	}

	@Override
	public String toString() {
		return "会员费";
	}
	
	
}
