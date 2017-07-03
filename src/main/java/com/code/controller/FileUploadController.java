package com.code.controller;


import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.code.model.FileUploadBean;
import com.code.service.StorageService;
import com.code.service.impl.StorageFileNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

@Controller

@RequestMapping("/upload_file")
public class FileUploadController {
    private final StorageService storageService;
    FileUploadBean ufile;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
        
        ufile  =new FileUploadBean();
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
    
    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
      //0. notice, we have used MultipartHttpServletRequest
      //1. get the files from the request object
      Iterator<String> itr =  request.getFileNames();
  
      MultipartFile mpf = request.getFile(itr.next());
      System.out.println(mpf.getOriginalFilename() +" uploaded!");
      
      try {
                 //just temporary save file info into ufile
         ufile.setSize(mpf.getBytes().length);
         ufile.setType(mpf.getContentType());
         ufile.setOrname(mpf.getOriginalFilename());
         ufile.setBytes(mpf.getBytes());
         
         System.out.println(ufile.getSize()+" size!");
         System.out.println(ufile.getType()+" type!");
         System.out.println(ufile.getBytes()+" size!");
  
     } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
  
      //2. send it back to the client as <img> that calls get method
      //we are using getTimeInMillis to avoid server cached image 
  
      return "<a class='imagSet'><img src='http://localhost:8080/upload_file/get/"+Calendar.getInstance().getTimeInMillis()+"' /></a>";
  
   }
    
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
    }
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
