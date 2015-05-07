package com.java1234.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.BaseDAO;
import com.java1234.entity.Notice;
import com.java1234.entity.PageBean;

import com.java1234.service.NoticeService;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	
	@Resource
	private BaseDAO<Notice> baseDAO;
	
	@Override
	public List<Notice> findNoticeList(Notice s_notice, PageBean pageBean) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer hql = new StringBuffer("from Notice");
		if(pageBean!=null){
			return baseDAO.find(hql.toString(), param, pageBean);
		}else{
			
			return null;
		}
	}

	@Override
	public Notice getNoticeById(int noticeId) {
		return baseDAO.get(Notice.class, noticeId);
	}

}
