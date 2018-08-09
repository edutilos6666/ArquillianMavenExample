/*
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.arquillian.example;

import com.edutilos.dao.WorkerDAO;
import com.edutilos.dao.WorkerDAOJPAImpl;
import com.edutilos.model.Worker;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

/**
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
@RunWith(Arquillian.class)
public class WorkerTest {

/*//    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addClasses(Worker.class, WorkerDAO.class, WorkerDAOJPAImpl.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").
        addAsResource("hibernate.cfg.xml", "META-INF/persistence.xml");
        // System.out.println(jar.toString(true));
        return jar;
    }*/


    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "login.war")
                .addClasses(Worker.class, WorkerDAO.class, WorkerDAOJPAImpl.class)
                .addAsResource("META-INF/persistence.xml")
//                .addAsWebInfResource("jbossas-ds.xml")
//                .addAsWebResource(new File(WEBAPP_SRC, "login.xhtml"))
//                .addAsWebResource(new File(WEBAPP_SRC, "home.xhtml"))
//                .addAsWebResource(new File(WEBAPP_SRC, "register.xhtml"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//                .addAsWebInfResource(
//                        new StringAsset("<faces-config version=\"2.0\"/>"),
//                        "faces-config.xml");
    }


    @Inject
    private WorkerDAO workerDAO;

    @Test
    public void testWorkerInsert() {
        workerDAO.insert(new Worker(1, "foo", 10, 100.0, true));
        Worker one = workerDAO.findById(1);
        Assert.assertEquals(1, one.getId());
        Assert.assertEquals("foo", one.getName());
        workerDAO.delete(1);
    }
}
