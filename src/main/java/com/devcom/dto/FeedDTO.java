	package com.devcom.dto;

	import java.sql.Date;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;

	import com.fasterxml.jackson.annotation.JsonFormat;

	public class FeedDTO {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		 private int feedid;
		
		 private int devId;
		 
		 private String query;
		
		 @JsonFormat(pattern="dd-MM-yyyy")		
		 private Date feedDate;

		 private String topic;
		
		 private int relevance;
 		public int getFeedid() {
 			return feedid;
 		}
		public void setFeedid(int feedid) {
			this.feedid = feedid;
		}
		public Date getFeedDate() {
			return feedDate;
		}
		public void setFeedDate(Date feedDate) {
			this.feedDate = feedDate;
		}
		public  String getQuery() {
			return query;
		}
		public void setQuery(String query) {
			this.query = query;
		}
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		public int getRelevance() {
			return relevance;
		}
		public void setRelevance(int relevance) {
			this.relevance = relevance;
		}
		public int getDevId() {
			return devId;
		}
		public void setDevId(int devId) {
			this.devId = devId;
		}
		}



