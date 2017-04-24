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



@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserDocumentService userDocumentService;
	
	@Autowired
	MessageSource messageSource;
	
	
	/**
	 * This method will list all existing users.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getPage(ModelMap model, HttpServletRequest request) throws MalformedURLException, IOException {
		String Id = request.getParameter("Id");
		UserDocument document;
		UserDocument deadline;
		UserDocument session;
		UserDocument keylink;
		byte[] file;
		String jsp="";
		String deadlinepage="";
		String sessionpage="";
		String keylinkpage="";
		if(Id==null)
			Id="1";
		document = userDocumentService.findById(Integer.parseInt(Id));
		if(document!=null){
			file = document.getContent();
			jsp=new String(file);
		}
		
		String lastnew = loadTop3News();
		
		deadline = userDocumentService.findByPath("/deadline");
		file = deadline.getContent();
		deadlinepage = new String(file);
		
		session = userDocumentService.findByPath("/session");
		file = session.getContent();
		sessionpage = new String(file);
		
		keylink = userDocumentService.findByPath("/keylink");
		file = keylink.getContent();
		keylinkpage = new String(file);

		model.addAttribute("leftPage", jsp);
		model.addAttribute("listtb_phong", lastnew);
		model.addAttribute("important_deadlines", deadlinepage);
		model.addAttribute("special_session", sessionpage);
		model.addAttribute("key_links", keylinkpage);
		return "jsp/ckediter";
	}
	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public String postPage(ModelMap model, @RequestParam("textArea1") String editor1,
			@RequestParam("textArea2") String editor2,
			@RequestParam("textArea3") String editor3,
			@RequestParam("textArea4") String editor4,
			HttpServletRequest request) throws MalformedURLException, IOException {
		String Id = request.getParameter("Id");
		System.out.println(Id);
		UserDocument document;
		User user = userService.findById(1);
		if(editor1!=""){
			if(Id!=null)
			{
				document = userDocumentService.findById(Integer.parseInt(Id));
				System.out.println(document);
				if(document!=null){
					updateDocument("/", Integer.parseInt(Id), editor1,null, user);
				}
				else
					saveDocument("/",editor1,null, user);
				
				return "redirect:/?Id="+Id;
			}
			else
			{
				document = userDocumentService.findById(1);
				if(document!=null){
					updateDocument("/", 1, editor1,null, user);
				}
				else
					updateDocument("/", 1, editor1,null, user);
				
				return "redirect:/";
			}
		}
		else if(editor2!=""){
			if(Id!=null)
			{
				saveDocument("/deadline",editor2,null, user);
				
				return "redirect:/?Id="+Id;
			}
			else
			{
				saveDocument("/deadline",editor2,null, user);
				
				return "redirect:/";
			}
			
		}
		else if(editor3!=""){
			if(Id!=null)
			{
				saveDocument("/session",editor3,null, user);
				
				return "redirect:/?Id="+Id;
			}
			else
			{
				saveDocument("/session",editor3,null, user);
				
				return "redirect:/";
			}
			
		}
		else if(editor4!=""){
			if(Id!=null)
			{
				saveDocument("/keylink",editor4,null, user);
				
				return "redirect:/?Id="+Id;
			}
			else
			{
				saveDocument("/keylink",editor4,null, user);
				
				return "redirect:/";
			}
			
		}
		return "redirect:/?Id="+Id;
		
	}
	@RequestMapping(value = { "/edit" }, method = RequestMethod.GET)
	public String getUploadPage(ModelMap model) throws MalformedURLException, IOException {
		return "jsp/uploadnews";
	}
	@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
	public String postUploadPage(ModelMap model, @RequestParam("body") String editor, @RequestParam("description") String description) throws MalformedURLException, IOException {
		User user = userService.findById(1);
		saveDocument("/news" ,editor,description, user);
		return "redirect:/edit";
	}

	private void saveDocument(String path, String text,String decription, User user) throws IOException{
		UserDocument document = new UserDocument();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();
		document.setPath(path);
		if(decription==null)
			document.setDescription("test");
		else
			document.setDescription(decription);
		document.setType("type/html");
		document.setContent(text.getBytes());
		document.setUser(user);
		document.setDate(dateFormat.format(date));
		userDocumentService.saveDocument(document);
	}
	private void updateDocument(String path,int id, String text,String decription, User user) throws IOException{
		UserDocument document = new UserDocument();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();
	    document.setId(id);
		document.setPath(path);
		if(decription==null)
			document.setDescription("test");
		else
			document.setDescription(decription);
		document.setType("type/html");
		document.setContent(text.getBytes());
		document.setUser(user);
		document.setDate(dateFormat.format(date));
		userDocumentService.updateDocument(document);
	}
	
	private String loadTop3News() throws IOException{
		String top3="";
		List<UserDocument> documents = userDocumentService.loadTop3News();
		top3 = String.join("\n"

				,"<ul> <li><a href=\"?Id="+ documents.get(0).getId() + "\">"
				,	"<p>Create " + documents.get(0).getDate() + "</p>"
				,	documents.get(0).getDescription().toUpperCase() + "</a></a> </li>"
			
			
				,"<li><a href=\"?Id="+ documents.get(1).getId() + "\">"
				,"<p>Create " + documents.get(1).getDate() + "</p>"
				,	documents.get(1).getDescription().toUpperCase() + "</a></a> </li>"
			
			
				,"<li><a href=\"?Id="+ documents.get(2).getId() + "\">"
				,"<p>Create " + documents.get(2).getDate() + "</p>"
				,	documents.get(2).getDescription().toUpperCase() + "</a></a> </li>"
					
			,"</ul>");
		return top3;
	}
	
}
