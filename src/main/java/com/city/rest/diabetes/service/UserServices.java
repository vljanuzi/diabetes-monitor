package com.city.rest.diabetes.service;


import com.city.rest.diabetes.utilities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fin
 */
@Stateless
public class UserServices {
    
    public Users doLogin(String username, String password) {
        
        List<Users> tokens = new ArrayList<>();
        
        Users admin = new Users();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole("admin");
        admin.setName("Admin");
        admin.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE1OTA2OTczMTEsImV4cCI6MTYyMjIzMzMxMSwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0.reZp9bF9a2n6iSW_s-amLhAEA9oc4ZFOMNX7aq1lDTk");

        tokens.add(admin);

        Users user = new Users();
        user.setUsername("physician");
        user.setPassword("123123");
        user.setRole("physician");
        user.setName("pysician");
        user.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImp0aSI6ImI4ZTgwZjdlLTMxNWYtNGU4MS04NWYzLTdjMzdiNzA0MjY5OSIsImlhdCI6MTU5MDY5NzE3MywiZXhwIjoxNTkwNzAwNzczfQ.srlqhgBS7O3VRJnVld66WEcMkBb3S05cC8S5WFC0Eas");

        tokens.add(user);
        
        for(Users u: tokens) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}
