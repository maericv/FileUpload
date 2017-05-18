package MetaUpload;

// import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// create repository and export it as a resource
@RepositoryRestResource(path="files", collectionResourceRel="files") 
// interface for create, red, update and delete
public interface FileRepository extends JpaRepository<File, Long> {

}
