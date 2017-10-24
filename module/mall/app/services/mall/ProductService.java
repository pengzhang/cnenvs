package services.mall;

import java.util.List;

import models.core.user.User;
import models.mall.Product;
import models.mall.ProductComment;
import models.mall.ProductTag;
import play.db.jpa.JPA;

public class ProductService {
	
	/**
	 * 为商品添加评论
	 * @param id
	 * @param comment
	 * @param user
	 * @return
	 */
	public ProductComment addProductComment(long id, ProductComment comment, User user){
		return comment.addProduct(Product.findById(id)).addUser(user).save();
	}
	
	/**
	 * 记录商品浏览次数
	 * @param id
	 * @param view_total
	 */
	public void addView(long id, long view_total){
		JPA.em().createQuery("update Product a set p.view_total=:total where p.id=:id").setParameter("total", ++view_total).setParameter("id", id).executeUpdate();
	}
	
	
	/**
	 * 获取最新的商品列表
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> getNewestProducts(int page, int size){
		return Product.find("order by createDate desc").fetch(page, size);
	}
	
	/**
	 * 获取某个商品分类的商品
	 * @param cid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> getProductsByCategory(long cid, int page, int size){
		return Product.find("select p from Product p left join p.category c where c.id=? and p.status=? order by p.createDate desc", cid, false).fetch(page, size);
	}
	
	/**
	 * 获取某个分类价格最贵的商品
	 * @param cid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> getProductsByPriceDesc(long cid, int page, int size){
		return Product.find("select p from Product p left join p.category c where c.id=? and p.status=? order by p.createDate, p.product_price desc", cid, false).fetch(page, size);
	}
	
	/**
	 * 获取某个分类价格最低的商品
	 * @param cid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> getProductsByPriceAsc(long cid, int page, int size){
		return Product.find("select p from Product p left join p.category c where c.id=? and p.status=?  order by p.createDate desc, p.product_price asc", cid, false).fetch(page, size);
	}
	
	
	/**
	 * 根据标签获取商品
	 * @param tagId
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> articleByTagList(long tagId, int page, int size) {
		return Product.find("select a from Product a left join p.tags t where t.id=? and p.status=? order by p.updateDate desc", tagId, false).fetch(page, size);
	}
	
	/**
	 * 获取某商品的评论
	 * @param id
	 * @param page
	 * @param size
	 * @return
	 */
	public List<ProductComment> getComments(long id, int page, int size){
		return ProductComment.find("select c from Product a left join p.comments c where p.id=? order by c.createDate desc", id).fetch(page, size);
	}
	
	/**
	 * 获取商品的标签
	 * @param max
	 * @return
	 */
	public List<ProductTag> getProductTags(int max){
		 return ProductTag.find("select t from Product a left join p.tags as t where p.status=? group by t.tag order by t.createDate desc", false).fetch(max);
	}

}
