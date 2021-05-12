package com.Service;

import com.entity.Bill;

import java.util.List;

public interface BillService {
    Bill lianbiao(Integer providerId, String productName);//连表查询
    List<Bill> getList();
    Bill getBillById(Integer id);
    List<Bill> getLike(String productName, Integer providerId, Integer isPayment);
    int delete(Integer id);

    Bill getBillCode(String billCode);

    int update(Bill bill);

    int add(Bill bill);
}
