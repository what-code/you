package com.b5m.you.service;

import java.util.List;
import java.util.Map;

import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.IBaseService;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGuideNotes;

/**
 * Title:IYouGuideService.java
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
public interface IYouGuideNotesService extends IBaseService<YouGuideNotes> {

	public List<YouGuideNotes> findGuideNotes(YouSearchDto dto);

	public Map<String, String> findNotesCity();

	public List<YouGuideNotes> findGuideNotesBySearchResul(String ipLocate,
			String limit);

	public B5MPageList<YouGuideNotes> findGuideNotesBySearchResult(
			YouSearchDto dto);

	public YouGuideNotes findYouGuideNotesDetail(String notesId);
}
