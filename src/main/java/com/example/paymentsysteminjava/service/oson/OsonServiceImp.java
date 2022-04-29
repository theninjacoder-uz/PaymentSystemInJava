package com.example.paymentsysteminjava.service.oson;

import com.example.paymentsysteminjava.dto.response.BaseApiResponse;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.oson.OsonServiceEntity;
import com.example.paymentsysteminjava.repository.merchant.MerchantServiceRepository;
import com.example.paymentsysteminjava.repository.OsonRepository;
import com.example.paymentsysteminjava.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class OsonServiceImp implements OsonService {

    private final OsonRepository osonRepository;
    private final MerchantServiceRepository merchantServiceRepository;
    private final FileService fileService;
    private final ModelMapper modelMapper;
    private final int DEFAULT_PAGE_SIZE = 10;



    @Override
    public BaseApiResponse add(Long merchantServiceId, MultipartFile file) {

        MerchantServiceEntity merchantServiceEntity
                = merchantServiceRepository.findByMerchantServiceId(merchantServiceId);

        //TODO: 11.04.2022 use appropriate exception
        if (merchantServiceEntity == null)
            throw new UsernameNotFoundException("merchant service not found");

        OsonServiceEntity osonServiceEntity = new OsonServiceEntity();
        osonServiceEntity.setMerchantServiceEntity(merchantServiceEntity);
        osonRepository.save(osonServiceEntity);
         return new BaseApiResponse(1, "Service created", fileService.saveFile(file));
    }

    @Override
    public Page<OsonServiceEntity> getList(Integer page, Integer size, String sortingKey) {

            Pageable pageable = PageRequest.of(
                    page == null ? 0 : page,
                    size == null ? DEFAULT_PAGE_SIZE : size,
                    Sort.by(sortingKey).ascending());

        return osonRepository.findAll(pageable);
    }
}
