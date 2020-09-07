package com.springtest.demo.web;

import com.springtest.demo.entity.Area;
import com.springtest.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xianghaochen
 * @date 2020/8/26 1:32
 */
@RestController
@RequestMapping("/superadmin")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    private Map<String, Object> listArea() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Area> list = areaService.getAreaList();
        modelMap.put("areaList", list);
        return modelMap;
    }

    @RequestMapping(value = "/getareabyid", method = RequestMethod.GET)
        private Map<String, Object> getAreaById(Integer areaId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Area area = areaService.getAreaById(areaId);
        modelMap.put("area", area);
        return modelMap;
    }

    /**
     * 添加区域信息
     */
    @RequestMapping(value = "/addarea", method = RequestMethod.POST)
    private Map<String, Object> addArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", areaService.addArea(area));
        return modelMap;

    }

    /**
     * 修改区域信息
     */
    @RequestMapping(value = "/modifyarea", method = RequestMethod.POST)
    private Map<String, Object> modifyArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 修改区域信息
        modelMap.put("success", areaService.modifyArea(area));
        return modelMap;
    }

    /**
     * 删除区域信息
     */
    @RequestMapping(value = "/removearea", method = RequestMethod.GET)
    private Map<String, Object> remmoveArea(Integer areaId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", areaService.deleteArea(areaId));
        return modelMap;
    }


}
