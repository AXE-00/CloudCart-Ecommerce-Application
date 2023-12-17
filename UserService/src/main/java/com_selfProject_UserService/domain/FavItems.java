package com_selfProject_UserService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavItems {
    private int itemId;
    private String itemName;
    private float itemPrice;
    private float itemRating;
    private String imageUrl;
}
