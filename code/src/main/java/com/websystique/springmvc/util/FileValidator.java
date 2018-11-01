package com.websystique.springmvc.util;

import com.websystique.springmvc.model.FileBucket;
import com.websystique.springmvc.model.UserDocument;
import com.websystique.springmvc.service.UserDocumentService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class FileValidator implements Validator {

	@Value("${number.id}")
	private int USERID;

	@Autowired
	UserDocumentService userDocumentService;
		
	public boolean supports(Class<?> clazz) {
		return FileBucket.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		FileBucket file = (FileBucket) obj;

		MultipartFile f = file.getFile();
		String fileStr = null;
		try {
			fileStr = new String(f.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
		String temp = FilenameUtils.removeExtension(file.getFile().getOriginalFilename());

		String extension = FilenameUtils.getExtension(file.getFile().getOriginalFilename());

		Matcher matcher = pattern.matcher(temp);
		boolean found = matcher.find();

		if(file.getFile()!=null){
			if (file.getFile().getSize() == 0) {
				errors.rejectValue("file", "missing.file");
			}
			else if(file.getFile().getSize() > 1024 * 1024)
			{
				errors.rejectValue("file", "maxSize.file");
			}
			else if(!extension.equals("jpg") && !extension.equals("png")){
				errors.rejectValue("file", "format.file");
			}
			else if(!found){
				errors.rejectValue("file", "format.fileName");
			}

			List<UserDocument> documentList = userDocumentService.findAllByUserId(USERID);
			String tmp1 = FilenameUtils.removeExtension(file.getFile().getOriginalFilename());

			for (UserDocument doc : documentList) {
				if (tmp1.equals(FilenameUtils.removeExtension(doc.getName()))) {
					errors.rejectValue("file", "object.exist");
				}

			}
		}
	}
}

