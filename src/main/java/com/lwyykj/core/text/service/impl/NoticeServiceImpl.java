package com.lwyykj.core.text.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.text.Notice;
import com.lwyykj.core.bean.text.NoticeQuery;
import com.lwyykj.core.dao.text.NoticeDao;
import com.lwyykj.core.text.service.NoticeService;

@Service("noticeService")
@Transactional
public class NoticeServiceImpl implements NoticeService {
	@Resource
	private NoticeDao noticeDao;

	@Override
	public List<Notice> findAll() {
		NoticeQuery noticeQuery = new NoticeQuery();
		return noticeDao.selectByExample(noticeQuery);
	}

}
