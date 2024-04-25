package com.room.good.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


//단순히 <dto,en> 이렇게 두가지의 타입을 지정할 수 있게 들어가있음 -> 제네릭의 장점
// 이렇게 dto와 en을 정해주면 private List<DTO> dtoList;이렇게 dto리스트를 만들어주는데 이 dto자리에는 movieDTO가 올 수도 있고 , reviewDTO가 올수도 있고 여러 DTO가 올 수 있음
// EN도 마찬가지로 유동성있게 처리가 가능하다
// 하지만 유동적이지만서도 불필요한 형변환을 할 필요도 없고 , 타입오류, 런타임오류가 발생하지않음  ===>>> 안정성이 높아짐!
@Data
public class PageResultDTO<DTO, EN> {

    //DTO리스트
    private List<DTO> dtoList; // 여기 DTO라 써있는 곳에는 여러타입의 DTO가 올 수 있음 , 뭔말이냐 -> private List<movieDTO> dtoList -> 이렇게 만들어져있었으면 저 무비디티오밖에 못들어옴
    // 하지만 클래스 자체에서 두개의 타입이 들어오면 그 타입에 맞게 안에 인스턴스들이 생성이 되기 때문에 타입에러 및 불필요한 형변환 , 런타임오류 등을 줄일 수 있음!!

    //총 페이지 번호
    private int totalPage;

    //현재 페이지 번호
    private int page;
    //목록 사이즈
    private int size;

    //시작 페이지 번호, 끝 페이지 번호
    private int start, end;

    //이전, 다음
    private boolean prev, next;

    //페이지 번호  목록
    private List<Integer> pageList;

    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn ){// 이 생성자를 부르면서 안에 인스턴스들 값 들어있는 거 전부 리턴해준다 .

        //아까 만들었던 fn그릇을 여기서 사용중 !
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        //stream() = > 리스트를 스트림으로 바꾼단 뜻이고 , 스트림이란 자바에서 시퀀스를 뜻함 .-> 즉 연속적으로 리스트를 넣어준다는 거 같음
        //.map(fn) => 스트림을 통해 넣은 리스트들을 fn의 그릇형태로 매핑! =>  각 요소를 fn 함수에 정의된 로직에 따라 변환
        //.collect(Collectors.toList()); => 로직에 의해 변환된 친구들 list형식으로 바꿔주겠다.
        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }


    private void makePageList(Pageable pageable){

        this.page = pageable.getPageNumber() + 1; // 0부터 시작하므로 1을 추가
        this.size = pageable.getPageSize();

        //temp end page
        int tempEnd = (int)(Math.ceil(page/5.0)) * 5;

        start = tempEnd - 4;

        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd: totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }

}
