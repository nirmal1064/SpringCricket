package com.project.cricket.utils;

import java.io.IOException;
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
		FileChannel fileChannel = null;
		try {
			LOGGER.info("Writing file {} in {}", fileName, dirPath);
			Path path = Paths.get(dirPath);
			Files.createDirectories(path);
			Path filePath = Paths.get(path.toString(), fileName);
			Files.write(filePath, content.getBytes());
			fileChannel = FileChannel.open(filePath);
			String fileSize = FileUtils.byteCountToDisplaySize(fileChannel.size());
			LOGGER.info("File {} written successfully in {} with size {}", fileName, dirPath, fileSize);
		} catch (Exception e) {
			LOGGER.error("Exception in writing file {}", fileName, e);
		} finally {
			try {
				if (fileChannel != null) {
					fileChannel.close();
				}
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}
}
