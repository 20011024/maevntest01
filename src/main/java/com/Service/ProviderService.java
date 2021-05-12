package com.Service;

import com.entity.Provider;

import java.util.List;

public interface ProviderService {
    int add(Provider provider);
    int delete(Integer id);
    int update(Provider provider);
    Provider getByid(Integer id);
    List<Provider> getList();
    List<Provider> getLike(String proCode, String proName);
    Provider getByproCode(String proCode);
}
