package com.maiyajf.base.utils.activemq;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import com.maiyajf.base.utils.log.ExceptionLogger;

public class ActivemqReceiveTask implements Runnable{
	ActivemqUtil activeUtil;
	BaseActivemqListener listener;
	String name,type;
	public ActivemqReceiveTask(ActivemqUtil activeUtil,String name, String type, BaseActivemqListener listener) {
		this.listener=listener;
		this.name=name;
		this.type=type;
		this.activeUtil=activeUtil;
	}
	@Override
	public void run() {
		while(true){
			//接收主题
			if(ActivemqUtil.TOPIC.equals(type)){
				//阻塞接收，直到获取到为主
				ObjectMessage objmessage=activeUtil.getTopicMessage(name);
				if(objmessage!=null){
					try {
						//获取到objmessage不为空以后，调用listener的onBaseMessage函数
						listener.onBaseMessage(activeUtil.objMessageToObj(objmessage),name,type);
					} catch (JMSException e) {
						ExceptionLogger.error("","消息队列接收topic异常",e);
					}
				}
			}else{//接收队列
				//阻塞接收，直到获取到为主
				ObjectMessage objmessage=activeUtil.getQueueMessage(name);
				if(objmessage!=null){
					try {
						//获取到mapmessage不为空以后，调用listener的onBaseMessage函数
						listener.onBaseMessage(activeUtil.objMessageToObj(objmessage),name,type);
					} catch (JMSException e) {
						ExceptionLogger.error("","消息队列接收queue异常",e);
					}
				}
			}
		}
	}

}
