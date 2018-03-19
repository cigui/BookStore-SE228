package utils;

public class JsonBean {
	private String id;
	private String text;

	public JsonBean(String id, String text) {
		this.setId(id);
		this.setText(text);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
