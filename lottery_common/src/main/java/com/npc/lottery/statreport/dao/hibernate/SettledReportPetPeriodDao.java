package com.npc.lottery.statreport.dao.hibernate;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.npc.lottery.common.dao.HibernateDao;
import com.npc.lottery.statreport.dao.interf.ISettledReportPetPeriodDao;
import com.npc.lottery.statreport.entity.DeliveryReportPetPeriod;

public class SettledReportPetPeriodDao extends HibernateDao<DeliveryReportPetPeriod ,Long> implements ISettledReportPetPeriodDao{
	private static Logger log = Logger.getLogger(SettledReportPetPeriodDao.class);
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void insertSettledReportPetPeriods(DeliveryReportPetPeriod reportInfo,String schema){
    	if (StringUtils.isNotEmpty(schema)) {
			schema = schema + ".";
		}
    	
		String sqlLog = "insert into "+schema+"TB_S_REPORT_PET_Period (ID,USER_ID,USER_TYPE,BETTING_USER_ID,BETTING_USER_TYPE,PARENT_USER_TYPE,"
				+ "ACCOUNT,CHNAME,COUNT,TOTAL_MONEY,MONEY_RATE_AGENT,MONEY_RATE_GENAGENT,MONEY_RATE_STOCKHOLDER,MONEY_RATE_BRANCH,"
				+ "MONEY_RATE_CHIEF,RATE_MONEY,MEMBER_AMOUNT,SUBORDINATE_AMOUNT_WIN,SUBORDINATE_AMOUNT_BACKWATER,"
				+ "REALWIN,REAL_BACKWATER,COMMISSION,PERIODS_NUM,REAL_RESULT_PER,BETTING_DATE,LOTTERY_TYPE) "+
						" VALUES (SEQ_TB_S_REPORT_PET_Period.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] parameters = new Object[] { reportInfo.getUserID(), reportInfo.getUserType(), reportInfo.getBettingUserID(), 
				reportInfo.getBettingUserType(), reportInfo.getParentUserType(),reportInfo.getSubordinate(),
				reportInfo.getUserName(),reportInfo.getTurnover(),reportInfo.getAmount(),reportInfo.getMoneyRateAgent(),
				reportInfo.getMoneyRateGenAgent(),reportInfo.getMoneyRateStockholder(),reportInfo.getMoneyRateBranch(),
				reportInfo.getMoneyRateChief(),reportInfo.getRateMoney(),reportInfo.getMemberAmount(),
				reportInfo.getSubordinateAmountWin(),reportInfo.getSubordinateAmountBackWater(),reportInfo.getRealWin(),
				reportInfo.getRealBackWater(),reportInfo.getCommission(),reportInfo.getPeriodsNum(),
				reportInfo.getRealResultPer(),reportInfo.getBettingDate(),reportInfo.getLotteryType()};
		jdbcTemplate.update(sqlLog, parameters);
    }
    
    @Override
    public void deleteSettledReportPet(String periodsNum,String lotteryType,String schema){
    	if (StringUtils.isNotEmpty(schema)) {
    		schema = schema + ".";
    	}
    	String sqlLog = "delete "+schema+"TB_S_REPORT_PET_Period where PERIODS_NUM=? and LOTTERY_TYPE=?";
    	Object[] parameters = new Object[] { periodsNum,lotteryType};
    	jdbcTemplate.update(sqlLog, parameters);
    }
    
    @Override
    public void flush(){
    	this.getSession().flush();
    }
    
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
