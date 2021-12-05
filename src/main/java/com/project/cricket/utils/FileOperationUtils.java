package com.project.cricket.utils;

import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileOperationUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileOperationUtils.class);

	public void writeToFile(String dirPath, String fileName, String content) {
		try {
			LOGGER.info("Writing file {} in {}", fileName, dirPath);
			Path path = Paths.get(dirPath);
			Files.createDirectories(path);
			Path filePath = Paths.get(path.toString(), fileName);
			Files.write(filePath, content.getBytes());
			String fileSize = FileUtils.byteCountToDisplaySize(FileChannel.open(filePath).size());
			LOGGER.info("File {} written successfully in {} with size {}", fileName, dirPath, fileSize);
		} catch (Exception e) {
			LOGGER.error("Exception in writing file {}", fileName, e);
		}
	}
}
