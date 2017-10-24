package services.mall;

import java.util.List;

import models.core.user.User;
import models.mall.Cart;
import models.mall.Product;

public class CartService {
	
	
	/**
	 * 获取购物车的所有记录
	 * @param uid
	 * @return
	 */
	public List<Cart> getMyCart(User user){
		return Cart.find("user.id=?", user.id).fetch();
	}
	
	/**
	 * 计算购物车的总费用
	 * @param uid
	 * @return
	 */
	public long getMyCartTotalFee(User user) {
		List<Cart> carts = getMyCart(user);
		long total_fee = 0L;
		for(Cart cart: carts) {
			total_fee += cart.product.product_price*cart.num;
		}
		return total_fee;
	}
	
	/**
	 * 添加商品到购物车
	 * @param uid
	 * @param pid
	 * @param num
	 */
	public void addProductToCart(User user, long pid, int num) {
		Cart cart = getCart(user, pid);
		if(cart == null) {
			Product product = Product.findById(pid);
			new Cart(product, num, user).save();
		}else {
			cart.num += num;
			cart.save();
		}
	}
	
	/**
	 * 减少购物车某商品的数量
	 * @param uid
	 * @param pid
	 * @param num
	 */
	public void subProductToCart(User user, long pid, int num) {
		Cart cart = getCart(user, pid);
		if(cart != null) {
			cart.num -= num;
			if(cart.num <1) {
				cart.num = 1;
			}
			cart.save();
		}
	}
	
	/**
	 * 从购物车中删除某个商品
	 * @param uid
	 * @param pid
	 */
	public void delProductFromCart(User user, long pid) {
		Cart.delete("user.id=? and product.id=?", user.id,pid);
	}
	
	
	/**
	 * 获取购物车中的某个商品
	 * @param uid
	 * @param pid
	 * @return
	 */
	public Cart getCart(User user, long pid) {
		return Cart.find("product.id=? and user.id=?", pid, user.id).first();
	}

}
