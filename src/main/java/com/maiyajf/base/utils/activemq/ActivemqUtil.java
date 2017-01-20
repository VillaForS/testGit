package com.maiyajf.base.utils.activemq;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.scheduling.annotation.Async;

import com.maiyajf.base.utils.log.ExceptionLogger;

public class ActivemqUtil{
	public static final String QUEUE="queue";
	public static final String TOPIC="topic";
	public static final int RetryCount = 3;
	public static final int WaitTime = 10000;
	
	private String brokerUrl;
	private List<BaseActivemqListener> listeners;
	//Activemq工厂
	private ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory();
	//创建连接
	private Connection connection = null;
	//创建会话
	private Session session;
	//发送消息的线程池
	private ExecutorService send_Service=Executors.newCachedThreadPool();
	//获取数据的定时器
	private ExecutorService receive_timer = Executors.newCachedThreadPool();
	//存放发送消息的队列生产者
	private Map<String, MessageProducer> sendQueues = new ConcurrentHashMap<String, MessageProducer>();
	//存放获取消息的队列消费者
	private Map<String, MessageConsumer> getQueues = new ConcurrentHashMap<String, MessageConsumer>();
	//存放发送主题的生产者
	private Map<String, MessageProducer> sendTopics = new ConcurrentHashMap<String, MessageProducer>();
	//存放获取主题的消费者
	private Map<String, MessageConsumer> getTopics = new ConcurrentHashMap<String, MessageConsumer>();
	
	public ActivemqUtil(String brokerUrl,List<BaseActivemqListener> listeners) throws JMSException{
		this.brokerUrl=brokerUrl;this.listeners=listeners;
		init();
	}
	
	public void init() throws JMSException {
		connectionFactory.setBrokerURL(brokerUrl);
    	connection = connectionFactory.createConnection();
    	connection.start();
    	//创建session，第一个参数是否启用事务，第二个消息确认方式
    	session = connection.createSession(Boolean.FALSE.booleanValue(),Session.AUTO_ACKNOWLEDGE);
    	if(listeners!=null){
    		for(BaseActivemqListener listener:listeners){
    			for(String queue:listener.getQueues()){
    				ActivemqReceiveTask task=new ActivemqReceiveTask(this,queue,ActivemqUtil.QUEUE,listener);
    				receive_timer.execute(task);
    			}
    			for(String topic:listener.getTopics()){
    				ActivemqReceiveTask task=new ActivemqReceiveTask(this,topic,ActivemqUtil.TOPIC,listener);
    				receive_timer.execute(task);
    			}
    		}
    	}
	}
	/**
	 * 获取队列对应的生产者，如果已经创建对应的生产者，直接返回已有producer
	 * @param name,队列的名称
	 * @return
	 */
	public MessageProducer getQueueProducer(String name) {
		if (sendQueues.containsKey(name))
			return ((MessageProducer)sendQueues.get(name));
	    try
	    {
	    	Destination destination = session.createQueue(name);
	    	MessageProducer producer = session.createProducer(destination);
	    	sendQueues.put(name, producer);
	    	return producer;
	    } catch (JMSException e) {
	    	ExceptionLogger.error(e);
	    }	 
	    return ((MessageProducer)sendQueues.get(name));
	}
	/**
	 * 获取队列对应的消费者，如果已经创建对应的消费者，直接返回已有consumer
	 * @param name队列的名称
	 * @return
	 */
	public MessageConsumer getQueueConsumer(String name) {
	    if (getQueues.containsKey(name))
	    	return ((MessageConsumer)getQueues.get(name));
	    try
	    {
	    	Destination destination = session.createQueue(name);
	    	MessageConsumer consumer = session.createConsumer(destination);
	    	getQueues.put(name, consumer);
	    	return consumer;
	    } catch (JMSException e) {
	    	ExceptionLogger.error(e);
	    } 
    	return ((MessageConsumer)getQueues.get(name));
	}
	
	
	/**
	 * 获取主题对应的生产者，如果已经创建对应的生产者，直接返回已有producer
	 * @param name,队列的名称
	 * @return
	 */
	public MessageProducer getTopicProducer(String name) {
		if (sendTopics.containsKey(name))
			return ((MessageProducer)sendTopics.get(name));
	    try
	    {
	    	Destination destination = session.createTopic(name);
	    	MessageProducer producer = session.createProducer(destination);
	    	sendTopics.put(name, producer);
	    	return producer;
	    } catch (JMSException e) {
	    	ExceptionLogger.error(e);
	    }	 
	    return ((MessageProducer)sendQueues.get(name));
	}
	/**
	 * 获取队列对应的消费者，如果已经创建对应的消费者，直接返回已有consumer
	 * @param name队列的名称
	 * @return
	 */
	public MessageConsumer getTopicConsumer(String name) {
	    if (getTopics.containsKey(name))
	    	return ((MessageConsumer)getTopics.get(name));
	    try
	    {
	    	Destination destination = session.createTopic(name);
	    	MessageConsumer consumer = session.createConsumer(destination);
	    	getTopics.put(name, consumer);
	    	return consumer;
	    } catch (JMSException e) {
	    	ExceptionLogger.error(e);
	    } 
    	return ((MessageConsumer)getTopics.get(name));
	}
	
	
 
	/**
	 * 发送消息到指定的消息队列
	 * @param queue目标队列
	 * @param map消息 通过key value形式存放
	 */
	@Async
	public void sendQueue(String queue,Serializable obj) {
		ActivemqSendTask task=new ActivemqSendTask(this,queue,obj,ActivemqUtil.QUEUE);
		send_Service.execute(task);
	}
	
	@Async
	public ObjectMessage getQueueMessage(String queue)
	{
		try {
			ObjectMessage message = (ObjectMessage)getQueueConsumer(queue).receive();
			return message;
		} catch (JMSException e) {
			ExceptionLogger.error(e);
		}
		return null;
	}
	
	
	/**
	 * 发送主题到指定的主题队列
	 * @param queue目标队列
	 * @param map消息 通过key value形式存放
	 */
	@Async
	public void sendTopic(String topic,Serializable obj) {
		ActivemqSendTask task=new ActivemqSendTask(this,topic,obj,ActivemqUtil.TOPIC);
		send_Service.execute(task);
	}
	
	
	@Async
	public ObjectMessage getTopicMessage(String queue)
	{
		try {
			ObjectMessage message = (ObjectMessage)getTopicConsumer(queue).receive();
			return message;
		} catch (JMSException e) {
			ExceptionLogger.error(e);
		}
		return null;
	}
	
	
	/**
	 * 关闭会话和连接
	 */
	public void close() {
	    try {
	    	session.close();
	    } catch (JMSException e) {
	    	ExceptionLogger.error(e);
	    }
	    try {
	    	connection.close();
	    } catch (JMSException e) {
	    	ExceptionLogger.error(e);
	    }
	}
  
  	/**
  	 * 将map转出message
  	 */
	/*
  	public MapMessage mapToMessage(Map<String, Object> map)throws JMSException{
  		Set<String> keys=map.keySet();
  		MapMessage message=session.createMapMessage();
  		for(String key:keys){
  			message.setObject(key,map.get(key));
  		}
  		return message;
  	}
  	*/
  	
  	
  	/**
  	 * 将message转成map
  	 * @param map
  	 * @return
  	 * @throws JMSException
  	 */
	/*
  	public Map<String, Object> messageToMap(MapMessage message)throws JMSException{
		Map<String, Object> map=new HashMap<String,Object>();
		@SuppressWarnings("unchecked")
		Enumeration<String> keys=message.getMapNames();
  		while(keys.hasMoreElements()){
  			String key=keys.nextElement();
  			map.put(key,message.getObject(key));
  		}
  		return map;
  	}
  	*/
  	
  	/**
  	 * 将obj转出objmessage
  	 */
  	public ObjectMessage objToObjMessage(Serializable obj)throws JMSException{
  		ObjectMessage objmessage=session.createObjectMessage(obj.toString());
  		return objmessage;
  	}
  	/**
  	 * 将objmessage转成obj
  	 * @param map
  	 * @return
  	 * @throws JMSException
  	 */
  	public Serializable objMessageToObj(ObjectMessage objmessage)throws JMSException{
  		return objmessage.getObject();
  	}

  	
  	
  	
	public String getBrokerUrl() {
		return brokerUrl;
	}


	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
	}


	public List<BaseActivemqListener> getListeners() {
		return listeners;
	}


	public void setListeners(List<BaseActivemqListener> listeners) {
		this.listeners = listeners;
	}


	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}
  	
  	
}