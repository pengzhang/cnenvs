package models.mall;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import annotations.Exclude;
import annotations.Price;
import annotations.Upload;
import models.BaseModel;
import models.mall.enumtype.ProductType;
import play.data.validation.MaxSize;
import play.data.validation.Required;

@Entity
@Table(name="mall_product")
@org.hibernate.annotations.Table(comment="商品", appliesTo = "mall_product")
public class Product extends MallModel implements Serializable{

	//商品基本信息
	
	@Required(message="填写商品编号")
	@Column(columnDefinition = "varchar(100) comment '商品编号'")
	public String product_num = RandomStringUtils.randomNumeric(10);
	
	@Required(message="填写商品名称")
	@Column(columnDefinition = "varchar(100) comment '商品名称'")
	public String product_name;
	
	@MaxSize(value=50000)
	@Column(columnDefinition = "text comment '商品描述'")
	public String product_detail;
	
	@Upload
	@Column(columnDefinition = "varchar(5000) comment '商品图片(最多10张)'")
	public String product_image;
	
	
	//商品促销信息
	@MaxSize(value=2000)
	@Column(columnDefinition = "varchar(2000) comment '促销简介'")
	public String sale_desc;
	
	@Required(message="商品类型")
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(50) comment '商品类型:1-会员商品,2-普通商品'")
	public ProductType product_type;
	
	//物流信息
	@Column(columnDefinition = "varchar(255) comment '商品发货时间'")
	public Date product_delivery_time;
	
	//价格
	@Price
	@Required(message="填写商品价格")
	@Column(columnDefinition = "int comment '商品价格(以分为单位)'")
	public int product_price;
	
	@Column(columnDefinition = "int default 0 comment '销售数量'")
	public int sale_count;
	
	@ManyToOne()
	@JoinTable(name="mall_product_category", joinColumns=@JoinColumn(name = "category_id"), inverseJoinColumns=@JoinColumn(name = "product_id"))
	public ProductCategory category;
	
	@Exclude
	@OneToMany(cascade=CascadeType.ALL,mappedBy="product")
	@LazyCollection(value = LazyCollectionOption.EXTRA)
	public List<ProductComment> comments = new ArrayList<>();
	
	
	@ManyToMany(cascade=CascadeType.ALL)
	public List<ProductTag> tags = new ArrayList<>();

	@Override
	public String toString() {
		return product_name;
	}
}