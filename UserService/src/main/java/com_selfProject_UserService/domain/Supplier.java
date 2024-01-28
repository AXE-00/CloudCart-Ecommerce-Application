package com_selfProject_UserService.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Supplier {
    @Id
    private String userEmail;
    private String userName;
    private String supplierRole="user";
    private long phoneNo;
    private byte[] userImage;
    List<Product>productList;
    boolean approvedSupplier=false;
}
