package com.controller;

import com.Service.RoleService;
import com.Service.UserService;
import com.alibaba.fastjson.JSON;
import com.entity.Constains;
import com.entity.Page;
import com.entity.Role;
import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
   @Autowired
    private UserService userService;
   @Autowired
   private RoleService roleService;
    @RequestMapping("/login")
    public String login(String userCode, String userPassword, Model model, HttpSession session, HttpServletRequest request){
        User user = userService.login(userCode, userPassword);
        String contextPath = request.getContextPath();
        session.setAttribute("user",user);
        if (user == null) {
            return "redirect:toLogin";
        }
        return "frame";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    @RequestMapping("/userList")
    public String getAllUser(Model model,HttpSession session,String userName,Integer userRole,Integer pageNo,Integer pageSize){
        Integer pageNo1=1;
        Integer pageSize1=5;
        if (pageNo != null && !"".equals(pageNo)) {
            pageNo1=pageNo;
        }
        List<Role> list = roleService.getList();
        session.setAttribute("rList",list);
        Page page = userService.getPageList2(userName,userRole,pageNo1, pageSize1);
        List<User> uList = page.getUserList();

        model.addAttribute("page",page);
        model.addAttribute("uList",uList);
        return "user_list";
    }
    @RequestMapping("/toUserAdd")
    public String toAdd(){

        return "user_add";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public String add(User user,
                      @RequestParam(value = "idPicPath_1",required = false) MultipartFile file,
                      HttpServletRequest request) throws IOException {
        //获取项目实际路径
        String realPath = request.getServletContext().getRealPath("/images");
        file.transferTo(new File(realPath+File.separator+file.getOriginalFilename()));
        user.setIdPicPath("images/"+file.getOriginalFilename());
        int add = userService.add(user);
        if (add>0){
            return "true";
        }else {
            return "false";
        }

    }
    @RequestMapping("/toUserUpdate")
    public String toUpdate(int id,Model model){
        User user = userService.getById(id);
        model.addAttribute("user",user);
        return "user_modify";
    }
    @RequestMapping("/UserUpdate")
    public String update(User user){
        int update = userService.update(user);

        return "redirect:userList";
    }
    @RequestMapping("/deleteUser")
    @ResponseBody
    public String delete(Integer id){
        int delete = userService.delete(id);
        if (delete>0){
            return "true";
        }else {
            return "false";
        }
    }
//    @RequestMapping("/deleteUser")
//    public String delete(Integer id){
//        int delete = userService.delete(id);
//            return "redirect:userList";
//    }
    @RequestMapping("/likeUser")
    public String getLike(String userName,String userRole,Model model){
        System.out.println(userName+userRole);
        List<User> userList = userService.getLike(userName, userRole);

        if (userList == null) {
            model.addAttribute("error","没有该用户");
        }
        model.addAttribute("uList",userList);
        return "user_list";
    }

    @RequestMapping("/getUserById")
    public String getById(Integer id,Model model){
        User user = userService.getById(id);
        model.addAttribute("user2",user);
        return "user_view";
    }
    @RequestMapping("/toPwdmodify")
    public String toPwdmodify(){
        return "pwdmodify";
    }

@RequestMapping("/pwdModify")
public  String pwdModify(String newpassword,HttpSession session){
    User user = (User) session.getAttribute(Constains.USER_SESSION);
    Integer id = user.getId();
    user.setUserPassword(newpassword);
   userService.pwdModify(user);
    return "frame";
}
    @RequestMapping("/jiaoyan")
    @ResponseBody()
    public String test(String oldpasswrod,HttpSession session) {
        Map<String,Object> map = new HashMap<>();
        if (session.getAttribute(Constains.USER_SESSION) == null) {
            map.put("jieguo","false");
            map.put("denglu","false");
        } else {
            map.put("denglu","true");
            User user = (User) session.getAttribute(Constains.USER_SESSION);
            String userPassword = user.getUserPassword();
            if (userPassword.equals(oldpasswrod)) {
               map.put("jieguo","true");
            } else {
                map.put("jieguo","false");
            }
        }
        return JSON.toJSONString(map);
    }
    @RequestMapping("/UserCode")
    @ResponseBody()
    public String getUserCode(String userCode,HttpSession session){

       User user =  userService.getUserCode(userCode);
        if (user == null) {
            return "true";
        }else {
            return "false";
        }

    }
    @RequestMapping("/tuichu")
    public String tuichu(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

}
