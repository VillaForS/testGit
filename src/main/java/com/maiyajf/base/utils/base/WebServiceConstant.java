/** 
 * WebServiceConstant.java
 * create on 2011-4-7
 * Copyright 2015 Todaysteel All Rights Reserved.
 */
package com.maiyajf.base.utils.base;


/**
 * WebService常量类
 * 
 * @author <a href="mailto:service@todaysteel.com">wangjianglin</a>
 * @since version1.0
 */
public class WebServiceConstant {
	public static final String SUCCESSCODE = "000000";      //成功标识
	public static final String FAILCODE = "111111";		    //失败标识
	
	public static final String CONNECTTIMEOUT = "222222";	//超时标识
	public static final String SUCEXIT = "333333";   //您已成功退出！;
	
	public static final String OPESUC = "";	//操作成功
//	public static final String OPEERR = "555555";   //操作失败！;
	
	public static final String USERORPASSNORITHT = "ERR001";//用户名或密码不正确
	public static final String INTERFACESERIALERR = "ERR002";//操作失败，仓库与平台接口序列号不一致！
	public static final String EXCEPTION = "ERR003";//出现异常，操作失败
	public static final String NOAUTHORITY = "ERR004";//仓库没有操作该接口的权限
	public static final String NOHTTPSACCESS = "ERR005";  //不是以https访问
	public static final String NOCERTIFICATE = "ERR006";  //没有CA证书
	public static final String CASIGNATUREERROR = "ERR007";  //CA证书信息错误
	public static final String NOTCOSTOMER = "ERR300";		 //发送短信时，没有仓库增值账户
	
//	public static HashMap<String,String> map=new HashMap<String,String>();
//	static{
//		map.put(SUCCESSCODE,"操作成功!");
//		map.put(FAILCODE,"操作失败!");
//		map.put(CONNECTTIMEOUT,"连接超时!");
//		map.put(USERORPASSNORITHT,"用户名或密码不正确!");
//			
//		map.put("ERR050","找不到提单主表ID!");
//		map.put("ERR051","输入格式不正确!");
//		map.put("ERR052","仓库不存在或未上线!");
//		map.put("ERR053","仓库没有该会员!");
//		
//		map.put("ERR054","该提单已存在");
//		map.put("ERR055","该提单不存在");
//		map.put("ERR056","撤牌出现异常，撤牌失败！");
//		map.put("ERR057","可开单量不足");
//		map.put("ERR058","已质押，不可重复新增质押");
//		map.put("ERR059","该入库单重量余额!=入库重量或仓库锁定！=0或可用量<入库重量，不能修改属性");
//		map.put("ERR060","所有仓单的可用量都不足以撤牌");
//		map.put("ERR061","仓单没有挂牌资源");
//		map.put("ERR062","挂牌资源不足以撤牌");
//		map.put("ERR063","该提单已作废");
//		map.put("ERR064","提单已出库");
//		map.put("ERR065","该提单已删除");
//		map.put("ERR066","该提单有仓单被冻结");
//		map.put("ERR067","该会员没有质押记录");
//		map.put("ERR068","仓单不存在");
//		map.put("ERR069","该会员没有向仓库申请关联");
//		map.put("ERR070","可用量小于入库量差额");
//		map.put("ERR071","本次数据不合法");
//		map.put("ERR072","该提单已作废");
//		map.put("ERR073","密码不正确");
//		map.put("ERR074","证件类型不对");
//		map.put("ERR075","证件号不对");
//		map.put("ERR076","没有向仓库申请关联的会员");
//		map.put("ERR077","没有过户单信息");
//		map.put("ERR078","过户单不存在");
//		map.put("ERR079","买方会员ID不对");
//		map.put("ERR080","必填项不能为空");
//		map.put("ERR081","入库重量小于剩余重量或者仓库锁定重量大于当前余额");
//		map.put("ERR082","业务类型只能为0、 1或者 2");
//		map.put("ERR083","业务类型不为0，结束标记必须是0或者1");
//		map.put("ERR084","合同已出库");
//		map.put("ERR085","电子单据（线上出库）操作出现异常，修改仓单失败！");
//		map.put("ERR086","线下出库操作出现异常，修改仓单失败！");
//		map.put("ERR087","修改属性操作出现异常，修改仓单失败！");
//		map.put("ERR088","非修改属性操作''出现异常，修改仓单失败！");
//		map.put("ERR089","该提单未验证通过");
//		map.put("ERR090","提单已过期");
//		map.put("ERR091","实际出库/过户重量太多");
//		map.put("ERR092","可用量小于实际溢出量");
//		map.put("ERR093","平台无该会员");
//		map.put("ERR094","新增父仓单出现异常，上传仓单失败！");
//		map.put("ERR095","合并父仓单出现异常，上传仓单失败！");
//		map.put("ERR999","系统异常");
//	}
//
//	public static String getValueByKey(String key){
//		String va=map.get(key);
//		if(va==null)
//			return  "出现异常！";
//		else
//			return va;
//	}
	
}
