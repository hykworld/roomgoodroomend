package com.room.good.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * CustomAnnotationCollection 설명: Custom 애너테이션 인터페이스 관리
 * 작성자: 김찬빈
 * 버전 정보: 1.0.0
 * 작성일자: 2023/04/20
 **/
public class CustomAnnotationCollection {

    @Target({PARAMETER, FIELD, METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = EmailValidator.class)
    @Documented
    public @interface CustomEmail {


        String message() default "로컬 최대 64자, 로컬에서 밑줄(_) 하이픈(-) 점(.) 허용, " +
                "로컬 시작과 끝에 점(.) 사용 불가능, 로컬 점(.) 연속 두 개 사용 불가능";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

}
