package org.swallow.swallow_data_analysis.service.implement;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.swallow.swallow_data_analysis.service.FileInputSystem;
import org.swallow.swallow_data_analysis.storage.StorageException;
import org.swallow.swallow_data_analysis.storage.StorageFileNotFoundException;
import org.swallow.swallow_data_analysis.storage.StorageProperties;

@Slf4j
@Service
public class FileInputSystemService implements FileInputSystem {

  private final Path rootLocation;

  @Autowired
  public FileInputSystemService(@Qualifier("StorageProperties") StorageProperties properties) {
    if (properties.getLocation().trim().isEmpty()) {
      throw new StorageException("File upload location can not be Empty");
    }

    this.rootLocation = Paths.get(properties.getLocation());
  }

  @Override
  public String store(MultipartFile file) {
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file.");
      }

      Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
          .normalize().toAbsolutePath();

      log.info(destinationFile.toString());

      if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
        // This is a security check
        throw new StorageException(
            "Cannot store file outside current directory.");
      }
      try (InputStream inputStream = file.getInputStream()) {
        long path = Files.copy(inputStream, destinationFile,
            StandardCopyOption.REPLACE_EXISTING);

        log.info(file.getOriginalFilename());
      }

      return destinationFile.toString();
    } catch (IOException e) {
      throw new StorageException("Failed to store file.", e);
    }
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.rootLocation, 1)
          .filter(path -> !path.equals(this.rootLocation))
          .map(this.rootLocation::relativize);
    } catch (IOException e) {
      throw new StorageException("Failed to read stored files", e);
    }

  }

  @Override
  public Path load(String filename) {
    return rootLocation.resolve(filename);
  }

  @Override
  public Resource loadAsResource(String filename) {
    try {
      Path file = load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new StorageFileNotFoundException(
            "Could not read file: " + filename);

      }
    } catch (MalformedURLException e) {
      throw new StorageFileNotFoundException("Could not read file: " + filename, e);
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile());
  }

  @Override
  public void init() {
    try {
      Files.createDirectories(rootLocation);
    } catch (IOException e) {
      throw new StorageException("Could not initialize storage", e);
    }
  }

}