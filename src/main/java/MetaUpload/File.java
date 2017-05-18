package MetaUpload;

import java.text.SimpleDateFormat;
// import
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

@Entity
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// Define variables
	private Long id;
	private String name;
	private Date created = new Date();
	private String summary;
	
	// Spring content fields 
	@ContentId private String contentId;  
	@ContentLength private long contentLength;  
	private String mimeType = "text/plain";
	
	// Get methods
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCreated() {
		SimpleDateFormat time = new SimpleDateFormat("MM/dd/yyyy hh:mm");
				String ftime = time.format(created);
		return ftime;
	}
	public String getSummary() {
		return summary;
	}
	public String getContentId() {
		return contentId;
	}
	public long getContentLength() {
		return contentLength;
	}
	public String getMimeType() {
		return mimeType;
	}

	// Set methods
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
