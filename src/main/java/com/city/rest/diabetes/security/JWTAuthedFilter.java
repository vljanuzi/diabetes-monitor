package com.city.rest.diabetes.security;


import com.city.rest.diabetes.utilities.Users;
import com.city.rest.diabetes.utilities.WSResponse;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@JWTAuthed
@Priority(Priorities.AUTHENTICATION)
public class JWTAuthedFilter extends WSResponse implements ContainerRequestFilter {

      final static List<Users> TOKENS = new ArrayList<>();

    public JWTAuthedFilter() {
        Users admin = new Users();
        admin.setRole("admin");
        admin.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE1OTA2OTczMTEsImV4cCI6MTYyMjIzMzMxMSwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0.reZp9bF9a2n6iSW_s-amLhAEA9oc4ZFOMNX7aq1lDTk");

        TOKENS.add(admin);

        Users user = new Users();
        user.setRole("user");
        user.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImp0aSI6ImI4ZTgwZjdlLTMxNWYtNGU4MS04NWYzLTdjMzdiNzA0MjY5OSIsImlhdCI6MTU5MDY5NzE3MywiZXhwIjoxNTkwNzAwNzczfQ.srlqhgBS7O3VRJnVld66WEcMkBb3S05cC8S5WFC0Eas");

        TOKENS.add(user);
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
       
        boolean authedStatus = false;
     
            for (Users tTmp : TOKENS) {
                try {
                     if (tTmp.getToken().equals(token.split(" ")[1])) {
                        authedStatus = true;
                    }
                } catch (Exception e) {

                }    
        }

        if (!authedStatus) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(jsonResponse(403, "Access forbidden", null)).build());
        }
    }
}
