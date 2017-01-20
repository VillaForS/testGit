package com.maiyajf.base.utils.activemq;

import java.io.Serializable;
import java.util.TimerTask;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import com.maiyajf.base.utils.log.ExceptionLogger;

public class ActivemqSendTask extends TimerTask{
	String name,type;
	Serializable obj;
	ActivemqUtil activemqUtil;
	public ActivemqSendTask(String name, String type) {
		this.name=name;
		this.type=type;
	}
	public ActivemqSendTask(ActivemqUtil activemqUtil, String name, Serializable obj, String type) {
		this.name=name;
		this.type=type;
		this.obj=obj;
		this.activemqUtil=activemqUtil;
	}
	@Override
	public void run() {
		//发送主题
		if(ActivemqUtil.TOPIC.equals(type)){
			//重试次数，只要有一次成功就结束
			for(int i=0;i<ActivemqUtil.RetryCount;i++){
			    try {
			    	//获取ObjectMessage
			    	ObjectMessage message = activemqUtil.objToObjMessage(obj);
			    	//发送mapmessage
			    	activemqUtil.getTopicProducer(name).send(message);
			    	return;
			    }
			    catch (JMSException e) {
			    	//抛出异常，暂停当前线程，然后继续执行
			    	ExceptionLogger.error(e);
			    	try{
			    		Thread.sleep(ActivemqUtil.WaitTime);
			    	}catch(InterruptedException ex){
			    		ExceptionLogger.error(ex);
			    	}
			    }
			}
		}
		//发送队列
		if(ActivemqUtil.QUEUE.equals(type)){
			for(int i=0;i<ActivemqUtil.RetryCount;i++){
			    try {
			    	//获取ObjectMessage
			    	ObjectMessage message = activemqUtil.objToObjMessage(obj);
			    	//发送mapmessage
			    	activemqUtil.getQueueProducer(name).send(message);
			    	return;
			    }
			    catch (JMSException e) {
			    	//抛出异常，暂停当前线程，然后继续执行
			    	ExceptionLogger.error(e);
			    	try{
			    		Thread.sleep(ActivemqUtil.WaitTime);
			    	}catch(InterruptedException ex){
			    		ExceptionLogger.error(ex);
			    	}
			    }
			}
		}	
	}

}
