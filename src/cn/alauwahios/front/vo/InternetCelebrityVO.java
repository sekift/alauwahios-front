package cn.alauwahios.front.vo;

import java.util.Date;

/**
 * 网红表
 * @author sekift
 *
 */
public class InternetCelebrityVO {
	private int id;// `id` int(11) NOT NULL COMMENT '递增',
	private String authorId;//`authorId` varchar(50) DEFAULT NULL COMMENT '网红id',
	private String authorName;// `authorName` varchar(100) DEFAULT NULL COMMENT '网红名称',
	private String authorLink;// `authorLink` varchar(500) DEFAULT NULL COMMENT '网红链接',
	private String shortLink;// `shortLink` varchar(100) DEFAULT '' COMMENT '短链接',
	private String content;// `content` varchar(500) DEFAULT NULL COMMENT '简介',
	private int type;// `type` int(11) DEFAULT NULL COMMENT '网红来源',
	private Date createTime;// `createTime` datetime NOT NULL COMMENT '抓取时间',
	private Date updateTime;// `updateTime` datetime NOT NULL COMMENT '更新时间',
	private int status;// `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态：1 可用 0 不可用。',
	private int star;// `star` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
	private int sort;// `sort` int(11) NOT NULL DEFAULT '1' COMMENT '人工排序，默认为1',
	private int hot;// `hot` int(11) NOT NULL DEFAULT '1' COMMENT '热度',
	private int visits;// `visits` int(11) NOT NULL DEFAULT '0' COMMENT '访问次数',
	private String remark;//`remark` varchar(500) DEFAULT '' COMMENT '备用字段',
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorLink() {
		return authorLink;
	}
	public void setAuthorLink(String authorLink) {
		this.authorLink = authorLink;
	}
	public String getShortLink() {
		return shortLink;
	}
	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return "InternetCelebrityVO [id=" + id
				+",authorId="+authorId
				+",authorName="+authorName
				+",authorLink="+authorLink
				+",shortLink="+shortLink
				+",content="+content
				+",type="+type
				+",createTime="+createTime
				+",updateTime="+updateTime
				+",status="+status
				+",star="+star
				+",sort="+sort
				+",hot="+hot
				+",visits="+visits
				+",remark="+remark
				+"]";
	}

}
