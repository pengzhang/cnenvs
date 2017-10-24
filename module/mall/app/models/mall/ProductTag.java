package models.mall;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import models.BaseModel;

@Entity
@Table(name="mall_product_tag")
@org.hibernate.annotations.Table(comment = "商品管理", appliesTo = "mall_product_tag")
public class ProductTag extends BaseModel {
	
	@Column(columnDefinition="varchar(255) comment '标签名称'")
	public String tag;
	
	@Column(columnDefinition="varchar(50) comment '标签类型'")
	public String color;
	
	@Override
	public String toString() {
		return tag;
	}
	
}
