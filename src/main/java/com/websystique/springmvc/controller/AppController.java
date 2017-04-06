package com.websystique.springmvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import net.htmlparser.jericho.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.websystique.springmvc.model.FileBucket;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserDocument;
import com.websystique.springmvc.service.UserDocumentService;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.util.FileValidator;



@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserDocumentService userDocumentService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	FileValidator fileValidator;
	
	@InitBinder("fileBucket")
	protected void initBinder(WebDataBinder binder) {
	   binder.setValidator(fileValidator);
	}
	
	/**
	 * This method will list all existing users.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@RequestMapping(value = { "/","/Default.aspx" }, method = RequestMethod.GET)
	public String getPage(ModelMap model, HttpServletRequest request) throws MalformedURLException, IOException {
		String pageId = request.getParameter("PageId");
		String articleId = request.getParameter("ArticleId");
		UserDocument document;
		document = userDocumentService.findByPath("/");
		if(pageId!=null)
			document = userDocumentService.findByPath(pageId);
		else if (articleId!=null)
			document = userDocumentService.findByPath(articleId);
		byte[] file = document.getContent();
		String jsp=new String(file);
		model.addAttribute("editor1", jsp);
		return "jsp/ckediter";
	}
	@RequestMapping(value = { "/","/Default.aspx" }, method = RequestMethod.POST)
	public String postPage(ModelMap model, @RequestParam("textArea") String editor, HttpServletRequest request) throws MalformedURLException, IOException {
		String pageId = request.getParameter("PageId");
		String articleId = request.getParameter("ArticleId");
		User user = userService.findById(1);
		if(pageId!=null)
		{
			saveDocument(pageId ,editor, user);
			return "redirect:/Default.aspx?PageId="+pageId;
		}
		else if (articleId!=null)
		{
			saveDocument(articleId ,editor, user);
			return "redirect:/Default.aspx?ArticleId="+articleId;
		}
		saveDocument("/" ,editor, user);
		
		return "redirect:/";
	}

	private void saveDocument(String path, String text, User user) throws IOException{
		UserDocument document = new UserDocument();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();
	    
		document.setPath(path);
		document.setDescription("test");
		document.setType("type/html");
		document.setContent(text.getBytes());
		document.setUser(user);
		document.setDate(dateFormat.format(date));
		userDocumentService.saveDocument(document);
	}
	
}
