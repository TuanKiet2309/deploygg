package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.UserDocument;

public interface UserDocumentService {

	UserDocument findByPath(String path);
	
	void saveDocument(UserDocument document);
}
