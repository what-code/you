package com.b5m.you.ctrl;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.b5m.web.core.AbstractBaseController;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.Constants;
import com.b5m.web.core.ContextUtils;
import com.b5m.you.common.ipUtil.IPSeeker;
import com.b5m.you.common.util.SF1Util;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.TuanModel;
import com.b5m.you.model.YouGoods;
import com.b5m.you.model.YouGuideNotes;
import com.b5m.you.model.YouHotel;
import com.b5m.you.model.YouHotelDetail;
import com.b5m.you.service.IYouGoodsService;
import com.b5m.you.service.IYouGuideCircleService;
import com.b5m.you.service.IYouGuideNotesService;
import com.b5m.you.service.IYouGuideService;
import com.b5m.you.service.IYouHotelDetailService;
import com.b5m.you.service.IYouHotelService;
import com.b5m.you.service.IYouKeyWordsService;
import com.b5m.you.service.IYouSourceService;
import com.b5m.you.service.IYouTopicService;
import com.b5m.you.service.IYouTravelService;
import com.google.gson.Gson;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Controller
public class MainController extends AbstractBaseController {
	@Resource(name = "taoGoodsServiceRaindrop")
	private IYouGoodsService goodsService;

	@Resource
	private IYouTravelService travelService;

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

	public static Logger logger = Logger.getLogger(MainController.class);
	
	public static final int cookieTime = 30 * 24 * 60 * 60;

	/**
	 * 所有搜索的入口，格式为：/searcher?keyword=上海&type=1&city=2&page=1
	 * 
	 * @param dto
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/searcher")
	public String search(YouSearchDto dto, Model model, HttpServletRequest request,HttpServletResponse response) {
		// 公共参数---------------s
		B5MPageList list = null;
		String from = (String) request.getParameter("from");
		String type = (String) request.getParameter("type");
		String keyword = (String) request.getParameter("keyword");
		String pageNo = (String) request.getParameter("page");
		String selectCityId = (String) request.getParameter("city");
		String currCity = IPSeeker.getInstance().getAddress(ContextUtils.getInstance().getIpAddr());
		// 分页URL
		StringBuffer pagerUrl = new StringBuffer("searcher?");
		Cookie ck1 = null;
		Cookie ck2 = null;
		String rootPath = ContextUtils.getInstance().getRootPath();
		if(StringUtils.isNotBlank(rootPath)){
			rootPath = rootPath.replace("http://you", "");
		}else{
			rootPath = ".b5m.com";
		}
		logger.info("-----rp---->" + rootPath);
		// 公共参数---------------e

		// 私有参数---------------s
		// trip参数
		// TODO

		// hotel参数
		// 酒店的入住及退房日期
		String dateBegin = (String) request.getParameter("hs");
		String dateEnd = (String) request.getParameter("he");
		// 酒店星级
		String hotelLevel = (String) request.getParameter("hl");
		// 是否海外酒店
		String hwOrNot = (String) request.getParameter("hw");
		dto.setHotelBeginDate(dateBegin);
		dto.setHotelEndDate(dateEnd);
		dto.setHotelStar(hotelLevel);
		if(StringUtils.isBlank(hwOrNot)){
			dto.setHwOrNot("0");
		}else{
			dto.setHwOrNot(hwOrNot);
		}
		// note参数
		// TODO
		// 私有参数---------------e

		// 参数处理并设置到dto对应的属性---------------s
		// keyword
		/*try {
		 keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
		 } catch (Exception e) {
		 e.printStackTrace();
		}*/
		dto.setKeyWords(keyword);

		// currCity
		if (currCity == null || "".equals(currCity.trim())) {
			currCity = "全部";
		}

		// selectCity
		if (selectCityId == null || "".equals(selectCityId.trim())) {
			selectCityId = "-1";
		}
		if (Constants.XML_CITY.get(selectCityId) == null) {
			selectCityId = "-1";
		}
		dto.setSelectedCity(Constants.XML_CITY.get(selectCityId));
		dto.setSelectedCityId(selectCityId);
		/**
		 * cookieCity
		 */
		String cookieCity = "";
		try {
			cookieCity = URLEncoder.encode(dto.getSelectedCity()+ "|" + dto.getSelectedCityId() ,"UTF-8") ;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		// pageNo
		if (pageNo == null || "".equals(pageNo.trim())) {
			pageNo = "1";
		} else {
			pageNo = pageNo.replaceAll("\\D", "");
			if ("".equals(pageNo.trim()) || Integer.parseInt(pageNo) < 1) {
				pageNo = "1";
			}
		}
		dto.setCurrPageNo(Integer.parseInt(pageNo));
		// 参数处理---------------e

		// 执行搜索---------------s
		logger.info("----main-searcher-----" + type + "-----" + keyword);
		if ("0".equals(type)) {
			// TODO 旅游搜索
			list = (B5MPageList) searchTrip(dto, request, model);
			model.addAttribute("pageType", "travel");
			try {
				ck1 = new Cookie("travelCity", cookieCity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ck1.setMaxAge(cookieTime);
			ck1.setDomain(rootPath);
			response.addCookie(ck1);
			ck1 = null;
		} else if ("1".equals(type)) {
			// TODO 酒店搜索
			list = (B5MPageList) searchHotel(dto, request, model);
			// 分页URL---------------s
			pagerUrl.append("keyword=").append(StringUtils.isBlank(keyword) ? "" : keyword);
			pagerUrl.append("&type=").append(StringUtils.isBlank(type) ? "" : type);
			pagerUrl.append("&city=").append(StringUtils.isBlank(selectCityId) ? "" : selectCityId);
			pagerUrl.append("&hs=").append(StringUtils.isBlank(dateBegin) ? "" : dateBegin);
			pagerUrl.append("&he=").append(StringUtils.isBlank(dateEnd) ? "" : dateEnd);
			pagerUrl.append("&hl=").append(StringUtils.isBlank(hotelLevel) ? "" : hotelLevel);
			pagerUrl.append("&hw=").append(StringUtils.isBlank(hwOrNot) ? "" : hwOrNot);
			pagerUrl.append("&page=");
			// 分页URL---------------e
			try {
				ck1 = new Cookie("hotelCity", cookieCity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ck1.setMaxAge(cookieTime);
			ck1.setDomain(rootPath);
			response.addCookie(ck1);
			ck1 = null;
		} else if ("2".equals(type)) {
			// TODO 攻略搜索
			list = (B5MPageList) searchNote(dto, request, model);
			// 分页URL---------------s
			pagerUrl.append("keyword=").append(StringUtils.isBlank(keyword) ? "" : keyword);
			pagerUrl.append("&type=").append(StringUtils.isBlank(type) ? "" : type);
			pagerUrl.append("&city=").append(StringUtils.isBlank(selectCityId) ? "" : selectCityId);
			pagerUrl.append("&page=");
			// 分页URL---------------e
		} else {
			list = (B5MPageList) searchTrip(dto, request, model);
			type = "0";
		}
		dto.setSearchType(type);
		// 执行搜索---------------e

		// 团购数据---------------s
		String chnCity = Constants.XML_CITY.get(dto.getSelectedCityId());
		if ("-1".equals(dto.getSelectedCityId()) || StringUtils.isBlank(chnCity)) {
			chnCity = "";
		} else {
			dto.setSelectedCity(chnCity);
		}
		List<TuanModel> tuanList = SF1Util.getTuanDataFromSF1(chnCity, type, "0".equals(type) ? 3 : 5);

		// 团购数据---------------e

		logger.info("result size--->" + list.getAll().size() + "---->" + dto.getPageSize());
		model.addAttribute("pagerUrl", pagerUrl.toString());
		model.addAttribute("fromType", "search");
		model.addAttribute("pageTag", type);
		model.addAttribute("searchDto", dto);
		if ("0".equals(type)) {
			model.addAttribute("tuanTravel", tuanList);
		} else {
			model.addAttribute("tuanList", tuanList);
		}
		// 页面跳转---------------s
		String page = resultPage(list, type);
		// 无结果页
		if (page.indexOf("noresult") != -1) {
			goToNoResult(dto, model, type);
		}
		return page;
		// 页面跳转---------------e
	}

	/**
	 * 错误页面
	 * 
	 * @param dto
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/youError")
	public String youError(YouSearchDto dto, Model model, HttpServletRequest request) {
		logger.info("--you-Error-page------------");
		String type = (String) request.getParameter("type");
		if(StringUtils.isBlank(dto.getSearchType())) { 
			dto.setSearchType("0");
		}
		goToNoResult(dto, model, type);
		return "youPage/noresult";
	}

	/**
	 * 去无结果页
	 * 
	 * @param model
	 * @param type
	 */
	private void goToNoResult(YouSearchDto dto, Model model, String type) {
		model.addAttribute("noResultKeyword", dto.getKeyWords());
		dto.setKeyWords("");
		dto.setSelectedCityId("-1");
		dto.setPageSize(12);
		B5MPageList list = null;
		// 酒店
		if ("1".equals(type)) {
			list = (B5MPageList) searchHotel(dto, null, model);
		} else if ("2".equals(type)) {// 攻略
			list = (B5MPageList) searchNote(dto, null, model);
		} else {// 旅游
			list = (B5MPageList) searchTrip(dto, null, model);
		}
		model.addAttribute("fromType", "noResult");
		model.addAttribute("pageList", list);
		model.addAttribute("searchDto", dto);
	}

	/**
	 * 搜索和列表结果页面 list 结果数据集,type 结果类型
	 * 
	 * @param list
	 * @param type
	 * @return
	 */
	private String resultPage(B5MPageList list, String type) {
		// TODO 无结果页
		if (list == null || list.getAll().size() == 0) {
			return "youPage/noresult";
		}

		// TODO 有结果页，具体的页面
		// 旅游
		if ("0".equals(type)) {
			return "/travel/travel";
		} else if ("1".equals(type)) {// 酒店
			return "youPage/hotelIndex";
		} else if ("2".equals(type)) {// 攻略
			return "youPage/noteIndex";
			// return "search_result";
		} else {// 默认

		}
		return "search_result";
	}

	/**
	 * 旅游搜索
	 * 
	 * @param dto
	 * @param request
	 * @return
	 */
	private Object searchTrip(YouSearchDto dto, HttpServletRequest request, Model model) {
		B5MPageList<YouGoods> travelList = travelService.findTravelByList(dto);
		model.addAttribute("pageList", travelList);
		model.addAttribute("xmlCity", Constants.XML_CITY);
		// setCtiyList(model, dto);
		return travelList;
	}

	/**
	 * 酒店搜索
	 * 
	 * @param dto
	 * @param request
	 * @return
	 */
	private Object searchHotel(YouSearchDto dto, HttpServletRequest request, Model model) {
		B5MPageList<YouHotel> hotelList = hotelService.getYouHotelList(dto);
		model.addAttribute("pageList", hotelList);
		// setCtiyList(model, dto);
		return hotelList;
	}

	/**
	 * 攻略搜索
	 * 
	 * @param dto
	 * @param request
	 * @return
	 */
	private Object searchNote(YouSearchDto dto, HttpServletRequest request, Model model) {
		B5MPageList<YouGuideNotes> pageList = guideNotesService.findGuideNotesBySearchResult(dto);
		model.addAttribute("pageList", pageList);
		// setCtiyList(model, dto);
		return pageList;
	}

	/**
	 * 设置城市列表
	 * 
	 * @param model
	 * @param dto
	 */
	private void setCtiyList(Model model, YouSearchDto dto) {
		// 酒店站点城市
		Map<String, String> hotelMap = hotelService.getHotelCity();
		model.addAttribute("hotelCity", hotelMap);

		// 旅游站点城市
		Map<String, String> youCity = goodsService.getYouCity(dto);
		model.addAttribute("tripCity", youCity);

		// 攻略站点城市
		Map<String, String> notesCity = guideNotesService.findNotesCity();
		model.addAttribute("notesCity", notesCity);
	}

	/**
	 * 各频道页面的入口(不包括首页)
	 * 
	 * @param dto
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(YouSearchDto dto, Model model, HttpServletRequest request,HttpServletResponse response) {
		// 搜索类型(0:旅游 1:酒店 2:攻略)、城市ID、页码
		logger.info("----list-----" + dto.getSearchType() + "-----" + dto.getSelectedCityId() + "----" + dto.getCurrPageNo());
		B5MPageList list = null;
		String type = dto.getSearchType();
		StringBuffer pagerUrl = new StringBuffer("list_");
		pagerUrl.append(dto.getSearchType()).append("_").append(dto.getSelectedCityId()).append("_");

		// 列表数据---------------s
		// 旅游首页
		if ("0".equals(dto.getSearchType().trim())) {
			// TODO 获取页面所需数据
			list = (B5MPageList) searchHotel(dto, null, model);
		} else if ("1".equals(dto.getSearchType().trim())) {// 酒店首页
			list = (B5MPageList) searchHotel(dto, request, model);
		} else if ("2".equals(dto.getSearchType().trim())) {// 攻略首页
			list = (B5MPageList) searchNote(dto, request, model);
		} else {
			// TODO 获取页面所需数据
			type = "0";
		}
		// 列表数据---------------e

		// 团购数据---------------s
		long mis = System.currentTimeMillis();
		String chnCity = Constants.XML_CITY.get(dto.getSelectedCityId());
		if ("-1".equals(dto.getSelectedCityId())) {
			chnCity = "";
		}
		dto.setSelectedCity(chnCity);
		List<TuanModel> tuanList = SF1Util.getTuanDataFromSF1(chnCity, type, 5);
		if (tuanList.size() == 0) {
			tuanList = SF1Util.getTuanDataFromSF1("", type, 5);
		}
		logger.info("---tuan-cost--->" + (System.currentTimeMillis() - mis));
		// 团购数据---------------e

		// 判断分页文件
		model.addAttribute("pagerUrl", pagerUrl.toString());
		model.addAttribute("fromType", "list");
		model.addAttribute("searchDto", dto);
		model.addAttribute("tuanList", tuanList);
		model.addAttribute("pageTag", type);
		String page = resultPage(list, type);
		// 无结果页
		if (page.indexOf("noresult") != -1) {
			goToNoResult(dto, model, type);
		}
		
		//cookieCity---------------------s
		String cookieCity = "";
		try {
					cookieCity = URLEncoder.encode(dto.getSelectedCity(),"UTF-8") + "|" + dto.getSelectedCityId();
		} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
		}
		String rootPath = ContextUtils.getInstance().getRootPathNew();
		Cookie ck1 = new Cookie("travelCity", cookieCity);
		Cookie ck2 = new Cookie("hotelCity", cookieCity);
		ck1.setMaxAge(MainController.cookieTime);
		ck2.setMaxAge(MainController.cookieTime);
		ck1.setDomain(rootPath);
		ck2.setDomain(rootPath);
		response.addCookie(ck1);
		response.addCookie(ck2);
		ck1 = null;
		ck2 = null;
		//cookieCity---------------------e	
		
		return page;
	}

	/**
	 * 旅游、酒店及攻略详情页的入口
	 * 
	 * @param dto
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(YouSearchDto dto, Model model, HttpServletRequest request) {
		logger.info("----detail-----" + dto.getSearchType() + "----" + dto.getGid());
		String type = dto.getSearchType();
		String gid = dto.getGid();
		YouHotel youHotel = null;
		YouGoods youGoods = null;
		YouGuideNotes youNotes = null;

		// 设置商品id及推荐数量
		dto.setClikcId(gid);
		dto.setPageSize(12);
		//设置为推荐模式
		dto.setIsRecommendtion("1");
		
		// 设置model属性
		model.addAttribute("searchDto", dto);
		model.addAttribute("fromType", "detail");

		// 旅游详情
		if ("0".equals(type)) {
			youGoods = goodsService.findYouGoodsById(dto);
			model.addAttribute("youGoods", youGoods);
			
			// TODO 旅游推荐
			B5MPageList<YouGoods> pageList = travelService.findTravelByList(dto);
			model.addAttribute("pageList", pageList);
		} else if ("1".equals(type)) {// 酒店详情
			youHotel = hotelService.getYouHotelById(dto);
			model.addAttribute("youGoods", youHotel);
			// 酒店详细信息
			List<YouHotelDetail> hotelDetailList = hotelDetailService.getYouHotelDetailById(dto);
			model.addAttribute("hotelDetailList", hotelDetailList);

			// TODO 推荐的酒店
			B5MPageList<YouHotel> hotelList = hotelService.getYouHotelList(dto);
			model.addAttribute("pageList", hotelList);
			model.addAttribute("menuCurPageTag", "hotel");
		} else {// 攻略详情
			youNotes = guideNotesService.findYouGuideNotesDetail(dto.getClikcId());
			model.addAttribute("detail", youNotes);
			if (youNotes != null) {
				// 热门景点
				List<YouGuideNotes> searchNotes = guideNotesService.findGuideNotesBySearchResul(youNotes.getCity(), "8");
				model.addAttribute("searchNotes", searchNotes);

				// 热门酒店
				dto.setPageSize(5);
				List<YouHotel> searchHotel = hotelService.findsNotesPageByList(youNotes.getCity(), dto);
				model.addAttribute("searchHotel", searchHotel);
				return "/youPage/notesDetail";
			}
		}

		// TODO 无结果页
		if (youHotel == null && youGoods == null && youNotes == null) {
			goToNoResult(dto, model, type);
			return "youPage/noresult";
		}
		return "/youPage/detail";
	}

	/**
	 * ajax方式获取列表数据，list的Ajax模式
	 * 
	 * @param dto
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/listAjax")
	public void listAjax(YouSearchDto dto, Model model, HttpServletResponse response) {
		String type = dto.getSearchType();
		PrintWriter out = null;
		B5MPageList list = null;
		StringBuffer buffer = new StringBuffer("");
		Gson gson = new Gson();
		// 旅游
		if ("0".equals(dto.getSearchType().trim())) {
			// TODO 获取Ajax所需数据

		} else if ("1".equals(dto.getSearchType().trim())) {// 酒店
			list = (B5MPageList) searchHotel(dto, null, model);
			buffer.append(gson.toJson(list));
		} else if ("2".equals(dto.getSearchType().trim())) {// 攻略
			list = (B5MPageList) searchNote(dto, null, model);
			buffer.append(gson.toJson(list));
		}

		try {
			out = response.getWriter();
			// 返回数据
			out.print(buffer.toString());
		} catch (Exception e) {
			list = new B5MPageList();
			list.setResponseCode("01");
			list.setResponseDetail(e.toString());
			out.print(gson.toJson(list));
			e.printStackTrace();
			// TODO 异常处理
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 火车票
	 * 
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequestMapping("/train")
	public String train(YouSearchDto dto, Model model) {
		model.addAttribute("fromType", "detail");
		model.addAttribute("pageTag", "4");
		model.addAttribute("searchDto", dto);
		return "youPage/train";
	}

	/**
	 * 机票
	 * 
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequestMapping("/flight")
	public String flight(YouSearchDto dto, Model model) {
		model.addAttribute("fromType", "detail");
		model.addAttribute("pageTag", "3");
		model.addAttribute("searchDto", dto);
		return "youPage/flight";
	}

	/**
	 * 获取推荐的数据——团购提供 或者 广告位
	 * 
	 * @param dto
	 */
	private Object recommend(YouSearchDto dto, String type) {
		List list = null;
		// 0:旅游 1:酒店
		if ("0".equals(type)) {
			list = hotelService.getRecommendHotel(dto);
		} else {
			// TODO 旅游推荐的Service

		}

		return list;
	}
}
