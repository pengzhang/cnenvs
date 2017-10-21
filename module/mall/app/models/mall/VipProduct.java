package models.mall;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import models.BaseModel;
import play.data.validation.Required;

@Entity
@Table(name="mall_vip_product")
@org.hibernate.annotations.Table(comment="一年会员专属商品商品", appliesTo = "mall_vip_product")
public class VipProduct extends BaseModel implements Serializable{

	
	@Required(message="会员价格")
	@Column(columnDefinition = "int(100) comment '会员价格'")
	public double vip_fee;
	
	@Required(message="会员有效期时间")
	@Column(columnDefinition = "int(100) comment '会员有效期时间'")
	public int vip_days;


	@Override
	public String toString() {
		return "会员费";
	}
	
	
}
