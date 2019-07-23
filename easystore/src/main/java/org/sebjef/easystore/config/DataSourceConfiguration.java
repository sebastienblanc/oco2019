package org.sebjef.easystore.config;

/*
 * Copyright 2019 JF James.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;

/**
 * With Payara hsqdb.har should be copied into glassfish/domains/domain1/lib/ext
 * 
 * @author JF James
 */
@ApplicationScoped
@DataSourceDefinition(
  name = "java:app/jdbc/merchant", 
  className = "org.hsqldb.jdbcDriver", 
  url = "jdbc:hsqldb:hsql://localhost/devoxx",
  user = "sa",
  password = "",
  maxPoolSize = 5,
  minPoolSize = 5,
  maxStatements = 100
  )
public class DataSourceConfiguration {
    
}
