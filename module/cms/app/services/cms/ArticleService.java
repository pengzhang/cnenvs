package services.cms;

import java.util.List;

import javax.inject.Named;

import models.cms.Article;
import models.cms.Comment;
import models.cms.Tag;
import models.cms.UserLike;
import models.cms.enumtype.Quality;
import models.cms.enumtype.Recommend;
import models.core.user.User;
import play.db.jpa.JPA;
import play.inject.BeanSource;

/**
 * 文章服务层
 * @author zp
 *
 */
public class ArticleService {
	
	/**
	 * 为文章添加评论
	 * @param id
	 * @param comment
	 * @param user
	 * @return
	 */
	public Comment addComment(long id, Comment comment, User user){
		return comment.addArticle(Article.findById(id)).addUser(user).save();
	}
	
	/**
	 * 为文章点赞
	 * @param article
	 * @param user
	 * @return
	 */
	public UserLike addLike(Article article, User user) {
		return new UserLike().addArticle(article).addUser(user).save();
	}
	
	/**
	 * 检查是否已点赞过
	 * @param article
	 * @param user
	 * @return
	 */
	public boolean checkUserLike(Article article, User user) {
		return UserLike.find("article=? and user=?", article, user).first() == null;
	}
	
	/**
	 * 记录文章浏览次数
	 * @param id
	 * @param view_total
	 */
	public void addView(long id, long view_total){
		JPA.em().createQuery("update Article a set a.view_total=:total where a.id=:id").setParameter("total", ++view_total).setParameter("id", id).executeUpdate();
	}
	
	/**
	 * 获取最新的文章
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Article> getNewestList(int page, int size) {
		return Article.find("status=? order by updateDate desc",false).fetch(page, size);
	}
	
	/**
	 * 获取最热(评论最多)的文章
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Article> articleByHot(int page, int size) {
		return Article.find("select a from Article a left join a.comments c where a.status=? group by a.id order by count(c.id) desc, a.updateDate desc",false).fetch(page, size);
	}
	
	/**
	 * 获取焦点(推荐和精华)文章
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Article> articleByFocus(int page, int size) {
		return Article.find("recommend=? and quality=? and status=? order by updateDate desc", Recommend.recommend, Quality.quality, false).fetch(page, size);
	}

	/**
	 * 根据分类获取文章
	 * @param categoryId
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Article> articleByCategoryList(long categoryId, int page, int size) {
		return Article.find("select a from Article a left join a.category c where c.id=? and a.status=? order by a.updateDate desc", categoryId, false).fetch(page, size);
	}
	
	/**
	 * 计算某个分类的文章数
	 * @param categoryId
	 * @return
	 */
	public long countArticleByCategory(long categoryId) {
		return Article.find("select count(a) from Article a left join a.category c where c.id=? and a.status=?", categoryId, false).first();
	}

	/**
	 * 根据标签获取文章
	 * @param tagId
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Article> articleByTagList(long tagId, int page, int size) {
		return Article.find("select a from Article a left join a.tags t where t.id=? and a.status=? order by a.updateDate desc", tagId, false).fetch(page, size);
	}
	
	/**
	 * 获取某文章的评论
	 * @param id
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Comment> getComments(long id, int page, int size){
		return Comment.find("select c from Article a left join a.comments c where a.id=? order by c.createDate desc", id).fetch(page, size);
	}
	
	/**
	 * 获取推荐的文章
	 * @param max
	 * @return
	 */
	public List<Article> getArticleRecommend(int max){
		return Article.find("recommend=? and status=?order by createDate desc", Recommend.recommend, false).fetch(max);
	}
	
	/**
	 * 获取文章的标签
	 * @param max
	 * @return
	 */
	public List<Tag> getArticleTags(int max){
		 return Tag.find("select t from Article a left join a.tags as t where a.status=? group by t.tag order by t.createDate desc", false).fetch(max);
	}
	
}
