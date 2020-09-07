package com.springtest.demo.dao;

import com.springtest.demo.entity.Area;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xianghaochen
 * @date 2020/8/24 0:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class AreaDaoTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    void queryArea() {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2, areaList.size());
    }

    @Test
    void queryAreaById() {
        Area area = areaDao.queryAreaById(1);
        assertEquals("东苑", area.getAreaName());
    }

    @Test
    void insertArea() {
        Area area = new Area();
        area.setAreaName("北苑");
        area.setPriority(1);
        area.setCreateTime(new Date());
        area.setUpdateTime(new Date());
        int rowNum = areaDao.insertArea(area);
        assertEquals(1, rowNum);
    }

    @Test
    void updateArea() {
        Area area = new Area();
        area.setAreaId(3);
        area.setAreaName("南苑");
        area.setUpdateTime(new Date());
        int rowNum = areaDao.updateArea(area);
        assertEquals(1, rowNum);
    }

    @Test
    void deleteArea() {
        int rowNum = areaDao.deleteArea(3);
        assertEquals(1, rowNum);
    }
}