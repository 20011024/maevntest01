package com.mapper;

import com.entity.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    Bill lianbiao(@Param("pid") Integer providerId, @Param("pname") String productName);//连表查询
    List<Bill> getList();
    Bill getBillById(@Param("billid") Integer id);
    int delete(@Param("id") Integer id);
    List<Bill> getLike(@Param("productName") String productName, @Param("providerId") Integer providerId, @Param("isPayment") Integer isPayment);

    Bill getBillCode(@Param("billCode") String billCode);

    int update(Bill bill);

    int add(Bill bill);
}
