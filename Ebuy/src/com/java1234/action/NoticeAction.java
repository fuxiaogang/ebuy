package com.java1234.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.java1234.entity.Notice;
import com.java1234.service.NoticeService;
import com.java1234.util.NavUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class NoticeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private NoticeService noticeService;
	
	private Notice notice;
	private int noticeId;
	private String mainPage;
	private String navCode;
	
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getMainPage() {
		return mainPage;
	}
	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	public String getNavCode() {
		return navCode;
	}
	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}
	
	public String showNotice()throws Exception{
		notice = noticeService.getNoticeById(noticeId);
		mainPage="notice/noticeDetails.jsp";
		navCode = NavUtil.genNavCode("公告信息");
		return SUCCESS;
	}
	
}
