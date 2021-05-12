package com.controller;

import com.Service.ProviderService;
import com.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller

public class ProviderController {
    @Autowired
    private ProviderService providerService;
    @RequestMapping("/providerList")
    public String getList(Model model){
        List<Provider> list = providerService.getList();
        model.addAttribute("plist",list);
        return "provider_list";
    }
    @RequestMapping("toProAdd")
    public String toAdd(){
        return "provider_add";
    }
    @RequestMapping("/addProvider")
    @ResponseBody
    public String add(HttpServletRequest request){
        String proCode = request.getParameter("proCode");
        String proName = request.getParameter("proName");
        String proDesc = request.getParameter("proDesc");
        String proContact = request.getParameter("proContact");
        String proPhone = request.getParameter("proPhone");
        String proAddress = request.getParameter("proAddress");
        String proFax = request.getParameter("proFax");
        int add = providerService.add(new Provider(proCode,proName,proDesc,proContact,proPhone,proAddress,proFax));
        System.out.println(proCode);
        if (add>0){
            return "true";
        }else {
            return "false";
        }
    }
    @RequestMapping("/getLike")
    public String getLike(String proCode,String proName,Model model){

        List<Provider> list= providerService.getLike(proCode, proName);

        if (list==null) {
           model.addAttribute("error","没有查到该供应商");
        }

        model.addAttribute("plist",list);
        return "provider_list";
    }
    @RequestMapping("/deleteProvider")
    @ResponseBody
    public String delete(Integer id){
        int delete = providerService.delete(id);
        if (delete>0) {
            return "true";
        }else {
            return "false";
        }

    }
//    @RequestMapping("/deleteProvider")
//    public String delete(Integer id){
//        int delete = providerService.delete(id);
//        return "redirect:providerList";
//
//    }
    @RequestMapping("/toProUpdate")
    public String toUpdate(Integer id,Model model){
        Provider provider = providerService.getByid(id);
        model.addAttribute("provider",provider);
        return "provider_modify";
    }
    @RequestMapping("/updateProvider")
    public String update(Provider provider){
        providerService.update(provider);
        return "redirect:providerList";
    }
    @RequestMapping("/getProById")
    public String getById(Integer id,Model model){
        Provider provider = providerService.getByid(id);
        model.addAttribute("provider",provider);
        return "provider_view";
    }
    @RequestMapping("/jiaoyan1")
    @ResponseBody
   public String getByproCode(String proCode){

        Provider provider = providerService.getByproCode(proCode);
        if (provider == null) {
            return "true";
        }else {
            return "false";
        }

   }
}
