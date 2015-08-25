package com.stupid.method.demo.bean;

import java.util.List;

public class Joke {
	public static class Jokes {
		private List<Joke> jokes;
		private int total;
		private int page;

		public List<Joke> getJokes() {
			return jokes;
		}

		public void setJokes(List<Joke> jokes) {
			this.jokes = jokes;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

	}

	private String id;

	private String title;

	private String content;

	private String tag;

	private String img;

	private String publishDate;

	private String url;

	private String commentUrl;

	private String vote;

	private String source;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setContent(String content) {
		this.content = content.replace("##", "\n");
	}

	public String getContent() {
		return this.content;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return this.tag;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImg() {
		return this.img;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublishDate() {
		return this.publishDate;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setCommentUrl(String commentUrl) {
		this.commentUrl = commentUrl;
	}

	public String getCommentUrl() {
		return this.commentUrl;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}

	public String getVote() {
		return this.vote;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return this.source;
	}

}
