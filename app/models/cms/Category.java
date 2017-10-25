package models.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import models.BaseModel;
import models.cms.enumtype.ModelType;

@Entity
@Table(name="cms_category")
@org.hibernate.annotations.Table(comment = "文章分类管理", appliesTo = "cms_category")
public class Category extends BaseModel {
	
	@OneToOne
	@JoinColumn(name="pid", columnDefinition="bigint default 0 comment '父ID'")
	public Category parentCategory;
	
	@Column(columnDefinition="varchar(255) comment '分类名称'")
	public String category;
	
	@Column(name="category_type", columnDefinition="varchar(50) comment '分类类型'")
	public int categoryType = 0;
	
	@Enumerated(EnumType.STRING)
	@Column(name="model_type",columnDefinition = "varchar(50) comment '模型类型'")
	public ModelType modelType;
	
	@Override
	public String toString() {
		return category;
	}
	
}
