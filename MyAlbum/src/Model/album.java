package Model;

public class album {
	private String name;
	private String id;
	private String [] cover=new String[4];
	private String tags;
	private String createTime;
	private String classify;
	private String userID;
	private String albumCreatorName;
	
	public String getAlbumCreatorName() {
		return albumCreatorName;
	}
	public void setAlbumCreatorName(String albumCreatorName) {
		this.albumCreatorName = albumCreatorName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String[] getCover() {
		return cover;
	}
	public void setCover(String[] cover) {
		this.cover = cover;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
}
