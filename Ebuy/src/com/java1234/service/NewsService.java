package com.java1234.service;

import java.util.List;

import com.java1234.entity.News;

import com.java1234.entity.PageBean;

public interface NewsService {
	
	public List<News> findNewsList(News s_news,PageBean pageBean );
	
	public News getNewsById(int newsId);
}
