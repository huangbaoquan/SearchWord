package ecnu.ica.wordsearch.model;

public class WordInfo {
	
	public String alias;
	public String title;
	public String description;
	public String time;
	public String history;
	public String reference;
	public String html;
	
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	@Override
	public String toString() {
		return "WordInfo [alias=" + alias + ", title=" + title
				+ ", description=" + description + ", time=" + time
				+ ", history=" + history + ", reference=" + reference
				+ ", html=" + html + "]";
	}
	
	
}
