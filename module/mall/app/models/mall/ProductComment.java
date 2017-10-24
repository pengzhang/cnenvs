package models.mall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import annotations.Exclude;
import models.BaseModel;
import models.core.user.User;
import play.data.validation.MaxSize;
/**
 * 评论
 * @author zp
 *
 */
@Entity
@Table(name="mall_product_comment")
@org.hibernate.annotations.Table(comment = "评论管理", appliesTo = "cms_product_comment")
public class ProductComment extends BaseModel implements Serializable{
	
	@MaxSize(value=500)
	@Column(columnDefinition="varchar(1000) comment '评论内容'")
	public String comment;
	
	@OneToOne
	@JoinColumn(name="user_id", columnDefinition="bigint default 0 comment '评论用户'")
	public User user;
	
	@ManyToOne
	@JoinTable(name="mall_product_comment", joinColumns=@JoinColumn(name = "comment_id"), inverseJoinColumns=@JoinColumn(name = "product_id"))
	public Product product;
	
	
	public ProductComment addProduct(Product product) {
		this.product = product;
		return this;
	}
	
	public ProductComment addUser(User user) {
		this.user = user;
		return this;
	}

	@Override
	public String toString() {
		return StringUtils.substring(comment, 0, 10);
	}
	
}
