package com.project.cricket.utils;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
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

	public boolean writeToFile(String dirPath, String fileName, String content) {
		boolean flag = false;
		FileChannel fileChannel = null;
		try {
			String response = readFile(dirPath, fileName);
			Path path = Paths.get(dirPath);
			Files.createDirectories(path);
			Path filePath = Paths.get(path.toString(), fileName);
			if (StringUtils.hasText(content) && !response.equalsIgnoreCase(content)) {
				Files.writeString(filePath, content);
				fileChannel = FileChannel.open(filePath);
				String fileSize = FileUtils.byteCountToDisplaySize(fileChannel.size());
				LOGGER.info("File {} written successfully in {} with size {}", fileName, dirPath, fileSize);
			} else {
				LOGGER.info("File {} already exists in {}", fileName, dirPath);
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
				response = FileUtils.readFileToString(file, UTF_8);
			}
		} catch (Exception e) {
			LOGGER.error("Error in reading filename {} from {}", fileName, dirPath, e);
		}
		return StringUtils.trimWhitespace(response);
	}

}
