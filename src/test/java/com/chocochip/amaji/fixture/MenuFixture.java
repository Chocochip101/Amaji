package com.chocochip.amaji.fixture;

import com.chocochip.amaji.menu.domain.Menu;

public class MenuFixture {
        private MenuFixture(){}

    public static Menu createRamen() {
        return Menu.builder()
                .name("돈코츠라멘")
                .price(10000).build();
    }

    public static Menu createSushi() {
        return Menu.builder()
                .name("스시")
                .price(13000).build();
    }
}
