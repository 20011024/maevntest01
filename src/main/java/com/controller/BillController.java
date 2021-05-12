package com.controller;

import com.Service.BillService;
import com.Service.ProviderService;
import com.entity.Bill;
import com.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BillController {
    @Autowired
   private BillService billService;
    @Autowired
    private ProviderService providerService;
    @RequestMapping("/billList")
    public String getList(Model model, HttpSession session){
        List<Bill> blist = billService.getList();
        List<Provider> plist = providerService.getList();
        model.addAttribute("bList",blist);
        session.setAttribute("pList",plist);
        return "bill_list";
    }
    @RequestMapping("/toBillView")
    public String toBillView(Integer id,Model model){
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill",bill);
        return "bill_view";
    }
    @RequestMapping("/getBillLike")
    public String getLike(String productName,Integer providerId,Integer isPayment ,Model model){
        List<Bill> list = billService.getLike(productName, providerId, isPayment);
        model.addAttribute("bList",list);
        return "bill_list";
    }
    @RequestMapping("/deleteBill")
    @ResponseBody
    public String delete(Integer id){
        int delete = billService.delete(id);
        if (delete>0){
            return "true";
        }else {
            return "false";
        }
    }
    @RequestMapping("/toAddBill")
    public String toAddBill(){
      return "bill_add";
    }
    @RequestMapping("/toModifyBill")
    public String toModifyBill(Integer id,Model model){
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill",bill);
        return "bill_modify";
    }

    @RequestMapping("/billCode")
    @ResponseBody
    public String billCode(String billCode){
        Bill bill = billService.getBillCode(billCode);
        if (bill== null){
            return "true";
        }
        return "false";
    }

    @RequestMapping("/ModifyBill")
    @ResponseBody
    public String ModifyBill(Bill bill){
      int count = billService.update(bill);
      if (count>0) {
          return "true";
      }else {
          return "false";
      }
    }
    @RequestMapping("/addBill")
    @ResponseBody
    public String addBill(Bill bill){
        int count = billService.add(bill);
        if (count>0) {
            return "true";
        }else {
            return "false";
        }
    }
}
