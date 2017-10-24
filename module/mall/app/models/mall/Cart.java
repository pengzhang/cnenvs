package models.mall;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import models.BaseModel;
import models.core.user.User;

@Entity
@Table(name="mall_cart")
@org.hibernate.annotations.Table(comment="购物车", appliesTo = "mall_cart")
public class Cart extends BaseModel implements Serializable{
	
	@OneToOne
	@JoinColumn(name="product_id", columnDefinition="bigint default 0 comment '商品'")
	public Product product;
	
	@Column(columnDefinition="int default 0 comment '购买数量'")
	public int num;
	
	@OneToOne
	@JoinColumn(name="user_id", columnDefinition="bigint default 0 comment '用户'")
	public User user;

	public Cart() {
	}
	
	public Cart(Product product, int num, User user) {
		super();
		this.product = product;
		this.num = num;
		this.user = user;
	}
	
	
	
}
