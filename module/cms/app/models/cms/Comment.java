package models.cms;

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
@Table(name="cms_comment")
@org.hibernate.annotations.Table(comment = "评论管理", appliesTo = "cms_comment")
public class Comment extends BaseModel implements Serializable{
	
	@MaxSize(value=500)
	@Column(columnDefinition="varchar(1000) comment '评论内容'")
	public String comment;
	
	@OneToOne
	@JoinColumn(name="user_id", columnDefinition="bigint default 0 comment '评论用户'")
	public User user;
	
	@ManyToOne
	@JoinTable(name="cms_article_comment", joinColumns=@JoinColumn(name = "comment_id"), inverseJoinColumns=@JoinColumn(name = "article_id"))
	public Article article;
	
	@ManyToOne
	@JoinTable(name="cms_video_comment", joinColumns=@JoinColumn(name = "comment_id"), inverseJoinColumns=@JoinColumn(name = "video_id"))
	public Video video;
	
	@Exclude
	@OneToMany(cascade=CascadeType.ALL,mappedBy="comment")
	@LazyCollection(value = LazyCollectionOption.EXTRA)
	public List<UserLike> likes = new ArrayList<>();
	
	public Comment addArticle(Article article) {
		this.article = article;
		return this;
	}
	
	public Comment addVideo(Video video) {
		this.video = video;
		return this;
	}
	
	public Comment addUser(User user) {
		this.user = user;
		return this;
	}

	@Override
	public String toString() {
		return StringUtils.substring(comment, 0, 10);
	}
	
}
