package com.mapper;

import com.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {
    int add(Provider provider);
    int delete(@Param("id") Integer id);
    int update(Provider provider);
    Provider getByid(@Param("id") Integer id);
    List<Provider> getLike(@Param("proCode") String proCode, @Param("proName") String proName);
    List<Provider> getList();
    Provider getByproCode(@Param("proCode") String proCode);
}
