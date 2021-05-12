package com.Service;

import com.entity.Bill;
import com.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService{
    @Autowired
    BillMapper billMapper;
    @Override
    public Bill lianbiao(Integer providerId,String productName) {
        return billMapper.lianbiao(providerId,productName);
    }

    @Override
    public List<Bill> getList() {
        return billMapper.getList();
    }

    @Override
    public Bill getBillById(Integer id) {
        return billMapper.getBillById(id);
    }

    @Override
    public List<Bill> getLike(String productName, Integer providerId, Integer isPayment) {
        return billMapper.getLike(productName,providerId,isPayment);
    }

    @Override
    public int delete(Integer id) {
        return billMapper.delete(id);
    }

    @Override
    public Bill getBillCode(String billCode) {
        return billMapper.getBillCode(billCode);
    }

    @Override
    public int update(Bill bill) {
        return billMapper.update(bill);
    }

    @Override
    public int add(Bill bill) {
        return billMapper.add(bill);
    }
}
