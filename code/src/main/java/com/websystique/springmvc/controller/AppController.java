package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.*;
import com.websystique.springmvc.service.BrowserService;
import com.websystique.springmvc.service.UserDocumentService;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.util.File2Validator;
import com.websystique.springmvc.util.FileValidator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/")
public class AppController {

    @Value("${path.location}")
    private String LOC;

    @Value("${number.id}")
    private int USERID; //yes, the user id is static for now, but the database is alr created. Login as future enhancement

    @Autowired
    UserService userService;

    @Autowired
    UserDocumentService userDocumentService;

    @Autowired
    BrowserService browserService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    FileValidator fileValidator;

    @Autowired
    File2Validator file2Validator;

    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @InitBinder("fileChange")
    protected void initBinder2(WebDataBinder binder) {
        binder.setValidator(file2Validator);
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String Home(ModelMap model) {

        return "home";
    }

    @RequestMapping(value = {"/404"}, method = RequestMethod.GET)
    public String Redirect404(ModelMap model) {

        return "404";
    }

    @RequestMapping(value = {"/500"}, method = RequestMethod.GET)
    public String Redirect500(ModelMap model) {

        return "500";
    }

    @RequestMapping(value = {"/generic"}, method = RequestMethod.GET)
    public String Redirect5Generic(ModelMap model) {

        return "errorgeneric";
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String Index(ModelMap model) {
        List<UserDocument> documents = userDocumentService.findAllByUserId(USERID);
        List<FileChange> listOfFiles = new LinkedList<FileChange>();

        for(int i =0; i<documents.size(); i++) {
            FileChange f = new FileChange();
            f.setId(documents.get(i).getId());
            f.setName(documents.get(i).getName());
            f.setDescription(documents.get(i).getDescription());
            f.setType(documents.get(i).getType());
            f.setContent(Base64.getEncoder().encodeToString(documents.get(i).getContent()));
            f.setCron(documents.get(i).getCron());
            listOfFiles.add(f);
        }
        model.addAttribute("fileChange", listOfFiles);

        return "index";
    }

    @RequestMapping(value = {"/image/upload"}, method = RequestMethod.GET)
    public String scriptUpload(ModelMap model) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "uploadscript";
    }


    @RequestMapping(value = {"/image/upload"}, method = RequestMethod.POST)
    public String uploadDoc(@Valid FileBucket fileBucket, BindingResult result, ModelMap model
            , RedirectAttributes redirectAttrs) throws IOException {

        if (result.hasErrors()) {
            System.out.println("validation errors");

            return "uploadscript";
        } else {
            System.out.println("Fetching file");
            User user = userService.findById(USERID);
            String name = saveDocument(fileBucket, user);


            redirectAttrs.addFlashAttribute("MESSAGE",
                    name + " has been successfully added!");
            return "redirect:/index";
        }
    }


    @RequestMapping(value = {"/image/{docId}"}, method = RequestMethod.GET)
    public String Documents(@PathVariable int docId, ModelMap model) throws IOException {

        UserDocument document = userDocumentService.findById(docId);

        FileChange f = new FileChange();
        f.setId(document.getId());
        f.setName(FilenameUtils.removeExtension(document.getName()));
        f.setExtension(FilenameUtils.getExtension(document.getName()));
        f.setDescription(document.getDescription());
        f.setContent(new String(document.getContent()));
        f.setCron(document.getCron());
        model.addAttribute("fileChange", f);
        model.addAttribute("document", document);

        return "managedoc";
    }

    @RequestMapping(value = {"/image/{docId}"}, method = RequestMethod.POST)
    public String updateDoc(@Valid FileChange fileChange, BindingResult result, ModelMap model, @PathVariable int docId,
                            RedirectAttributes redirectAttrs) throws IOException {

        if (result.hasErrors()) {
            System.out.println("validation errors");

            UserDocument document = userDocumentService.findById(docId);

            model.addAttribute("expandcollapse", "false");
            model.addAttribute("document", document);

            return "managedoc";
        } else {

            UserDocument document = userDocumentService.findById(docId);

            String name = updateDocument(document, fileChange);
            User user = userService.findById(USERID);
            Browser browser = browserService.findByName(user.getBrowser());

            Quartz.scheduleJob(document, browser);
            redirectAttrs.addFlashAttribute("MESSAGE2",
                    name + " has been successfully updated!");
            return "redirect:/image/" + docId;
        }
    }

    @RequestMapping(value = {"/{docId}/image"}, method = RequestMethod.GET)
    public void dlScript(@PathVariable int docId, ModelMap model, HttpServletResponse response) throws IOException {
        UserDocument document = userDocumentService.findById(docId);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getName() + "\"");

        FileCopyUtils.copy(document.getContent(), response.getOutputStream());
    }


    @ResponseBody
    @RequestMapping(value = {"/rundoc"}, method = RequestMethod.POST)
    public void runDoc(int docId) throws IOException, ParseException {
        UserDocument document = userDocumentService.findById(docId);
        User user = userService.findById(USERID);
        Browser browser = browserService.findByName(user.getBrowser());
        Quartz.runMethod(document, browser);
    }

    @ResponseBody
    @RequestMapping(value = {"/deletedoc"}, method = RequestMethod.POST)
    public void deleteDoc(int docId, RedirectAttributes redirectAttrs) throws IOException {
        final UserDocument document = userDocumentService.findById(docId);
        String scriptName = document.getName();

        //delete document directory
        File f = new File(LOC + docId + "/");
        if (f.exists()) {
            FileUtils.deleteDirectory(f);
        }

        //delete schedule
        document.setCron("");
        Quartz.scheduleJob(document, null);

        userDocumentService.deleteById(docId);

        redirectAttrs.addFlashAttribute("MESSAGE",
                "script has been successfully deleted!");
    }


    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String UserInfo(ModelMap model) {

        User user = userService.findById(USERID);
        List<Browser> browsers = browserService.findAllBrowsers();
        model.addAttribute("user", user);
        model.addAttribute("browsers", browsers);
        return "user";
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.POST)
    public String UserInfoPOST(@Valid User userUpdate, BindingResult result, ModelMap model,
                               RedirectAttributes redirectAttrs) throws IOException {

        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "user";
        } else {
            boolean check = false;
            User user = userService.findById(USERID);
            if (!user.getEmail().equals(userUpdate.getEmail())) { //update scheduling jobs,if email was changed
                check = true;
            } else if (!user.getBrowser().equals(userUpdate.getBrowser())) { //update scheduling jobs,if email was changed
                check = true;
            }
            userService.updateUser(userUpdate);

            if (check) {
                List<UserDocument> documents = userDocumentService.findAllByUserId(USERID);
                Browser browser = browserService.findByName(userUpdate.getBrowser());
                for (UserDocument doc : documents
                        ) {
                    Quartz.scheduleJob(doc, browser);
                }
            }

            redirectAttrs.addFlashAttribute("MESSAGE",
                    "User has been successfully updated!");
            return "redirect:/user";
        }
    }

    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public String About(ModelMap model) {

        return "about";
    }


    private String saveDocument(FileBucket fileBucket, User user) throws IOException {

        UserDocument document = new UserDocument();

        MultipartFile multipartFile = fileBucket.getFile();

        document.setName(multipartFile.getOriginalFilename());
        document.setDescription(fileBucket.getDescription());
        document.setType(multipartFile.getContentType());
        document.setContent(multipartFile.getBytes());
        document.setUser(user);
        document.setCron("");
        userDocumentService.saveDocument(document);

        createScriptDirectory(document);

        return multipartFile.getOriginalFilename();
    }

    private String updateDocument(UserDocument document, FileChange file) throws IOException {
        String tmpName = file.getName()+ "." + file.getExtension();

        //delete, then create the directory again later if the name is changed
        if (!document.getName().equals(tmpName)) {
            File dir = new File(LOC + document.getId() + "/");

            if (dir.exists()) {

                FileUtils.deleteDirectory(dir);
            }
            dir.mkdir();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        document.setName(tmpName);
        document.setDescription(file.getDescription());
        document.setCron(file.getCron());
        System.out.println(document.toString());
        userDocumentService.updateDocument(document);

        File f3 = new File(LOC + document.getId() + "/" + tmpName);
        f3.createNewFile();
        FileCopyUtils.copy(document.getContent(), f3);

        return tmpName;
    }

    private void createScriptDirectory(UserDocument document) throws IOException {
        File dir = new File(LOC + document.getId());
            dir.mkdir();
            File file = new File(LOC + document.getId() + "/" + document.getName());
            file.createNewFile();
            FileCopyUtils.copy(document.getContent(), file);

    }
}
