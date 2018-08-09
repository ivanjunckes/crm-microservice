/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.superbiz.moviefun.rest;

import org.apache.commons.lang.StringUtils;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.superbiz.moviefun.PessoaJuridica;
import org.superbiz.moviefun.PessoaJuridicaBean;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.logging.Logger;

@Path("pj")
@Produces({"application/json"})
@ApplicationScoped
public class PessoaJuridicaResource {

    private static final Logger LOGGER = Logger.getLogger(PessoaJuridicaResource.class.getName());

    @EJB
    private PessoaJuridicaBean service;

    @Inject
    @Claim("username")
    private ClaimValue<String> username;

    @Inject
    @Claim("email")
    private ClaimValue<String> email;

    @Inject
    @Claim("jti")
    private ClaimValue<String> jti;

    @Inject
    private JsonWebToken jwtPrincipal;

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("{crm}")
    public PessoaJuridica find(@PathParam("crm") String crm) {
        LOGGER.info("find: " + toIdentityString());
        return service.findByCrm(crm);
    }

    private String toIdentityString() {
        if (jwtPrincipal == null) {
            return "no authenticated user.";
        }

        final StringBuilder builder = new StringBuilder();

        builder.append(username);
        builder.append(String.format(" (jti=%s)", jti));
        builder.append(String.format(" (email=%s)", email));
        builder.append(String.format(" (groups=%s)", StringUtils.join(jwtPrincipal.getGroups(), ", ")));
        return builder.toString();
    }

    @GET
    public List<PessoaJuridica> getPessoasJuridicas(@QueryParam("first") Integer first, @QueryParam("max") Integer max,
                                                    @QueryParam("field") String field, @QueryParam("searchTerm") String searchTerm) {
        LOGGER.info("list: " + toIdentityString());
        return service.getPessoasJuridicas(first, max, field, searchTerm);
    }
}