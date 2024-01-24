package com_selfProject_UserService.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.json.simple.JSONObject;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class EcommDTO {
    private JSONObject jsonObject;
}
