package com.springtest.demo.dao;

import com.springtest.demo.entity.Area;

import java.util.List;

/**
 * @author xianghaochen
 * @date 2020/8/8 18:01
 */
public interface AreaDao {

    /**
     * 列出区域列表
     * @return
     */
    List<Area> queryArea();

    /**
     * 根据Id列出查询具体区域
     * @param areaId
     * @return
     */
    Area queryAreaById(int areaId);

    /**
     * 插入区域信息
     * @param area
     * @return
     */
    int insertArea(Area area);

    /**
     * 更新区域信息
     * @param area
     * @return
     */
    int updateArea(Area area);

    /**
     * 删除区域信息
     * @param areaId
     * @return
     */
    int deleteArea(int areaId);

}
