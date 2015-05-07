package com.java1234.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.BaseDAO;
import com.java1234.entity.Comment;
import com.java1234.entity.PageBean;
import com.java1234.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	@Resource
	private BaseDAO<Comment> baseDAO;
	
	@Override
	public List<Comment> findCommentList(Comment s_comment, PageBean pageBean) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer hql = new StringBuffer("from Comment");
		hql.append(" order by createTime desc");
		if(pageBean!=null){
			return baseDAO.find(hql.toString(), param, pageBean);
		}else{
			
			return null;
		}
	}

	@Override
	public Long getCommentCount(Comment s_comment) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer hql = new StringBuffer("select count(*) from Comment");
		return baseDAO.count(hql.toString(), param);
	}

	@Override
	public void saveComment(Comment comment) {
		baseDAO.merge(comment);
		
		
	}

}
