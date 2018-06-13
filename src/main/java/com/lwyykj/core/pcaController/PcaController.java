package com.lwyykj.core.pcaController;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lwyykj.core.bean.pca.Areas;
import com.lwyykj.core.bean.pca.Cities;
import com.lwyykj.core.pca.service.PcaService;

@Controller
@RequestMapping("/pca")
public class PcaController {
	@Resource
	private PcaService pcaService;

	@RequestMapping("/city.aspx")
	@ResponseBody
	public List<Cities> findAllCity(String province_id) {
		List<Cities> cities = pcaService.findByPid(province_id);
		return cities;
	}

	@RequestMapping("/county.do")
	@ResponseBody
	public List<Areas> findAllCounty(String city_id) {
		List<Areas> areas = pcaService.findByCid(city_id);
		return areas;
	}
}
