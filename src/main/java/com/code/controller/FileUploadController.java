package com.code.controller;


import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.code.model.FileUploadBean;
import com.code.service.IFileImageService;
import com.code.service.StorageService;
import com.code.service.impl.StorageFileNotFoundException;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/upload_file")
public class FileUploadController {
    private final StorageService storageService;
    private final IFileImageService iFileImageService;
 
    FileUploadBean ufile;
    
    @Autowired
    public FileUploadController(StorageService storageService,IFileImageService iFileImageService) {
        this.storageService = storageService;
        this.iFileImageService = iFileImageService;
        ufile = new FileUploadBean();
    }

    @RequestMapping("/loadall_file")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "auploadfile";
    }

    @RequestMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

   /* @RequestMapping("/store_file")
    public String handleFileUpload(@RequestParam("file") MultipartFile[] file,
                                   RedirectAttributes redirectAttributes) {
    	for (int i = 0; i < file.length; i++) {
    		storageService.store(file[i]);
    	}
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file[0].getOriginalFilename() + "!");

        return "redirect:/";
    }*/
    
    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String,Object>  upload(MultipartHttpServletRequest request, HttpServletResponse response) {
    	//0. notice, we have used MultipartHttpServletRequest
    	//1. get the files from the request object

    	Iterator<String> itr =  request.getFileNames();
    	MultipartFile mpf = request.getFile(itr.next());  
    	ufile.setOrname(mpf.getOriginalFilename());
    	String fileName = DateFormatUtils.format(new Date(), "yyyyMMdd") + "_"+ UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(mpf.getOriginalFilename());
    	try {
    		ufile.setSize(mpf.getBytes().length);
    		ufile.setRegdate(DateFormatUtils.format(new Date(), "yyyyMMddhhmmss"));
    		ufile.setType(mpf.getContentType());
    		ufile.setRandname(fileName);
    		
    		
    		//just temporary save file info into ufile
    		//if(mpf.getBytes().length < 1048575) {
    			System.out.println(mpf.getBytes().length);
    			storageService.store(mpf,fileName);	
    		//}
    	
    		
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	//2. send it back to the client as <img> that calls get method
    	//we are using getTimeInMillis to avoid server cached image 
    	return new HashMap<String,Object>(){
    		{
    			put("OUT_REC",ufile);
    			put("RANDNAME",fileName);
    		}
    	};
    }
/*    
    @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
    public void get(HttpServletResponse response,@PathVariable String value){
          try {
              response.setContentType(ufile.getType());
              response.setContentLength(ufile.getSize());
              FileCopyUtils.copy(ufile.getBytes(), response.getOutputStream());
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
    }*/
    
    @RequestMapping(value = "/remove_file_name", method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> removeFile(@RequestParam(value = "randname") String randname){
		this.iFileImageService.remove(randname);
		this.storageService.delete(randname);
	    return new HashMap<String,Object>(){
	          {
	              put("randname",randname);
	              put("ERROR","delete success!");
	          }
	   };
    }
    
    @RequestMapping(value = "/remove_file_local", method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> removeFileLocal(@RequestParam(value = "filename") String filename){
	    this.storageService.delete(filename);
	    return new HashMap<String,Object>(){
	          {
	              put("filename",filename);
	              put("ERROR","delete success!");
	          }
	   };
    }
    
    @RequestMapping(value="/save_file_name",method = RequestMethod.POST ,produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String,Object> saveCategory(@RequestBody FileUploadBean fileUploadBean){
    	if(!StringUtil.isEmpty(fileUploadBean.getOld_randname())) {
    		this.iFileImageService.deleteProfileImage(fileUploadBean);
    	}
    	this.iFileImageService.saveFileUploadBean(fileUploadBean);
    	return new HashMap<String,Object>(){
    		{
    			put("SUCC","Filse was Saved");
    			put("OUT_REC",fileUploadBean);
    		}
    	};
    }
    
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> test(){  
    	return new HashMap<String,Object>(){
    		{
    			put("SUCC","Filse was Saved");
    		}
    	};
    }
   @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
