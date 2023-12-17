package com_selfProject_UserService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    private String userEmail;
    private String userName;
    private String password;
    private String role="user";
    private long phoneNo;
    private Address address;
    private byte[] userImage;
    private String imageName;
    private List<FavItems> favItems;

}
