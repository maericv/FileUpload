package MetaUpload;

// filesystem dependency
import org.springframework.content.commons.repository.ContentStore;

// provides methods for setContent, getContent
public interface FileContentStore extends ContentStore<File, String> {
}  
