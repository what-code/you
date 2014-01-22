package com.b5m.you.dao;

import java.util.List;

import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.IBaseDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGuideNotes;

/**
 * Title:IYouGuideDao.java
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
public interface IYouGuideNotesDao extends IBaseDao<YouGuideNotes> {

	public List<YouGuideNotes> findGuideNotes(YouSearchDto dto);

	public List<YouGuideNotes> findGuideNotesBySearchResul(String ipLocate, String limit);

	public B5MPageList<YouGuideNotes> findGuideNotesBySearchResult(YouSearchDto dto);

	public List<String> findNotesCity();

	public YouGuideNotes findYouGuideNotesDetail(String notesId);
}
