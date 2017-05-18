package MetaUpload;

//imports
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileContentController {

	@Autowired private FileRepository filesRepo;
	@Autowired private FileContentStore contentStore;
	// setContent method
	@RequestMapping(value="/files/{fileId}", method = RequestMethod.PUT, headers="content-type!=application/hal+json")
	public ResponseEntity<?> setContent(@PathVariable("fileId") Long id, @RequestParam("file") MultipartFile file) 
			// exception handling if file does not exist
			throws IOException {
		// finds file off id 
		File f = filesRepo.findOne(id);
		f.setMimeType(file.getContentType());
		// usage of contentStore
		contentStore.setContent(f, file.getInputStream());
		// save updated content-related info using file repository
		filesRepo.save(f);
		// returns http status 200	
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	// getContent method
	@RequestMapping(value="/files/{fileId}", method = RequestMethod.GET, headers="accept!=application/hal+json")
	public ResponseEntity<?> getContent(@PathVariable("fileId") Long id) {
		// usage of file repository and meta data
		File f = filesRepo.findOne(id);
		InputStreamResource inputStreamResource = new InputStreamResource(contentStore.getContent(f));
		// creation of http headers
		HttpHeaders headers = new HttpHeaders();
		// sets length of item
		headers.setContentLength(f.getContentLength());
		// sets mime type
		headers.set("Content-Type", 	f.getMimeType());
		// returns http status 200
		return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
	}
}