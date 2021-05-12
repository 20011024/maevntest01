package com.Service;

import com.entity.Provider;
import com.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    ProviderMapper providerMapper;

    @Override
    public int add(Provider provider) {
        return providerMapper.add(provider);
    }

    @Override
    public int delete(Integer id) {
        return providerMapper.delete(id);
    }

    @Override
    public int update(Provider provider) {
        return providerMapper.update(provider);
    }

    @Override
    public Provider getByid(Integer id) {
        return providerMapper.getByid(id);
    }

    @Override
    public List<Provider> getList() {
        return providerMapper.getList();
    }

    @Override
    public List<Provider> getLike(String proCode, String proName) {
        return providerMapper.getLike(proCode,proName);
    }

    @Override
    public Provider getByproCode(String proCode) {
        return providerMapper.getByproCode(proCode);
    }
}
