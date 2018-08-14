/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.superbiz.moviefun.rest;

import com.github.javafaker.Faker;
import org.superbiz.moviefun.PessoaJuridica;
import org.superbiz.moviefun.PessoaJuridicaBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Singleton
@Startup
public class LoadDataResource {

    @EJB
    private PessoaJuridicaBean pessoaJuridicaBean;

    @PostConstruct
    public void load() {
        List<PessoaJuridica> pjs = pessoaJuridicaBean.getPessoasJuridicas(null, null, null, null);

        if(pjs != null && pjs.size() > 0){
            pjs.forEach(p -> {
                pessoaJuridicaBean.deletePessoaJuridica(p.getId());
            });
        }

        final Faker faker = new Faker(Locale.ENGLISH);
        final Random random = new Random(System.nanoTime());

        int crm = 1000;
        for (int i = 0 ; i < (5 + random.nextInt(20)) ; i++) {
            pessoaJuridicaBean.addPessoaJuridica(new PessoaJuridica(String.valueOf(crm++), faker.company().name()));
        }
    }
}