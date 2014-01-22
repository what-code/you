package com.b5m.you.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.b5m.web.core.AbstractBaseController;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.Constants;
import com.b5m.you.common.DefaultParameterUtil;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.HotelKeyWords;
import com.b5m.you.model.HotelMapKeyWord;
import com.b5m.you.model.YouGoods;
import com.b5m.you.model.YouGuide;
import com.b5m.you.model.YouGuideCircle;
import com.b5m.you.model.YouGuideNotes;
import com.b5m.you.model.YouHotel;
import com.b5m.you.model.YouHotelDetail;
import com.b5m.you.model.YouKeyWords;
import com.b5m.you.model.YouTopic;
import com.b5m.you.model.YouTopicBar;
import com.b5m.you.model.YouTopicMain;
import com.b5m.you.service.IYouGoodsService;
import com.b5m.you.service.IYouGuideCircleService;
import com.b5m.you.service.IYouGuideNotesService;
import com.b5m.you.service.IYouGuideService;
import com.b5m.you.service.IYouHotelDetailService;
import com.b5m.you.service.IYouHotelService;
import com.b5m.you.service.IYouKeyWordsService;
import com.b5m.you.service.IYouSourceService;
import com.b5m.you.service.IYouTopicService;
import com.google.gson.Gson;

@Controller
public class CoreController_Old extends AbstractBaseController {
	@Resource(name = "taoGoodsServiceRaindrop")
	private IYouGoodsService goodsService;

	@Resource
	private IYouTopicService taoTopicService;

	@Resource
	private IYouSourceService taoSourceService;

	@Resource(name = "hotelServiceRaindrop")
	private IYouHotelService hotelService;

	@Resource
	private IYouHotelDetailService hotelDetailService;

	@Resource
	private IYouKeyWordsService keywordsService;

	@Resource
	private IYouGuideService guideService;

	@Resource
	private IYouGuideCircleService guideCircleService;

	@Resource
	private IYouGuideNotesService guideNotesService;

	// 用户中心接口地址
	public @Value("#{configProp[uc_server]}")
	String ucServer;
	
	// autofill接口地址
	public @Value("#{configProp[autofill_server]}")
	String autofillServer;
	
	public static Logger logger = Logger.getLogger(CoreController_Old.class);

	/*******************************************************************/
	/**
	 * 所有页面 对点击的商品的抢购数加1
	 * 
	 */
	@RequestMapping("/youTotalClick")
	public void youTotalClick(YouSearchDto dto) {
		if (dto.getClikcId() != null) {
			goodsService.totalClick(dto);
			logger.info("-----youTotalClick001-----");
		}
	}

	/**
	 * 所有页面 对点击的商品的抢购数加1
	 * 
	 */
	@RequestMapping("/hotelTotalClick")
	public void hotelTotalClick(YouSearchDto dto) {
		if (dto.getClikcId() != null) {
			hotelService.totalClick(dto);
			logger.info("-----hotelTotalClick001-----");
		}
	}

	/********************************* 错误页面s **********************************/
	/**
	 * 无结果集页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/taoError")
	public String taoError(YouSearchDto searchDto, Model model) {
		logger.info("----youError page001-----");
		model.addAttribute("hotList", ttjHotList(searchDto));
		// 数据源
		Map<String, String> dataSource = taoSourceService.findTaoSource();
		model.addAttribute("dataSource", dataSource);

		// keywords
		List<YouKeyWords> dataKeyWords = keywordsService.findKeyWords();
		model.addAttribute("keyWords", dataKeyWords);

		model.addAttribute("loginAndRegisterURL", ucServer);
		model.addAttribute("curPageTag", "noResult");
		return "noresult";
	}

	/**
	 * 搜索无结果页下的（默认12条数据）
	 * 
	 * @return 最人气页面
	 */
	public List ttjHotList(YouSearchDto searchDto) {
		//酒店搜索无结果页推荐
		if (Constants.HOTEL_SEARCH.equals(searchDto.getAjaxType()) || "hotelSearchresult".equals(searchDto.getAjaxType())) {
			//searchDto
			return hotelService.findsIndexHotelByList(searchDto);
		}
		return goodsService.getTaoGoods(12);
	}

	/********************************* 错误页面e **********************************/

	/**************************** 页面主数据s **********************************/
	/**
	 * 分页数据
	 * 
	 */
	@RequestMapping("/taoPage")
	public String findTaoGoodByPage(YouSearchDto searchDto, Model model,HttpServletRequest request) {
		String pageSize = "-1";
		model.addAttribute("autofillServer", autofillServer);
		model.addAttribute("dataCityKeyId", Constants.XML_CITY);
		//去掉搜索关键字中所有的空格
		//searchDto.setKeyWords(searchDto.getKeyWords().replaceAll(" ", ""));
		//酒店搜索：INDEX_HOTEL_SEARCH 表示从首页php进入的搜索
		if (Constants.INDEX_HOTEL_SEARCH.equals(searchDto.getAjaxType()) || "hotelSearchresult".equals(searchDto.getAjaxType()) || Constants.YOU_HOTEL.equals(searchDto.getAjaxType()) 
				|| Constants.HOTEL_SEARCH.equals(searchDto.getAjaxType())) {
			logger.info("----taoPage page0020-----");

			getCtiys(searchDto, model, "hotel");
			searchDto.setHotelStarChn(Constants.HOTEL_INT_CHN_MAP.get(searchDto.getHotelStar()));
			searchDto.setPageSize(Constants.DEFAULT_PAGE_SIZE);
			if (Constants.HOTEL_SEARCH.equals(searchDto.getAjaxType())){
				searchDto.setKeyWords(DefaultParameterUtil.dealParamters(searchDto.getKeyWords(), ""));
			}
			
			B5MPageList<YouHotel> hotelList = hotelService.getYouHotelList(searchDto);
			String returnRuelt = attributeList(searchDto, model, hotelList);
			if (!"".equals(returnRuelt)) {
				return returnRuelt;
			}
		//攻略搜索：INDEX_NOTE_SEARCH 表示从首页php进入的搜索
		} else if(Constants.INDEX_NOTE_SEARCH.equals(searchDto.getAjaxType()) || Constants.NOTES_SEARCH.equals(searchDto.getAjaxType())){
			logger.info("----taoPage page0022-----");
			getCtiys(searchDto, model, "");
			pageSize = toNotesList(searchDto,model,request);
		//旅游产品搜索
		}else {
			logger.info("----taoPage page0021-----");
			getCtiys(searchDto, model, "you");
			// 获取数据集合
			searchDto.setPageSize(Constants.DEFAULT_PAGE_SIZE);
			B5MPageList<YouGoods> pageList = dealAjaxType(searchDto);

			String returnRuelt = attributeList(searchDto, model, pageList);
			//针对从“酒店”频道过来时无结果的页面(列表页面),跳回到对应频道的“全部”页面
			if (!"".equals(returnRuelt)) {
				//仅限于pageMode为list的情况
				if(Constants.PAGE_MODE_LIST.equals(searchDto.getPageMode())){
					searchDto.setSelectedCityId("-1");
					pageList = dealAjaxType(searchDto);
					model.addAttribute("pageList", pageList);
					if(pageList.getAll().size() == 0){
						return returnRuelt;
					}
				}else{
					return returnRuelt;
				}
			}
		}
		
		//true:内页的搜索模式, false:其他
		boolean pageMode = (
				(Constants.DOMESTIC_TRAVEL.equals(searchDto.getAjaxType())|| Constants.ABROAD_TRAVEL.equals(searchDto.getAjaxType())|| Constants.PERIPHERY_TRAVEL.equals(searchDto.getAjaxType())) 
				&& 
				Constants.PAGE_MODE_SEARCH.equals(searchDto.getPageMode()) 
		);
		
		if ("hotelSearchresult".equals(searchDto.getAjaxType()) || Constants.SEARCH.equals(searchDto.getAjaxType()) 
				//从国内游、境外游、周边游内页的搜索框进行搜索
				|| pageMode || Constants.INDEX_HOTEL_SEARCH.equals(searchDto.getAjaxType()) || Constants.INDEX_TRIP_SEARCH.equals(searchDto.getAjaxType())) {
			List<YouGuideNotes> searchNotes = guideNotesService.findGuideNotesBySearchResul(null, "9");
			model.addAttribute("searchNotes", searchNotes);
		}

		// 数据源
		Map<String, String> dataSource = taoSourceService.findTaoSource();
		model.addAttribute("dataSource", dataSource);

		// keywords
		List<YouKeyWords> dataKeyWords = keywordsService.findKeyWords();
		model.addAttribute("keyWords", dataKeyWords);

		model.addAttribute("curPageTag", searchDto.getAjaxType());
		model.addAttribute("menuCurPageTag", searchDto.getPageType());
		model.addAttribute("loginAndRegisterURL", ucServer);
		

		if ("hotelSearchresult".equals(searchDto.getAjaxType()) || Constants.SEARCH.equals(searchDto.getAjaxType()) 
				//从国内游、境外游、周边游内页的搜索框进行搜索,区分列表 和 搜索结果页面，此跳往搜索结果页
				|| pageMode || Constants.INDEX_HOTEL_SEARCH.equals(searchDto.getAjaxType()) || Constants.INDEX_TRIP_SEARCH.equals(searchDto.getAjaxType())){
			return "searchResult";
		}else if(Constants.NOTES_SEARCH.equals(searchDto.getAjaxType()) || Constants.INDEX_NOTE_SEARCH.equals(searchDto.getAjaxType())){
			if(Integer.parseInt(pageSize) <= 0){
				logger.info("----tao_page_notes_list---->" + pageSize);
				model.addAttribute("curPageTag", "noNoteResult");
				model.addAttribute("keyWords", searchDto.getKeyWords());
				model.addAttribute("menuCurPageTag", searchDto.getAjaxType());
				searchDto.setKeyWords("");
				B5MPageList<YouGuideNotes> pageList = guideNotesService.findGuideNotesBySearchResult(searchDto);
				model.addAttribute("hotList", pageList.getAll());
				return "noresult";
			}
			return "notesList";
		}else{
			return "taoResult";
		}
	}

	private String attributeList(YouSearchDto searchDto, Model model, B5MPageList<?> pageList) {
		model.addAttribute("searchDto", searchDto);
		model.addAttribute("dataCityKey", Constants.XML_CITY_KEY);
		model.addAttribute("dataCityKeyId", Constants.XML_CITY);
		if (pageList != null) {
			if (pageList.getCount() <= 0) {
				model.addAttribute("curPageTag", searchDto.getAjaxType());
				model.addAttribute("menuCurPageTag", searchDto.getAjaxType());
				return taoError(searchDto, model);
			}
		} else {
			model.addAttribute("curPageTag", "");
			return taoError(searchDto, model);
		}
		model.addAttribute("pageList", pageList);
		return "";
	}

	/**************************** 页面主数据e **********************************/

	/**
	 * 根据不同的ajaxType 类型 返回对应的数据 B5MPageList<YouGoods>
	 * 
	 * @return B5MPageList<YouGoods>
	 */
	private B5MPageList<YouGoods> dealAjaxType(YouSearchDto searchDto) {
		B5MPageList<YouGoods> goodsList = null;
		if (Constants.DOMESTIC_TRAVEL.equals(searchDto.getAjaxType()) || "domesticTravel".equals(searchDto.getAjaxType())) {
			logger.info("----youType001-----");
			goodsList = goodsService.getYouGoodsByDomesticTravel(searchDto);
		} else if (Constants.ABROAD_TRAVEL.equals(searchDto.getAjaxType()) || "abroadTravel".equals(searchDto.getAjaxType())) {
			logger.info("----youType002-----");
			goodsList = this.goodsService.getYouGoodsByAbroadTravel(searchDto);
		} else if (Constants.PERIPHERY_TRAVEL.equals(searchDto.getAjaxType()) || "peripheryTravel".equals(searchDto.getAjaxType())) {
			logger.info("----youType003-----");
			goodsList = this.goodsService.getYouGoodsByPeripheryTravel(searchDto);
		} else if (Constants.SEARCH.equals(searchDto.getAjaxType()) || Constants.INDEX_TRIP_SEARCH.equals(searchDto.getAjaxType())) {
			logger.info("----youType004-----");
			searchDto.setKeyWords(DefaultParameterUtil.dealParamters(searchDto.getKeyWords(), ""));
			goodsList = this.goodsService.getYouGoodsSearch(searchDto);
		}
		return goodsList;
	}

	/**
	 * 首页数据
	 * 
	 */
	@RequestMapping("/old/index")
	public String findIndexByPage(YouSearchDto dto, Model model) {
		logger.info("----indexPage page003-----");
		// 今日最新
		dto.setPageSize(Constants.DETAILED_PAGE_SIZE);
		List<YouGoods> topIndexList = goodsService.findsIndexSpecialTravelByList(dto, "0");
		model.addAttribute("topIndexList", topIndexList);

		// 最后尾货
		dto.setPageSize(Constants.YOU_RECOMMEND_SZIE);
		List<YouGoods> lastPotterList = goodsService.findsIndexSpecialTravelByList(dto, "1");
		model.addAttribute("lastPotterList", lastPotterList);

		// 即将开售
		List<YouGoods> taoPeripheryList = goodsService.findsIndexSpecialTravelByList(dto, "2");
		model.addAttribute("taoPeripheryList", taoPeripheryList);

		// 酒店
		List<YouHotel> hotelList = hotelService.findsIndexHotelByList(dto);
		model.addAttribute("youHotelList", hotelList);

		// 广告专题数据
		List<YouTopic> taoIndexTopicList = taoTopicService.findIndexTopTopic();
		model.addAttribute("taoIndexTopicList", taoIndexTopicList);

		// 数据源
		Map<String, String> dataSource = taoSourceService.findTaoSource();
		model.addAttribute("dataSource", dataSource);

		// keywords
		List<YouKeyWords> dataKeyWords = keywordsService.findKeyWords();
		model.addAttribute("keyWords", dataKeyWords);

		getCtiys(dto, model, "you");
		model.addAttribute("searchDto", dto);
		model.addAttribute("dataCityKey", Constants.XML_CITY_KEY);
		model.addAttribute("curPageTag", dto.getAjaxType());
		model.addAttribute("menuCurPageTag", dto.getPageType());
		model.addAttribute("loginAndRegisterURL", ucServer);

		return "taoIndex";
	}

	/**
	 * 详情页
	 * 
	 * @param url
	 *            电商的商品地址
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/pageDetailed")
	public String pageDetailed(YouSearchDto dto, Model model) throws IOException {
		logger.info("----pageDetailedPage page004-----");
		dto.setAjaxType(dto.getPageType());
		YouHotel youHotel = null;
		YouGoods youGoods = null;
		// 按点击id获取对应商品
		if (Constants.YOU_HOTEL.equals(dto.getAjaxType())) {
			// 按点击id获取对应商品
			youHotel = hotelService.getYouHotelById(dto);
			model.addAttribute("youGoods", youHotel);
			// 酒店详细信息
			List<YouHotelDetail> hotelDetailList = hotelDetailService.getYouHotelDetailById(dto);
			model.addAttribute("hotelDetailList", hotelDetailList);
			// 设置为详情页标志
			dto.setSearchType(Constants.B5M_HOTEL_SAME_CITY);
			// 相关酒店
			dto.setPageSize(Constants.DEFAULT_HOT_COUNT);
			B5MPageList<YouHotel> hotelList = hotelService.getYouHotelList(dto);
			model.addAttribute("pageList", hotelList);
		} else {
			// 按点击id获取对应商品
			youGoods = goodsService.findYouGoodsById(dto);
			model.addAttribute("youGoods", youGoods);
			// 设置为详情页标志
			dto.setSearchType(Constants.B5M_YOU_SAME_TYPE);
			dto.setPageSize(Constants.DEFAULT_HOT_COUNT);
			B5MPageList<YouGoods> pageList = dealAjaxType(dto);
			model.addAttribute("pageList", pageList);
		}

		// 数据源
		Map<String, String> dataSource = taoSourceService.findTaoSource();
		
		model.addAttribute("dataSource", dataSource);
		model.addAttribute("fromType", "detail");
		model.addAttribute("searchDto", dto);
		model.addAttribute("dataCityKey", Constants.XML_CITY_KEY);
		model.addAttribute("dataCityKeyId", Constants.XML_CITY);
		model.addAttribute("menuCurPageTag", dto.getPageType());
		model.addAttribute("loginAndRegisterURL", ucServer);
		model.addAttribute("ucServer", ucServer);
		if((Constants.YOU_HOTEL.equals(dto.getAjaxType()) && youHotel == null) || (!Constants.YOU_HOTEL.equals(dto.getAjaxType()) && youGoods == null)){
			return taoError(dto, model);
		}
		return "pageDetailed";
	}

	/**
	 * 其他项目统一进入 登陆界面
	 * 
	 * @param url
	 *            电商的商品地址
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/toDialogLogin")
	public String toDialogLogin(YouSearchDto dto, Model model) throws IOException {
		logger.info("----pagetoDialogLogin page005-----");
		// url不合法
		if (StringUtils.isBlank(dto.getUrl()) || !dto.getUrl().trim().startsWith("http:") || StringUtils.isBlank(dto.getShowUrl())
				|| !dto.getShowUrl().trim().startsWith("http:"))
			return "redirect:" + ucServer + "/user/user/index.htm";

		String login = (String) getRequest().getAttribute(Constants.USER_SESSION_FLAG);
		// 已登陆
		if (("true").equals(login) && login != null)
			return "redirect:" + dto.getUrl();

		// 未登录,跳转到统一登陆界面
		model.addAttribute("redirectUrl", dto.getUrl());
		model.addAttribute("showUrl", dto.getShowUrl());
		model.addAttribute("ucServer", ucServer);
		return "/common/toDialogLogin";
	}

	/**
	 * 景点着陆页
	 * 
	 * @return
	 */
	@RequestMapping("/youGuideDetailed")
	public String youGuideDetailed(YouSearchDto dto, Model model) {
		logger.info("----pageYouGuideDetailed page006-----");
		YouGuide guide = guideService.findGuide(dto);
		model.addAttribute("guide", guide);

		List<YouGuideNotes> notesList = guideNotesService.findGuideNotes(dto);
		model.addAttribute("notesList", notesList);

		dto.setPageSize(Constants.YOU_LANDING_SZIE);
		if (guide != null)
			dto.setYouType(guide.getYouType());
		else
			dto.setYouType("0");
		List<YouGoods> youList = goodsService.findsIndexSpecialTravelByList(dto, dto.getYouType());
		model.addAttribute("youList", youList);
		if("0".equals(dto.getYouType())){
			model.addAttribute("currPageTag", "domesticTravel");
		}else if("1".equals(dto.getYouType())){
			model.addAttribute("currPageTag", "abroadTravel");
		}else{
			model.addAttribute("currPageTag", "peripheryTravel");
		}

		dto.setPageSize(Constants.YOU_LANDING_SZIE - 2);
		List<YouGuideCircle> circleList = guideCircleService.findGuideCircle(dto);
		model.addAttribute("circleList", circleList);

		// keywords
		List<YouKeyWords> dataKeyWords = keywordsService.findKeyWords();
		model.addAttribute("keyWords", dataKeyWords);

		// 数据源
		Map<String, String> dataSource = taoSourceService.findTaoSource();
		model.addAttribute("dataSource", dataSource);

		model.addAttribute("searchDto", dto);
		model.addAttribute("dataCityKey", Constants.XML_CITY_KEY);
		model.addAttribute("loginAndRegisterURL", ucServer);
		return "youLandingPage";
	}

	/**
	 * 攻略列表页
	 * 
	 * @param dto
	 * @param model
	 * @return
	 */
	// @RequestMapping("/notesList")
	public String toNotesList(YouSearchDto dto, Model model,HttpServletRequest request) {
		logger.info("----toNotesList notesList-----");
		dto.setPageSize(6);
		dto.setKeyWords(DefaultParameterUtil.dealParamters(dto.getKeyWords(), ""));
		B5MPageList<YouGuideNotes> pageList = guideNotesService.findGuideNotesBySearchResult(dto);
		model.addAttribute("pageList", pageList);

		// 相关旅游
		dto.setPageSize(Constants.YOU_LANDING_SZIE - 3);
		dto.setYouType("0");
		List<YouGoods> youList = goodsService.findsIndexSpecialTravelByList(dto, dto.getYouType());
		model.addAttribute("youList", youList);

		// 相关酒店
		dto.setPageSize(Constants.DEFAULT_HOT_COUNT - 7);
		List<YouHotel> hotelList = hotelService.findsNotesPageByList(null, dto);
		model.addAttribute("hotelList", hotelList);

		// 数据源
		Map<String, String> dataSource = taoSourceService.findTaoSource();
		model.addAttribute("dataSource", dataSource);

		model.addAttribute("searchDto", dto);
		model.addAttribute("curPageTag", dto.getAjaxType());
		model.addAttribute("dataCityKey", Constants.XML_CITY_KEY);
		model.addAttribute("loginAndRegisterURL", ucServer);
		return pageList.getAll().size() + "";
	}

	/**
	 * 查询结果页
	 * 
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequestMapping("/search")
	public String toSearch(YouSearchDto dto, Model model, HttpServletRequest request) {
		logger.info("----search finds-----");
		String pageSize = "-1";
		model.addAttribute("autofillServer", autofillServer);
		setDtoParameter(dto, request);
		//去掉搜索关键字中所有的空格
		//dto.setKeyWords(dto.getKeyWords().replaceAll(" ", ""));
		//攻略列表页面无需推荐
		if(!Constants.NOTES_SEARCH.equals(dto.getAjaxType())){
			// 攻略hot数据
			List<YouGuideNotes> searchNotes = guideNotesService.findGuideNotesBySearchResul(null, "9");
			model.addAttribute("searchNotes", searchNotes);
		}
		dto.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		if (Constants.HOTEL_SEARCH.equals(dto.getAjaxType()) || "hotelSearchresult".equals(dto.getAjaxType()) || Constants.INDEX_HOTEL_SEARCH.equals(dto.getAjaxType())) {
			String tempFalg = request.getParameter("f");
			//酒店下拉框 更多链接地址
			if(tempFalg != null && "map".equals(tempFalg)){
				return "address";
			}
			
			getCtiys(dto, model, "hotel");
			B5MPageList<YouHotel> hotelList = hotelService.getYouHotelList(dto);
			boolean hotelSearchFlag = (StringUtils.isNotBlank(dto.getHotelPrice()) || StringUtils.isNotBlank(dto.getHotelStar()));
			String returnRuelt = attributeList(dto, model, hotelList);
			if (!"".equals(returnRuelt) && !hotelSearchFlag) {
				return returnRuelt;
			}
		} else if(Constants.NOTES_SEARCH.equals(dto.getAjaxType()) || Constants.INDEX_NOTE_SEARCH.equals(dto.getAjaxType())){
			getCtiys(dto, model, "");
			pageSize = toNotesList(dto,model,request);
		}else{
			//默认 访问search.html的时候
			if("".equals(dto.getAjaxType())){
				dto.setAjaxType("searchresult");
			}
			getCtiys(dto, model, "you");

			// 商品数据查询
			B5MPageList<YouGoods> searchList = goodsService.getYouGoodsSearch(dto);
			model.addAttribute("pageList", searchList);

			String returnRuelt = attributeList(dto, model, searchList);
			boolean searchFlag = (StringUtils.isNotBlank(dto.getType0()) || StringUtils.isNotBlank(dto.getType1()) || StringUtils.isNotBlank(dto.getDays())
					|| StringUtils.isNotBlank(dto.getPriceStart()+"") || StringUtils.isNotBlank(dto.getPriceEnd()+"") || StringUtils.isNotBlank(dto.getTimeStart())
					|| StringUtils.isNotBlank(dto.getTimeEnd()));
			//搜索无结果页
			if (!"".equals(returnRuelt) && !searchFlag) {
				return returnRuelt;
			}
		}

		// 数据源
		Map<String, String> dataSource = taoSourceService.findTaoSource();
		model.addAttribute("dataSource", dataSource);

		// keywords
		List<YouKeyWords> dataKeyWords = keywordsService.findKeyWords();
		model.addAttribute("keyWords", dataKeyWords);
		//目的地城市列表
		//model.addAttribute("dcList", goodsService.getCitysByKeyword(dto.getKeyWords()));
		
		model.addAttribute("searchDto", dto);
		model.addAttribute("curPageTag", dto.getAjaxType());
		model.addAttribute("dataCityKey", Constants.XML_CITY_KEY);
		model.addAttribute("dataCityKeyId", Constants.XML_CITY);
		model.addAttribute("loginAndRegisterURL", ucServer);
		model.addAttribute("autofillFlag", dto.getAjaxType());
		if(Constants.NOTES_SEARCH.equals(dto.getAjaxType()) || Constants.INDEX_NOTE_SEARCH.equals(dto.getAjaxType())){
			if(Integer.parseInt(pageSize) <= 0){
				logger.info("----notes_list---->" + pageSize);
				model.addAttribute("curPageTag", "noNoteResult");
				model.addAttribute("menuCurPageTag", dto.getAjaxType());
				model.addAttribute("keyWords", dto.getKeyWords());
				dto.setKeyWords("");
				B5MPageList<YouGuideNotes> pageList = guideNotesService.findGuideNotesBySearchResult(dto);
				model.addAttribute("hotList", pageList.getAll());
				return "noresult";
			}
			return "notesList";
		}
		return "searchResult";
	}

	private void setDtoParameter(YouSearchDto dto, HttpServletRequest request) {
		String keyWords = (String) request.getParameter("keywords");
		String city = (String) request.getParameter("city");
		String ajaxType = (String) request.getParameter("ajaxType");
		String currPageNo = (String) request.getParameter("currPageNo");
		String order = (String) request.getParameter("order");
		//新添加的搜索条件
		String type0 = request.getParameter("type0");
		String destination = request.getParameter("destination");
		String days = request.getParameter("days");
		String type1 = request.getParameter("type1");
		String priceStart = request.getParameter("price_start");
		String priceEnd = request.getParameter("price_end");
		String timeStart = request.getParameter("J_date1");
		String timeEnd = request.getParameter("J_date2");
		
		//酒店新添加的条件
		String hotelPrice = request.getParameter("hotel_price");
		String hotelStar = request.getParameter("hotel_star");
		
		if (keyWords != null){
			dto.setKeyWords(DefaultParameterUtil.dealParamters(keyWords, ""));
		}else{
			dto.setKeyWords(DefaultParameterUtil.dealParamters(dto.getKeyWords(), ""));
		}
		if (city != null){
			dto.setIpLocate(DefaultParameterUtil.dealParamters(city, ""));
		}
		
		if (ajaxType != null){
			dto.setAjaxType(DefaultParameterUtil.dealParamters(ajaxType, ""));
		}
		if (currPageNo != null){
			dto.setCurrPageNoTemp(DefaultParameterUtil.dealParamters(currPageNo, ""));
		}
		if (order != null){
			dto.setOrder(DefaultParameterUtil.dealParamters(order, ""));
		}
		//新的搜索条件
		if(type0 != null){
			dto.setType0(type0);
		}
		if(destination != null){
			dto.setDestination(destination);
		}
		if(days != null){
			dto.setDays(days);
		}
		if(type1 != null){
			dto.setType1(type1);
		}
		if(!"".equals(priceStart) && priceStart != null){
			dto.setPriceStart(priceStart);
		}else{
			dto.setPriceStart("");
		}
		
		if(!"".equals(priceEnd) && priceEnd != null){
			dto.setPriceEnd(priceEnd);
		}else{
			dto.setPriceEnd("");
		}
		
		if(timeStart != null){
			dto.setTimeStart(timeStart);
		}
		if(timeEnd != null){
			dto.setTimeEnd(timeEnd);
		}
		
		//酒店新的条件
		if(hotelPrice != null){
			dto.setHotelPrice(hotelPrice);
		}
		if(hotelStar != null){
			dto.setHotelStar(hotelStar);
			dto.setHotelStarChn(Constants.HOTEL_INT_CHN_MAP.get(hotelStar));
		}
	}

	private void getCtiys(YouSearchDto dto, Model model, String type) {
		// 酒店站点城市
		Map<String, String> hotelMap = hotelService.getHotelCity();
		model.addAttribute("hotelCity", hotelMap);

		// 帮5游站点城市
		Map<String, String> youCity = goodsService.getYouCity(dto);
		model.addAttribute("youCity", youCity);

		// 攻略站点城市
		Map<String, String> notesCity = guideNotesService.findNotesCity();
		model.addAttribute("notesCity", notesCity);

		if (("hotel").equals(type)) {
			String isCity = hotelMap.get(Constants.XML_CITY_KEY.get(dto.getIpLocate()));
			if (null == isCity || ("").equals(isCity)) {
				String shCity = hotelMap.get(Constants.XML_CITY_KEY.get("上海"));
				if ((null == shCity || ("").equals(shCity)) && hotelMap.keySet().size() > 0)
					dto.setIpLocate((String) ((hotelMap.keySet().toArray())[0]));
				else
					dto.setIpLocate("2");
			}
		} else if (("you").equals(type)) {
			String isCity = youCity.get(Constants.XML_CITY_KEY.get(dto.getIpLocate()));
			if (null == isCity || ("").equals(isCity)) {
				String shCity = youCity.get(Constants.XML_CITY_KEY.get("上海"));
				if ((null == shCity || ("").equals(shCity)) && youCity.keySet().size() > 0)
					dto.setIpLocate((String) ((youCity.keySet().toArray())[0]));
				else
					dto.setIpLocate("2");
			}
		} else {
			if("-1".equals(dto.getSelectedCityId())){
				dto.setIpLocate("全部");
			}
		}
	}

	/**
	 * 攻略详情页
	 * 
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequestMapping("/notesDetail")
	public String toNotesDetail(YouSearchDto dto, Model model) {
		logger.info("----toNotesList notesList-----");

		// 攻略详情页数据
		YouGuideNotes detail = guideNotesService.findYouGuideNotesDetail(dto.getClikcId());
		model.addAttribute("detail", detail);

		if (detail != null) {
			// IP热门景点
			List<YouGuideNotes> searchNotes = guideNotesService.findGuideNotesBySearchResul(detail.getCity(), "8");
			model.addAttribute("searchNotes", searchNotes);

			// IP热门酒店
			dto.setPageSize(5);
			List<YouHotel> searchHotel = hotelService.findsNotesPageByList(detail.getCity(), dto);
			model.addAttribute("searchHotel", searchHotel);
		}

		model.addAttribute("searchDto", dto);
		model.addAttribute("curPageTag", dto.getAjaxType());
		model.addAttribute("dataCityKey", Constants.XML_CITY_KEY);
		model.addAttribute("loginAndRegisterURL", ucServer);
		return "notesDetail";
	}
	
	/**
	 * site map
	 * 
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequestMapping("/siteMap")
	public String siteMap(YouSearchDto dto, Model model) {
		model.addAttribute("curPageTag", "sitemap");
		model.addAttribute("siteMapType", dto.getSiteMapType());
		model.addAttribute("dataCityKey", Constants.XML_CITY_KEY);
		model.addAttribute("searchDto", dto);
		List list = new ArrayList();
		//3 酒店
		if("3".equals(dto.getSiteMapType())){
			list = hotelService.getSiteMapList(dto);
			// 酒店站点城市
			Map<String, String> hotelMap = hotelService.getHotelCity();
			hotelMap.remove("-1");
			hotelMap.remove(Constants.XML_CITY_KEY.get(dto.getIpLocate()));
			model.addAttribute("hotelCity", hotelMap);
		}else{
			//其他城市
			list = goodsService.getSiteMapList(dto);
			Map<String, String> youCity = goodsService.getYouCity(dto);
			youCity.remove("-1");
			youCity.remove(Constants.XML_CITY_KEY.get(dto.getIpLocate()));
			model.addAttribute("youCity", youCity);
		}
		
		model.addAttribute("siteList", list);
		return "siteMap";
	}
	
	@RequestMapping("/mapKeyWorkList")
	public String mapKeyWorkList(YouSearchDto searchDto, Model model,HttpServletRequest request,HttpServletResponse response) {
		String city = request.getParameter("city")==null ? "2":request.getParameter("city");
		String callback = request.getParameter("callback");
		if(!"-1".equals(city)){
			searchDto.setSelectedCityId(city);
			Map map = hotelService.getMapKeyWordsList(searchDto);
			Gson gson = new Gson();
			System.out.println("mapKeyWorkList--->" + city);
			PrintWriter out = null;
			try {
				out = response.getWriter();
				if(callback != null){
					out.print(callback + "(" + gson.toJson(map) + ")");
				}else{
					out.print(gson.toJson(map));
				}
				out.flush();
			}catch(Exception e){
				
			}finally{
				if(out != null){
					out.close();
				}
			}
		}
		return null;
	}
	
	/**
	 * ajax 获取地图酒店数据
	 * @param dto
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getHotelsForMapByAjax")
	public String getHotelsForMapByAjax(YouSearchDto dto, Model model,HttpServletRequest request,HttpServletResponse response) {
		String keyWords = (String) request.getParameter("keyword");
		String city = (String) request.getParameter("city");
		String currPageNo = (String) request.getParameter("currPageNo");
		String level = (String) request.getParameter("level");
		String callback = request.getParameter("callback");
		String noResult = "";
		dto.setKeyWords(keyWords);
		//0表示不在列表中，1表示在列表中
		String isKeyWordInMap = "0";
		
		if(StringUtils.isNotBlank(city)){
			dto.setSelectedCityId(city);
		}
		
		if(StringUtils.isNotBlank(currPageNo)){
			dto.setCurrPageNo(Integer.parseInt(currPageNo));
		}
		
		if(StringUtils.isNotBlank(level)){
			dto.setHotelStar(level);
		}else{
			dto.setHotelStar("");
		}
		PrintWriter out = null;
		
		try {
			dto.setPageMode("map");
			dto.setPageSize(10);
			//城市中心坐标
			String cityCenterPoints = hotelService.getCityCenterLatAndLong(dto);
			noResult = "{\"all\": [],\"status\":\"-1\",\"cityCenter\":\"" + cityCenterPoints + "\"}";
			
			B5MPageList<YouHotel> hotelList = hotelService.getYouHotelList(dto);
		
			Gson gson = new Gson();
			logger.info("getHotelsForMap--->" + city + "---" + keyWords + "---" + hotelList.getAll().size());
		
			out = response.getWriter();
			String result = gson.toJson(hotelList);
			if(dto.isKeyWordInMap()){
				isKeyWordInMap = "1";
			}
			result = result.substring(0, result.length() - 1) + ",\"latitude\":" + dto.getLatitude() + ",\"longitude\":" + dto.getLongitude() +",\"status\":\"" + 0 + "\",\"inmap\":\"" + isKeyWordInMap + "\",\"cityCenter\":\"" + cityCenterPoints + "\"}";
			
			//下拉框 关键字 无结果页的情况：1、总页数小于1   2、无中心点
			boolean flag1 = (dto.getLatitude() == null || dto.getLongitude() == null || hotelList.getTotalPages() < 1) && dto.isKeyWordInMap();
			//非下拉框 关键字 无结果情况
			boolean flag2 = hotelList.getTotalPages() < 1 && !dto.isKeyWordInMap();
			
			if(callback != null){
				if(flag1 || flag2){
					out.print(callback + "(" + noResult + ")");
					logger.info("--getHotelsForMapByAjax-noResult----fl->" + flag1 + "--f2-->" + flag2 + "---->" + dto.isKeyWordInMap() + "---" + hotelList.getTotalPages());
				}else{
					out.print(callback + "(" + result + ")");
				}
			}else{
				if(flag1 || flag2){
					out.print(noResult);
					logger.info("--getHotelsForMapByAjax-noResult----fl->" + flag1 + "--f2-->" + flag2 + "---->" + dto.isKeyWordInMap()  + "---" + hotelList.getTotalPages());
				}else{
					out.print(result);
				}
			}
			out.flush();
		}catch(Exception e){
			out.print(noResult);
			out.flush();
		}finally{
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
	
	@RequestMapping("/getHotelsForMap")
	public String getHotelsForMap(YouSearchDto dto, Model model,HttpServletRequest request,HttpServletResponse response) {
		String keyWords = (String) request.getParameter("keywords");
		String city = (String) request.getParameter("selectedCityId");
		if(city == null){
			city = "2";
		}
		dto.setSelectedCityId(city);
		dto.setPageSize(10);
		String str = getDefaultKeyWord(dto);
		//城市中心坐标
		String cityCenterPoints = hotelService.getCityCenterLatAndLong(dto);
		if(keyWords == null){
			keyWords = str;
		}
		//获取推荐列表
		//dto.setKeyWords(str);
		dto.setPageMode("mapNoResult");
		//推荐商品列表
		B5MPageList<YouHotel> hotelList = hotelService.getYouHotelList(dto);
		String defaultJson = getDefaultListToJson(hotelList.getAll(),"");
		//设置为真实的keywords
		dto.setKeyWords(keyWords);
		try {
			model.addAttribute("defaultKeyWord", str);
			model.addAttribute("mapKeyword", keyWords);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("hotelList", hotelList.getAll().size() > 10 ? hotelList.getAll().subList(0, 10) : hotelList.getAll());
		model.addAttribute("defaultJson", defaultJson);
		model.addAttribute("mapCity", city);
		model.addAttribute("mapMode", "post");
		model.addAttribute("dataCityKeyId", Constants.XML_CITY);
		model.addAttribute("dataHotelsChn", Constants.HOTEL_INT_CHN_MAP);
		model.addAttribute("searchDto", dto);
		model.addAttribute("cityCenterPoints", cityCenterPoints);
		
		// 酒店站点城市
		Map<String, String> hotelMap = hotelService.getHotelCity();
		hotelMap.remove("-1");
		model.addAttribute("hotelCity", hotelMap);
		
		return "hotelMap";
	}
	
	/**
	 * 活动专题
	 * 
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequestMapping("/famousTopics")
	public String toFamousTopics(YouSearchDto dto, Model model) {
		dto.setCurrPageNo(4);
		if (StringUtils.isNotBlank(dto.getBtmSource())) {
			YouTopicMain main = goodsService.findActivityTopicMain(dto.getBtmSource());
			if (main != null) {
				List<YouTopicBar> listBar = goodsService.findActivityTopicBar(main.getId());
				for (YouTopicBar taoTopicBar : listBar) {
					if(taoTopicBar.getTitle().indexOf("&nbsp;") > -1){
						taoTopicBar.setTitle(taoTopicBar.getTitle().replaceAll("&nbsp;", ""));
					}
					dto.setKeyWords(main.getName() + "_" + taoTopicBar.getTitle() + "_");
					//logger.info("------toFamousTopics------" + main.getName() + "_" + taoTopicBar.getTitle() + "_");
					taoTopicBar.setTopics(hotelService.findTopicYouHotels(dto));
				}
				model.addAttribute("listBar", listBar);
			} else {
				return taoError(dto, model);
			}
			model.addAttribute("topicMain", main);
		} else {
			return taoError(dto, model);
		}
		model.addAttribute("searchDto", dto);
		model.addAttribute("dataSource", Constants.DATA_SOURCE);
		logger.info("------查找名品专题----专题数据------famousTopics------");
		return "activityTemplate";
	}
	
	@RequestMapping("/address")
	public String address(YouSearchDto dto, Model model, HttpServletRequest request) {
		String city = request.getParameter("city")==null ? "2":request.getParameter("city");
		setDtoParameter(dto, request);
		// 攻略hot数据
		List<YouGuideNotes> searchNotes = guideNotesService.findGuideNotesBySearchResul(null, "9");
		model.addAttribute("searchNotes", searchNotes);
		model.addAttribute("curPageTag","hotelSearchresult");
		getCtiys(dto, model, "hotel");
		dto.setSelectedCityId(city);
		model.addAttribute("dataCityKeyId", Constants.XML_CITY);
		model.addAttribute("searchDto", dto);
		HotelKeyWords hk2 = hotelService.getMetroHotelListByType(dto,"2");
		HotelKeyWords hk3 = hotelService.getMetroHotelListByType(dto,"3");
		HotelKeyWords hk4 = hotelService.getMetroHotelListByType(dto,"4");
		HotelKeyWords hk5 = hotelService.getMetroHotelListByType(dto,"5");
		
		model.addAttribute("hk2", hk2);
		model.addAttribute("hk3", hk3);
		model.addAttribute("hk4", hk4);
		model.addAttribute("hk5", hk5);
		return "address";
	}

	/**
	 * 酒店搜索的地图模式
	 * @param dto
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/hotelMap")
	public String hotelMap(YouSearchDto dto, Model model, HttpServletRequest request) {
		model.addAttribute("dto", dto);
		// 酒店站点城市
		Map<String, String> hotelMap = hotelService.getHotelCity();
		model.addAttribute("hotelCity", hotelMap);
		Map map = hotelService.getMapKeyWordsList(dto);
		StringBuffer str = (StringBuffer)map.get("biz");
		String keyWords = str.toString().split("#")[0];
		model.addAttribute("defaultKeyWord", keyWords);
		return "hotelMap";
	}
	
	/**
	 * 获取默认关键字
	 * @param dto
	 * @return
	 */
	private String getDefaultKeyWord(YouSearchDto dto){
		Map map = hotelService.getMapKeyWordsList(dto);
		String result = "";
		Set<String> set = map.keySet();
		for(String key :set){
			String value = ((StringBuffer)map.get(key)).toString();
			if(!"hotel".equals(key) && value != null && value.split("#").length > 1){
				result = value.split("#")[0];
				break;
			}
		}
		return result;
	}
	
	/**
	 * 返回默认列表的json
	 * @param list
	 * @param server
	 * @return
	 */
	private String getDefaultListToJson(List list,String server){
		String result = "[";
		String size = "25|32";
		String clickUrl = "";
		String jsonStr = "";
		JSONArray ja = new JSONArray();
		JSONObject js = null;
		for(int i = 0;i < list.size();i++){
			YouHotel th = (YouHotel)list.get(i);
			String addr = th.getAddress();
			if(addr !=null && addr.length() > 10){
				addr = addr.substring(0, 10) + "...";
			}
			if(StringUtils.isBlank(addr)){
				addr = "未知";
			}
			String icon = "../../images/you/map/"
					+ (i + 1) + ".png";
			clickUrl = server
					+ "/pageDetailed_-1_hotel_"
					+ th.getId() + ".html";
			jsonStr = "{\"point\":\"" + th.getNewLongitude()
				+ "|" + th.getNewLatitude()
				+ "\",\"shortTitle\":\"" + th.getName()
				+ "\",\"fullTitle\":\"" + th.getName()
				+ "\",\"price\":\"" + th.getSalesPrice()
				+ "&nbsp;&nbsp;&nbsp;&nbsp;\",\"shortAddr\":\"" + addr
				+ "\",\"fullAddr\":\"" + th.getAddress()
				+ "\",\"star\":\"" + Constants.HOTEL_INT_CHN_MAP.get(th.getLevelInfo())
				+ "\",\"pic\":\"" + th.getImgurl()
				+ "\",\"icon\":\"" + icon
				+ "\",\"size\":\"" + size
				+ "\",\"url\":\"" + clickUrl + "\"}";
			js = JSONObject.fromString(jsonStr);
			ja.put(js);
			result = result + jsonStr + ",";
		}
		result = result.substring(0, result.length()-1) + "]";
		return result;
	}
}
