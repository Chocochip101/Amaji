package com.chocochip.amaji.menu.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class MenuTest {
    @DisplayName("메뉴를 빌더 패턴을 활용하여 생성할 수 있다")
    @Test
    public void createMenu() throws Exception {
        //given
        Long id = 1L;
        Integer price = 10000;
        String name = "규카츠";


        //when
        Menu menu = Menu.builder()
                .name(name)
                .price(price)
                .build();

        //then
        Assertions.assertEquals(menu.getName(), name);
        Assertions.assertEquals(menu.getPrice(), price);
    }
}