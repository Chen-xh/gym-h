package com.chen.gym.service.contest;


import com.chen.gym.bean.Contest;


import java.util.List;
import java.util.Map;

public interface ContestService {

    /**
     * 查询所有
     */
    List<Contest> findAll();
    /**
     * 根据target查询所有
     */
    List<Contest> findByTarget(Integer target);
    /**
     * 多条件查询
     */
    List<Contest> select(Contest contest);
    /**
     * 根据id查询
     */
    Contest findContestById(Long id);
    /**
     * 安排器材
     */
    void arrayEquipment(Long id ,Long eid);
    /**
     * 安排场地
     */
    void arrayField(Long id,Long fid);
    /**
     * 安排裁判
     */
    void arrayJudgment(Long id,Long jid);
    /**
     * 添加
     */
    void add(Contest contest);
    /**
     * 删除
     */
    void delete(Long id);
    /**
     * 修改
     */
    void update(Contest contest);
    /**
     * 管理员复核
     */
    void review(Long cid);

}
