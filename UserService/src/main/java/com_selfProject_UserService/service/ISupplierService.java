package com_selfProject_UserService.service;

import com_selfProject_UserService.domain.Product;
import com_selfProject_UserService.domain.Supplier;
import com_selfProject_UserService.domain.User;
import com_selfProject_UserService.exception.UserNotFoundException;

import java.util.List;
import java.util.Map;

public interface ISupplierService {
    Supplier addSupplier(User user);
    Supplier addProduct(String email, Product product);
    List<Supplier> getSuppliersWaiting();
    void approveOrNot(String email,boolean select);
    Map<String,String> supplierRole(String email) throws UserNotFoundException;
    public byte[] getUserImage(String email)throws UserNotFoundException;
}
