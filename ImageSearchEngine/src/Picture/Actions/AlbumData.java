package Picture.Actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.jasig.cas.client.validation.Assertion;


import DBTool.DB18Util;
import Picture.Models.AlbumPicture;
import Picture.Models.album;

import com.ckcest.sso.UserProfile;
import com.opensymphony.xwork2.ActionSupport;

public class AlbumData extends ActionSupport{

	//albumID
	private String albumID="";
	//username
	private String username="";
	//userid
	private String userid="";
	//tag
	private String tag="";
	//pictureList
	private List<AlbumPicture> pictureList=new ArrayList<AlbumPicture>();
	//m_album
	private album m_album;
	
	
	private static final long serialVersionUID = 1L;
	public static Connection conn = null;
	
	public String execute()throws Exception{
		
		//userid=getUserID();
		username=getUserName();
		userid = username;
		
		if(conn==null) {
			conn=DB18Util.connectMySql();
		}
		
		
		Statement statement=conn.createStatement();
		String sql="";
		if(tag==""){
			sql="select * from userinfo.pictureStore where isDeleted = 0 and albumID="+albumID;
		}
		else{
			sql="select * from userinfo.pictureStore where isDeleted = 0 and albumID="+albumID+" and tags like '%"+tag+"%'";
		}
		statement = conn.prepareStatement(sql);
		ResultSet rs=statement.executeQuery(sql);
		while(rs.next()){
			AlbumPicture m_picture=new AlbumPicture();
			m_picture.setAlbumID(rs.getString("albumID"));
			m_picture.setPictureID(rs.getString("pictureID"));
			m_picture.setTags(rs.getString("tags"));
			pictureList.add(m_picture);
			//albumList.add(rs.getString("name"));
		}
		sql="select * from userinfo.album where isDeleted =0 and id="+albumID;
		statement = conn.prepareStatement(sql);
		rs=statement.executeQuery(sql);
		m_album=new album();
		while(rs.next()){
			m_album.setCreateTime(rs.getString("createTime"));
			m_album.setName(rs.getString("name"));
			m_album.setClassify(rs.getString("classID"));
			
		}
		
		
		statement.close();
		return "success";
	}
	

	private String getUserName() {
		// TODO Auto-generated method stub
		String username="";
		HttpServletRequest request=ServletActionContext.getRequest();
		Object object = request.getSession().getAttribute("_const_cas_assertion_");
		if(object != null) {

		    Assertion assertion = (Assertion)object;
		    username = assertion.getPrincipal().getName();
		}
		return username;
	}

	private String getUserID() {
		// TODO Auto-generated method stub
		HttpServletRequest request=ServletActionContext.getRequest();
		Object object = request.getSession().getAttribute("_const_userprofile_assertion_");
		if(object!=null){
			UserProfile userProfile= (UserProfile)object;
			return userProfile.userId;
		}
		return null;
	}

	
	
	
	//
	public String getAlbumID() {
		return albumID;
	}
	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	
	public List<AlbumPicture> getPictureList() {
		return pictureList;
	}
	public void setPictureList(List<AlbumPicture> pictureList) {
		this.pictureList = pictureList;
	}
	
	

	public album getM_album() {
		return m_album;
	}
	public void setM_album(album mAlbum) {
		m_album = mAlbum;
	}
}
