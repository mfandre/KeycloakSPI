package org.halliburton.keycoak.user.utils;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;

/**
 * @author André de Mattos Ferraz
 */
public interface ExternalUserProviderClient {
    @GET
    @Path("/getUser")
    String getUser(@HeaderParam("user") String username);

    @GET
    @Path("/getUserById")
    String getUserById(@HeaderParam("id_person") String userId);

    @GET
    @Path("/validatePassword")
    String validatePassword(@HeaderParam("user") String username,
                            @HeaderParam("password") String password);
}
