package com.java1234.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.java1234.entity.Comment;
import com.java1234.entity.PageBean;
import com.java1234.service.CommentService;
import com.java1234.util.PageUtil;
import com.java1234.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class CommentAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	@Resource
	private CommentService commentService;
	
	private List<Comment> commentList;
	
	private String page;
	private Long total;
	private String pageCode;
	
	private Comment s_Comment;
	private Comment comment;
	
	public List<Comment> getCommentList() {
		return commentList;
	}



	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}



	public String getPage() {
		return page;
	}



	public void setPage(String page) {
		this.page = page;
	}



	public Long getTotal() {
		return total;
	}



	public void setTotal(Long total) {
		this.total = total;
	}





	
	public String getPageCode() {
		return pageCode;
	}



	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}



	public Comment getS_Comment() {
		return s_Comment;
	}



	public void setS_Comment(Comment s_Comment) {
		this.s_Comment = s_Comment;
	}



	public Comment getComment() {
		return comment;
	}



	public void setComment(Comment comment) {
		this.comment = comment;
	}



	public String list()throws Exception{
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean = new  PageBean(Integer.parseInt(page), 3);
		commentList = commentService.findCommentList(s_Comment, pageBean);
		total = commentService.getCommentCount(s_Comment);
		pageCode = PageUtil.genPaginationNoParam(request.getContextPath()+"/comment_list.action", total, Integer.parseInt(page), 3);
		return SUCCESS;		
	}

	public String save()throws Exception{
		if(comment.getCreateTime()==null){
			comment.setCreateTime(new Date());
		}
		commentService.saveComment(comment);
		return "save";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

}
