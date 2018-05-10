/*
 * Copyright 2006-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.consol.citrus.samples.todolist;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.design.TestDesigner;
import com.consol.citrus.dsl.testng.TestNGCitrusTest;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.*;

/**
 * @author Christoph Deppisch
 */
@Test(invocationCount = 10, threadPoolSize = 25)
public class TodoListLoadTestIT extends TestNGCitrusTest {

    @Autowired
    private HttpClient todoClient;

    @Parameters( { "designer" })
    @CitrusTest
    public void testAddTodo(@Optional @CitrusResource TestDesigner designer) {
        designer.http()
            .client(todoClient)
            .send()
            .get("/today");

        designer.http()
            .client(todoClient)
            .receive()
            .response(HttpStatus.OK);
    }

    @Parameters( { "designer" })
    @CitrusTest
    public void testListTodos(@Optional @CitrusResource TestDesigner designer) {
        designer.http()
            .client(todoClient)
            .send()
            .get("/today");

        designer.http()
            .client(todoClient)
            .receive()
            .response(HttpStatus.OK);
    }

}
