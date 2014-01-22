package com.b5m.you.ctrl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.b5m.web.core.AbstractBaseController;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.Constants;
import com.b5m.web.core.ContextUtils;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.TuanModel;
import com.b5m.you.model.YouGoods;
import com.b5m.you.service.IYouTravelService;

@Controller
public class TravelController extends AbstractBaseController {

	public static Logger logger = Logger.getLogger(TravelController.class);

	@Resource
	private IYouTravelService travelService;
	

	/**
	 * 旅游列表
	 * 
	 * @param dto
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/travel")
	public String travelPost(YouSearchDto dto, Model model, HttpServletRequest request,HttpServletResponse response) {
		if (("-1").equals(dto.getSelectedCityId())) {
			// 获取本地城市
			Constants.getLocate(dto);
		}

		int page = dto.getCurrPageNo();
		// 旅游列表页数据
		B5MPageList<YouGoods> pageList = travelService.findTravelByList(dto);
		// 大于总页数 add by duanyu
		if (page > pageList.getTotalPages()) {
			dto.setCurrPageNo(pageList.getTotalPages());
			pageList = travelService.findTravelByList(dto);
		}
		// 小于0
		if (page < 0) {

		}

		// 查询无数据，跳转错误页面
		if (pageList.getAll() == null || pageList.getAll().size() == 0) {
			model.addAttribute("pageTag", 0);
			return "forward:/youError";
		}

		model.addAttribute("pageList", pageList);

		// 旅游列表团购旅游数据
		String city = "";
		if (!("-1").equals(dto.getSelectedCityId()) || StringUtils.isBlank(city)) {
			city = Constants.XML_CITY.get(dto.getSelectedCityId());
			dto.setSelectedCity(city);
		}
		List<TuanModel> tuanTravel = travelService.findTravelTuan(city, "0", 3);
		logger.info("旅游列表团购旅游数据:" + tuanTravel.size());
		model.addAttribute("tuanTravel", tuanTravel);

		model.addAttribute("tripType", Constants.TRIP_EN_CHN_MAP);
		model.addAttribute("fromType", "list");
		model.addAttribute("pageType", "travel");
		model.addAttribute("searchDto", dto);
		model.addAttribute("pageTag", 0);
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
		ck1 = null;
		ck2 = null;
		//cookieCity---------------------e		
		return "/travel/travel";
	}
}
