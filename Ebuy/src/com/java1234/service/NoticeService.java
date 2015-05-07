package com.java1234.service;

import java.util.List;


import com.java1234.entity.Notice;
import com.java1234.entity.PageBean;

public interface NoticeService {
	
	public List<Notice> findNoticeList(Notice s_notice,PageBean pageBean );
	
	public Notice getNoticeById(int noticeId);
}
