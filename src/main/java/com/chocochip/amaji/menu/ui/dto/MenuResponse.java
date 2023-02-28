package com.chocochip.amaji.menu.ui.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuResponse {
    private String menu_name;
    private Integer menu_price;
    private List<String> menu_picture_url;

}
