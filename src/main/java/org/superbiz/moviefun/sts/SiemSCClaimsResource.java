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
package org.superbiz.moviefun.sts;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("siem/sc/claims")
public class SiemSCClaimsResource {

    private static HashMap<String, SiemUser> data = new HashMap<>();

    static {
        data.put("nilzia", new SiemUser("076.289.788-86", "Fiscalização", "12345678", "SC"));
        data.put("zuleica", new SiemUser("071.265.324-31", "Pessoa Física", "12345678", "SC"));
        data.put("claudia", new SiemUser("082.389.912-71", "Pessoa Jurídica", "12345678", "SC"));
        data.put("claudete", new SiemUser("082.389.912-71", "Processo Ético Profissional", "12345678", "SC"));
        data.put("rsv.guima", new SiemUser("099.212.789-12", "Informática", "12345678", "SC"));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SiemUser authenticate(final Map<String, String> payload) {
        String username = payload.get("username");
        SiemUser user = data.get(username);
        return user;
    }

}