package com.springtest.demo.service;

import com.springtest.demo.entity.Area;

import java.util.List;

/**
 * @author xianghaochen
 * @date 2020/8/26 0:53
 */
public interface AreaService {

    /**
     * 获取区域列表
     * @return
     */
    List<Area> getAreaList();

    /**
     * 通过区域ID获取区域信息
     * @param areaId
     * @return
     */
    Area getAreaById(int areaId);

    /**
     * 增加区域信息
     * @param area
     * @return
     */
    boolean addArea(Area area);

    /**
     * 修改区域信息
     * @param area
     * @return
     */
    boolean modifyArea(Area area);

    /**
     * 删除区域信息
     * @param areaId
     * @return
     */
    boolean deleteArea(int areaId);
}
