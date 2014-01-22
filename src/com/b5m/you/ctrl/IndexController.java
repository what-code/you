package com.b5m.you.ctrl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.b5m.web.core.AbstractBaseController;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.Constants;
import com.b5m.web.core.ContextUtils;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.TuanModel;
import com.b5m.you.model.YouGoods;
import com.b5m.you.model.YouGuideNotes;
import com.b5m.you.model.YouHotel;
import com.b5m.you.model.YouTravel;
import com.b5m.you.service.IYouGuideNotesService;
import com.b5m.you.service.IYouHotelService;
import com.b5m.you.service.IYouTravelService;

@Controller
public class IndexController extends AbstractBaseController {

	public static Logger logger = Logger.getLogger(IndexController.class);

	@Resource
	private IYouTravelService travelService;

	@Resource
	private IYouHotelService hotelService;

	@Resource
	private IYouGuideNotesService guideNotesService;

	/**
	 * 首页
	 * 
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String indexPost(YouSearchDto dto, Model model,HttpServletResponse response) {
		logger.info("cityId：" + dto.getSelectedCityId());

		// 当季热门城市
		List<YouTravel> hotCity = travelService.findIndexHotTravel("0");
		logger.info("总条数：" + hotCity.size());
		model.addAttribute("hotCity", hotCity);

		// 当季热门类型
		List<YouTravel> hotTravle = travelService.findIndexHotTravel("1");
		logger.info("总条数：" + hotTravle.size());
		model.addAttribute("hotTravle", hotTravle);

		// 默认请求国内游数据
		dto.setAjaxType(Constants.PERIPHERY_TRAVEL);
		// 首页旅游数据展示8条数据
		dto.setPageSize(8);
		B5MPageList<YouGoods> travelList = travelService.findTravelByList(dto);
		logger.info("总页数：" + travelList.getTotalPages() + "---总条数：" + travelList.getTotalCount());
		model.addAttribute("travel", travelList.getAll());
		// 还原
		dto.setAjaxType("all");

		// 首页团购旅游数据
		List<TuanModel> tuanTravel = travelService.findTravelTuan("", "0", 1);
		logger.info("团购旅游数据:" + tuanTravel.size());
		model.addAttribute("tuanTravel", tuanTravel);

		// 首页酒店展示6条数据
		dto.setPageSize(6);
		B5MPageList<YouHotel> hotelList = hotelService.getYouHotelList(dto);
		logger.info("总页数：" + hotelList.getTotalPages() + "---总条数：" + hotelList.getTotalCount());
		model.addAttribute("hotel", hotelList.getAll());

		// 首页团购酒店数据
		List<TuanModel> tuanHotel = travelService.findTravelTuan("", "1", 1);
		logger.info("团购酒店数据:" + tuanHotel.size());
		model.addAttribute("tuanHotel", tuanHotel);

		// 首页攻略展示7条数据
		dto.setPageSize(7);
		B5MPageList<YouGuideNotes> guideNotesList = guideNotesService.findGuideNotesBySearchResult(dto);
		logger.info("总页数：" + guideNotesList.getTotalPages() + "---总条数：" + guideNotesList.getTotalCount());
		model.addAttribute("guideNotes", guideNotesList.getAll());
		model.addAttribute("pageType", "index");
		Constants.getLocate(dto);
		model.addAttribute("searchDto", dto);
		 
		//cookieCity---------------------s
		String cookieCity = "";
		try {
			cookieCity = URLEncoder.encode(dto.getSelectedCity() + "|" + dto.getSelectedCityId(),"UTF-8");
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
		//help gc
		ck1 = null;
		ck2 = null;
		//cookieCity---------------------e
		return "/index/index";
	}

	/**
	 * 首页
	 * 
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequestMapping("/index0")
	public String indexAjax(YouSearchDto dto, Model model,HttpServletResponse response) {
		// 首页团购旅游数据
		List<TuanModel> tuanTravel = travelService.findTravelTuan("", "0", 1);
		logger.info("团购旅游数据:" + tuanTravel.size());
		model.addAttribute("tuanTravel", tuanTravel);
				
		// 首页团购酒店数据
		List<TuanModel> tuanHotel = travelService.findTravelTuan("", "1", 1);
		logger.info("团购酒店数据:" + tuanHotel.size());
		model.addAttribute("tuanHotel", tuanHotel);
		return "/index/indexAjax";
	}
	
	/**
	 * 首页旅游ajax请求
	 * 
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequestMapping("/indexTravelAjax")
	public @ResponseBody
	Object indexPostTravelAjax(YouSearchDto dto, Model model) {
		// 首页旅游数据展示8条数据
		dto.setPageSize(8);
		B5MPageList<YouGoods> travelList = travelService.findTravelByList(dto);
		logger.info("总页数：" + travelList.getTotalPages() + "---总条数：" + travelList.getTotalCount());
		/*for (YouGoods you : travelList.getAll()) {
			logger.info(you.getName() + " " + you.getImgurl() + " " + you.getSalesPrice());
		}*/
		return travelList;
	}

	/**
	 * 首页酒店ajax请求
	 * 
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequestMapping("/indexHotelAjax")
	public @ResponseBody
	Object indexPostHotelAjax(YouSearchDto dto, Model model) {
		// 首页酒店展示6条数据
		dto.setPageSize(6);
		B5MPageList<YouHotel> hotelList = hotelService.getYouHotelList(dto);
		logger.info("总页数：" + hotelList.getTotalPages() + "---总条数：" + hotelList.getTotalCount());
		/*for (YouHotel you : hotelList.getAll()) {
			logger.info(you.getName() + " " + you.getImgurl() + " " + you.getSalesPrice());
		}*/
		return hotelList;
	}
	
	@RequestMapping("/indexHotCity")
	public @ResponseBody
	Object indexHotCityAjax(YouSearchDto dto, Model model) {
		List<YouTravel> hotCity = travelService.findIndexHotTravel("0");
		return hotCity;
	}
	
	@RequestMapping("/indexHotType")
	public @ResponseBody
	Object indexHotType(YouSearchDto dto, Model model) {
		List<YouTravel> hotCity = travelService.findIndexHotTravel("1");
		return hotCity;
	}
}
