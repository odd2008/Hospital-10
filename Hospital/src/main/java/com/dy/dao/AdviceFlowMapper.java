package com.dy.dao;

import com.dy.model.AdviceFlow;

public interface AdviceFlowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ssm_adviceflow
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer adviceflowid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ssm_adviceflow
     *
     * @mbggenerated
     */
    int insert(AdviceFlow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ssm_adviceflow
     *
     * @mbggenerated
     */
    int insertSelective(AdviceFlow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ssm_adviceflow
     *
     * @mbggenerated
     */
    AdviceFlow selectByPrimaryKey(Integer adviceflowid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ssm_adviceflow
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AdviceFlow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ssm_adviceflow
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AdviceFlow record);
}