package com.maiyajf.base.utils.activemq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivemqListener{
	List<String> queues=new ArrayList<String>();
	List<String> topics=new ArrayList<String>();
	public List<String> getQueues() {
		return queues;
	}
	public void setQueues(List<String> queues) {
		this.queues = queues;
	}
	public List<String> getTopics() {
		return topics;
	}
	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	public abstract void onBaseMessage(Serializable map, String name, String type);
}
