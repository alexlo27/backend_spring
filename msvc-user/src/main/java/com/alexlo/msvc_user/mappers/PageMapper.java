package com.alexlo.msvc_user.mappers;

import com.alexlo.msvc_user.dto.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public class PageMapper {

    public static <S, T> PageResponse<T> map(Page<S> page, Function<S, T> converter){

        /*List<T> content = page.getContent()
                .stream()
                .map(converter)
                .toList();*/
        Page<T> page2 = page.map(converter);

        return new PageResponse<>(
                //content,
                page2.getContent(),
                page2.getNumber() + 1,
                page2.getSize(),
                page2.getTotalElements(),
                page2.getTotalPages(),
                page2.isLast()
        );

    }

    public static <T> PageResponse<T> map(Page<T> page){
        return new PageResponse<>(
                page.getContent(),
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
