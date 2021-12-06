package com.project.cricket.utils;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FileOperationUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileOperationUtils.class);

	public boolean writeToFile(String dirPath, String fileName, String content, boolean overWrite) {
		boolean flag = false;
		FileChannel fileChannel = null;
		try {
			LOGGER.info("Writing file {} in {}", fileName, dirPath);
			Path path = Paths.get(dirPath);
			Files.createDirectories(path);
			Path filePath = Paths.get(path.toString(), fileName);
			if (!overWrite && filePath.toFile().exists()) {
				LOGGER.info("File {} already exists in {}", fileName, dirPath);
			} else {
				Files.write(filePath, content.getBytes());
				fileChannel = FileChannel.open(filePath);
				String fileSize = FileUtils.byteCountToDisplaySize(fileChannel.size());
				LOGGER.info("File {} written successfully in {} with size {}", fileName, dirPath, fileSize);
			}
			flag = true;
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
		return flag;
	}

	public List<Integer> getMatchFilesAsInteger(String dirPath) {
		List<Integer> files = new ArrayList<>();
		try (Stream<Path> list = Files.list(Paths.get(dirPath))) {
			files = list.filter(Files::isRegularFile)
					.map(Path::toFile)
					.map(File::getName)
					.map(str -> str.replace(".json", ""))
					.map(Integer::valueOf)
					.collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return files;
	}

	public String readFile(String dirPath, String fileName) {
		String response = "";
		try {
			Path path = Paths.get(dirPath);
			Path filePath = Paths.get(path.toString(), fileName);
			File file = filePath.toFile();
			if (file.exists()) {
				response = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
			}
		} catch (Exception e) {
			LOGGER.error("Error in reading filename {} from {}", fileName, dirPath, e);
		}
		return StringUtils.trimWhitespace(response);
	}
}
