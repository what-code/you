package com.b5m.you.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.b5m.web.core.AbstractBaseService;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.Constants;
import com.b5m.you.common.util.XMemCachedUtil;
import com.b5m.you.dao.IYouGuideNotesDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGuideNotes;
import com.b5m.you.service.IYouGuideNotesService;

/**
 * Title:YouGuideServiceImpl.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-7-30
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Jia Liu
 * 
 * @version 1.0
 */
@Service
public class YouGuideNotesServiceImpl extends AbstractBaseService<YouGuideNotes> implements IYouGuideNotesService {

	public static Logger logger = Logger.getLogger(YouGuideNotesServiceImpl.class);

	@Resource
	private IYouGuideNotesDao guideNotesDao;

	@Override
	public List<YouGuideNotes> findGuideNotes(YouSearchDto dto) {
		String key = "findGuideNotes" + dto.getClikcId();
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_guide_notes_from--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);

				data = guideNotesDao.findGuideNotes(dto);

				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_guide_notes_from---90--######---");
				if (data == null) {
					logger.info("----you_guide_notes_from---90--?????????????????-->" + key);

					data = guideNotesDao.findGuideNotes(dto);

					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}

		}
		return (List<YouGuideNotes>) data;
	}

	@Override
	public Map<String, String> findNotesCity() {
		String key = "findGuideNotesByCiyts";
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_guide_notes_from--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);

				data = getMapCity();

				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_guide_notes_from---90--######---");
				if (data == null) {
					logger.info("----you_guide_notes_from---90--?????????????????-->" + key);

					data = getMapCity();

					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}

		}
		return (Map<String, String>) data;
	}

	@Override
	public List<YouGuideNotes> findGuideNotesBySearchResul(String ipLocate, String limit) {
		String key = "findGuideNotesBySearchResul_" + ipLocate + "_" + limit;
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_guide_notes_from--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);

				data = guideNotesDao.findGuideNotesBySearchResul(ipLocate, limit);

				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_guide_notes_from---90--######---");
				if (data == null) {
					logger.info("----you_guide_notes_from---90--?????????????????-->" + key);

					data = guideNotesDao.findGuideNotesBySearchResul(ipLocate, limit);

					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}

		}
		return (List<YouGuideNotes>) data;
	}

	@Override
	public B5MPageList<YouGuideNotes> findGuideNotesBySearchResult(YouSearchDto dto) {
		B5MPageList<YouGuideNotes> list = findGuideNotesBySearchResulNew(dto);
		//对超过总页数的处理
		if(list.getTotalPages() > 0 && dto.getCurrPageNo() > list.getTotalPages()){
			dto.setCurrPageNo(list.getTotalPages());
			return findGuideNotesBySearchResult(dto);
		}
		return list;
	}
	
	
	public B5MPageList<YouGuideNotes> findGuideNotesBySearchResulNew(YouSearchDto dto) {
		String key = "findGuideNotesBySearchResult_" + getMemCachedKey(dto);
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		//TODO delete------------------
		//data = null;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			//TODO delete-------------------
			//flag = true;
			if (flag) {
				logger.info("----you_guide_notes_from--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);
				data = guideNotesDao.findGuideNotesBySearchResult(dto);
				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_guide_notes_from---90--######---");
				if (data == null) {
					logger.info("----you_guide_notes_from---90--?????????????????-->" + key);
					data = guideNotesDao.findGuideNotesBySearchResult(dto);
					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}

		}
		return (B5MPageList<YouGuideNotes>) data;
	}

	@Override
	public YouGuideNotes findYouGuideNotesDetail(String notesId) {
		String key = "findYouGuideNotesDetail" + notesId;
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_guide_notes_from--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);

				data = guideNotesDao.findYouGuideNotesDetail(notesId);

				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_guide_notes_from---90--######---");
				if (data == null) {
					logger.info("----you_guide_notes_from---90--?????????????????-->" + key);

					data = guideNotesDao.findYouGuideNotesDetail(notesId);

					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}

		}
		return (YouGuideNotes) data;
	}

	/**
	 * 获取城市集合
	 * 
	 * @param ttDto
	 * @return
	 */
	private LinkedHashMap<String, String> getMapCity() {
		LinkedHashMap<String, String> youMap = new LinkedHashMap<String, String>();
		List<String> list = guideNotesDao.findNotesCity();

		List<Integer> listKey = new ArrayList<Integer>();
		for (String string : list) {
			String cityKey = Constants.XML_CITY_KEY.get(string);
			if (cityKey != null && !("").equals(cityKey)) {
				listKey.add(Integer.valueOf(cityKey));
			}
		}
		Collections.sort(listKey);
		for (Integer keys : listKey) {
			String cityKey = Constants.XML_CITY.get(keys.toString());
			if (cityKey != null && !("").equals(cityKey)) {
				youMap.put(keys.toString(), cityKey);
			}
		}
		return youMap;
	}

	/**
	 * 获取缓存的key
	 * 
	 * @param ttDto
	 * @return
	 */
	private String getMemCachedKey(YouSearchDto ttDto) {
		StringBuffer key = new StringBuffer();
		if (ttDto != null) {
			key = new StringBuffer("b5m_you").append(ttDto.getPageType()).append(ttDto.getAjaxType()).append(ttDto.getKeyWords().replaceAll(" ", ""))
					.append(ttDto.getCurrPageNo()).append(ttDto.getClikcId()).append(ttDto.getShowUrl()).append(ttDto.getUrl())
					.append(ttDto.getPageSize()).append(ttDto.getIpLocate()).append(ttDto.getCity()).append(ttDto.getOrder()).append(ttDto.getSelectedCityId());
		} else {
			return "YOU_CACHE_KEY_IS_NULL_";
		}
		return key.toString();
	}
}
