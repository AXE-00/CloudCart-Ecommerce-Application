package com_selfProject_UserService.service;

import com_selfProject_UserService.config.EcommDTO;
import com_selfProject_UserService.domain.Product;
import com_selfProject_UserService.domain.Supplier;
import com_selfProject_UserService.domain.User;
import com_selfProject_UserService.exception.UserNotFoundException;
import com_selfProject_UserService.repository.SupplierRepo;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplierService implements ISupplierService{

    private  final SupplierRepo supplierRepo;
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;


    @Autowired
    public SupplierService(SupplierRepo supplierRepo, RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.supplierRepo = supplierRepo;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    @Override
    public Supplier addSupplier(User user) {
        Supplier supplier = new Supplier();
        supplier.setUserEmail(user.getUserEmail());
        supplier.setUserName(user.getUserName());
        supplier.setPhoneNo(user.getPhoneNo());
        supplier.setUserImage(user.getUserImage());
        supplierRepo.save(supplier);
        return supplier;
    }

    @Override
    public Supplier addProduct(String email, Product product) {
        return null;
    }

    @Override
    public List<Supplier> getSuppliersWaiting() {
        List<Supplier> waitingToApprove = new ArrayList<>();
        List<Supplier> supplierList = supplierRepo.findAll();
        for(Supplier supplier:supplierList){
            if(!supplier.isApprovedSupplier()){
                waitingToApprove.add(supplier);
            }
        }
        return waitingToApprove;
    }

    @Override
    public void approveOrNot(String email,boolean select) {
        Supplier supplier = supplierRepo.findById(email).get();
        if(select){
            supplier.setApprovedSupplier(true);
            supplier.setSupplierRole("supplier");
            supplierRepo.save(supplier);

            String message = "Dear " + supplier.getUserName() + ",\n" +
                    "Thank you for Applying for becoming a Supplier for Cloud Cart!\n" +
                    "We are delighted to tell you that we have Approved your request.\n\n" +
                    "Congratulation on becoming Cloud-cart supplier,If you need any assistance then, feel free to reach out to our support team."+
                    "Once again, Congratulation!\n\n" +
                    "Best regards,\n" +
                    "Team CloudCart\n";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("to",supplier.getUserEmail());
            jsonObject.put("subject","Supplier Request Approved");
            jsonObject.put("message",message);

            EcommDTO ecommDTO = new EcommDTO();
            ecommDTO.setJsonObject(jsonObject);

            rabbitTemplate.convertAndSend(directExchange.getName(),"Cloud-Key",ecommDTO);
        }
        else {
            String message = "Dear " + supplier.getUserName() + ",\n" +
                    "Thank you for Applying for becoming a Supplier for Cloud Cart!\n" +
                    "Sorry to tell you that you request have been denied\n\n" +
                    "Feel free to reach out to our support team.\n\n"+
                    "Best regards,\n" +
                    "Team CloudCart\n";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("to",supplier.getUserEmail());
            jsonObject.put("subject","Supplier Request Approved");
            jsonObject.put("message",message);

            EcommDTO ecommDTO = new EcommDTO();
            ecommDTO.setJsonObject(jsonObject);

            rabbitTemplate.convertAndSend(directExchange.getName(),"Cloud-Key",ecommDTO);
            supplierRepo.deleteById(supplier.getUserEmail());
        }
    }

    @Override
    public Map<String,String> supplierRole(String email) {
        Supplier supplier = supplierRepo.findById(email).get();

        Map<String,String> suppData = new HashMap<>();
        suppData.put("suppRole",supplier.getSupplierRole());

        return suppData;
    }

    @Override
    public byte[] getUserImage(String email)throws UserNotFoundException {
        if(supplierRepo.findById(email).isEmpty()){
            System.out.println("User not exist"); // user defined exception
            throw new UserNotFoundException();
        }
        Supplier supplier = supplierRepo.findById(email).get();
        System.out.println(supplier.getUserEmail());
        return supplier.getUserImage();
    }
}
