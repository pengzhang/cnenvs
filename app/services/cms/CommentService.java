package services.cms;

import javax.inject.Named;

import models.cms.Comment;
import models.cms.UserLike;
import models.core.user.User;

/**
 * 评论
 * @author zp
 *
 */
@Named
public class CommentService {

	public UserLike addLike(long id, User user) {
		return new UserLike().addComment(Comment.findById(id)).addUser(user).save();
	}
}
