package com.room.good.service;

import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.dto.ProductDTO;
import com.room.good.entity.Product;
import com.room.good.entity.ProductImage;
import com.room.good.entity.ProductImage2;
import com.room.good.repository.ProductttRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductttServiceImpl implements ProductttService{

    private final ProductttRepository productttRepository;

    @Override
    public PageResultDTO<ProductDTO, Object[]> getListtt(PageRequestDTO requestDTO ,String priceno) {
        Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());// mno 기준으로 솔팅!

        Page<Object[]> result =null;
        if(priceno.equals("0")){
             result = productttRepository.getListcheapPageAll(pageable); // 리스트를 페이징처리해서 가져옴
        }else if (priceno.equals("1")){
            result = productttRepository.getListcheapPage1000(pageable);
        }else if (priceno.equals("2")){
            result = productttRepository.getListcheapPage3000(pageable);
        }else if (priceno.equals("3")){
            result = productttRepository.getListcheapPage5000(pageable);
        }else if (priceno.equals("4")){
            result = productttRepository.getListcheapPage7000(pageable);
        }else if (priceno.equals("5")){
            result = productttRepository.getListcheapPage9000(pageable);
        }
        log.info("==============================================result"+result);
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });

        Function<Object[], ProductDTO> fn = (arr -> entitiesToDTO(
                (Product) arr[0],
                (List<ProductImage>) Arrays.asList((ProductImage) arr[1]),
                (List<ProductImage2>) Arrays.asList((ProductImage2) arr[2])));
        // PageResultDTO<DTO,EN> pageResult = new PageResultDTO<>(result, fn);
        // return pageResult; 이거랑 같은 뜻임 . -> 즉 pageResult에 저 생성자 정보들
        return new PageResultDTO<>(result, fn);
        // 위에서 만들었던 펑션 -> <Object[], MovieDTO> 이 그릇을 가지고있는 fn과 result를 가지고 생성자를 호출중
    }

}
