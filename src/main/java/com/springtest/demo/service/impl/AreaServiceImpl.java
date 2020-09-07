package com.springtest.demo.service.impl;

import com.springtest.demo.config.service.TransactionManagementConfiguration;
import com.springtest.demo.dao.AreaDao;
import com.springtest.demo.entity.Area;
import com.springtest.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;
import java.util.List;

/**
 * @author xianghaochen
 * @date 2020/8/26 0:57
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }

    @Override
    public Area getAreaById(int areaId) {
        return areaDao.queryAreaById(areaId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addArea(Area area) {
        if (area.getAreaName() != null && !"".equals(area.getAreaName())) {
            area.setCreateTime(new Date());
            area.setUpdateTime(new Date());

            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setName("AreaBeforeInsert");
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = transactionManager.getTransaction(def);

            try {
                int rowNum = areaDao.insertArea(area);
                if (rowNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("插入区域信息失败！");
                }
            } catch (Exception e) {
                /*// 手动回滚
                transactionManager.rollback(status);*/
                throw new RuntimeException("插入区域信息失败！" + e.getMessage());
            }
        } else {
            throw new RuntimeException("区域信息不能为为空！");
        }

        // @Transactional并不会回滚所有事务，默认接收RuntimeException去回滚
        // 或者改成这样  @Transactional(noRollbackFor = Exception.class)
        // 需要在Transaction注解指定rollBackFor或者在方法中显式的rollback
        // 事务场景中，抛出异常被catch后，如果需要回滚，一定要手动回滚事务。
    }

    @Override
    public boolean modifyArea(Area area) {
        // 空值判断，主要是areaId不为空
        if (area.getAreaId() != null && area.getAreaId() > 0) {
            // 设置默认值
            area.setUpdateTime(new Date());
            try {
                // 更新区域信息
                int rowNum = areaDao.updateArea(area);
                if (rowNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("更新区域信息失败！");
                }
            } catch (Exception e) {
                throw new RuntimeException("更新区域信息失败！" + e.toString());
            }
        } else {
            throw new RuntimeException("区域信息不能为空！");
        }
    }

    @Override
    public boolean deleteArea(int areaId) {
        if (areaId > 0) {
            try {
                // 删除区域信息
                int rowNum = areaDao.deleteArea(areaId);
                if (rowNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("删除区域信息失败！");
                }
            } catch (Exception e) {
                throw new RuntimeException("删除区域信息失败！");
            }
        } else {
            throw new RuntimeException("区域Id不能为空！");
        }
    }
}
