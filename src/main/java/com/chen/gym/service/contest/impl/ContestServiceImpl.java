package com.chen.gym.service.contest.impl;

import com.chen.gym.bean.Contest;
import com.chen.gym.dao.contest.ContestDao;
import com.chen.gym.exception.CustomizeRuntimeException;
import com.chen.gym.exception.MyCustomizeErrorCode;
import com.chen.gym.service.contest.ContestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @author CHEN
 * @date 2021/5/27
 */
@Service
public class ContestServiceImpl implements ContestService {
    @Resource
    private ContestDao contestDao;

    private static final Logger PLOG = LoggerFactory.getLogger(ContestServiceImpl.class);

    @Override
    public List<Contest> findAll() {
        return contestDao.findAll();
    }

    @Override
    public List<Contest> select(Contest contest) {

        StringBuilder sql = new StringBuilder("SELECT * FROM contest where 1=1 ");
        try {
            //通过反射遍历对象中的属性
            Class cls = contest.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields) {
                //设置属性可访问，破坏私有
                f.setAccessible(true);
                if (f.get(contest) != null) {
//                    System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(contest));
                    if (f.getName().equals("date")||f.getName().equals("openTime")) {
//                       时间要具体处理。。
                    } else {
                        sql.append(" and ").append(f.getName()).append(" like '%").append(f.get(contest)).append("%'");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        PLOG.info("Select >> "+sql.toString());
        return contestDao.select(sql.toString());
    }

    @Override
    public Contest findContestById(Long id) {
        Contest contest = contestDao.findContestById(id);
        if (contest == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Contest);
        }
        return contest;
    }

    @Override
    public void arrayEquipment(Long id,Long eid) {
        Contest contest = contestDao.findContestById(id);
        if (contest == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Contest);
        }
        contestDao.updateEquipment(eid,id);
        //重置为未复核
        contestDao.updateTarget(1,id);
    }

    @Override
    public void arrayField(Long id,Long fid) {
        Contest contest = contestDao.findContestById(id);
        if (contest == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Contest);
        }
        contestDao.updatePlace(fid,id);
        //重置为未复核
        contestDao.updateTarget(1,id);
    }

    @Override
    public void add(Contest contest) {
        contest.setDate(new Date());
        contestDao.add(contest);
    }

    @Override
    public void delete(Long id) {
        Contest contest = contestDao.findContestById(id);
        if (contest == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Contest);
        }
        contestDao.delete(id);
    }

    @Override
    public void update(Contest contest) {
        Contest item = contestDao.findContestById(contest.getCid());
        if (item == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Contest);
        }
        //重置为未复核
        contest.setTarget(1);
        contest.setDate(new Date());
        contestDao.update(contest);
    }

    @Override
    public void review(Long cid) {
        Contest item = contestDao.findContestById(cid);
        if (item == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Contest);
        }
        contestDao.updateTarget(2,cid);
    }
}
