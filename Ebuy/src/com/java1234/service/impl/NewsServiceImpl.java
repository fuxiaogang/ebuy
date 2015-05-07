package com.java1234.service.impl;


import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.BaseDAO;
import com.java1234.entity.News;
import com.java1234.entity.PageBean;
import com.java1234.service.NewsService;


@Service("newsService")
public class NewsServiceImpl implements NewsService{
	
	@Resource
	private BaseDAO<News> baseDAO;
	

	@Override
	public List<News> findNewsList(News s_news, PageBean pageBean) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer hql = new StringBuffer("from News");
		if(pageBean!=null){
			return baseDAO.find(hql.toString(), param, pageBean);
		}else{
			
			return null;
		}
	}


	@Override
	public News getNewsById(int newsId) {
		return baseDAO.get(News.class, newsId);
	}

}
