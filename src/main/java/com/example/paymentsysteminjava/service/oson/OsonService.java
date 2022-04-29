package com.example.paymentsysteminjava.service.oson;

import com.example.paymentsysteminjava.dto.response.BaseApiResponse;
import com.example.paymentsysteminjava.entity.oson.OsonServiceEntity;
import com.example.paymentsysteminjava.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface OsonService extends BaseService {
    BaseApiResponse add(Long merchantServiceId, MultipartFile file);
    Page<OsonServiceEntity> getList(Integer page, Integer size, String name);
}
