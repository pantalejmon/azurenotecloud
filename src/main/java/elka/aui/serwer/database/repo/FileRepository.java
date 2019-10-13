package elka.aui.serwer.database.repo;

import elka.aui.serwer.database.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Repository("fileRepository")
public interface FileRepository extends JpaRepository<File,String> {
}
