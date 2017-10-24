package models.mall;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import models.BaseModel;

@Entity
@Table(name="mall_category")
@org.hibernate.annotations.Table(comment = "产品分类管理", appliesTo = "mall_category")
public class ProductCategory extends BaseModel {
	
	@OneToOne
	@JoinColumn(name="pid", columnDefinition="bigint default 0 comment '父ID'")
	public ProductCategory p_parentCategory;
	
	@Column(columnDefinition="varchar(255) comment '分类名称'")
	public String p_category;
	
	@Column(name="category_type", columnDefinition="varchar(50) comment '分类类型'")
	public int p_categoryType = 0;
	
	@Override
	public String toString() {
		return p_category;
	}
	
}
