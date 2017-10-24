package models.mall;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import annotations.Hidden;
import models.BaseModel;
/**
 * Mall Model
 * @author zp
 *
 */
@MappedSuperclass
public class MallModel extends BaseModel {

	//计数部分
	@Hidden
	@Column(columnDefinition = "bigint comment '浏览总数'")
	public long view_total = 0;  //浏览总数

	@Hidden
	@Column(columnDefinition = "bigint comment '评论总数'")
	public long comment_total = 0; //评论总数

	
}
