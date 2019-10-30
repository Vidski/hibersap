/*
 * Copyright (c) 2008-2019 akquinet tech@spree GmbH
 *
 * This file is part of Hibersap.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this software except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hibersap.interceptor;

/**
 * Implementations may be registered on the SessionManager and will then be called before and after
 * a function module in SAP is called. The Bapi object itself will be passed to the methods.
 */
public interface BapiInterceptor {

    /**
     * Will be called before the function module is called in SAP.
     *
     * @param bapiObject The Bapi object as provided by the application code.
     */
    void beforeExecution(Object bapiObject);

    /**
     * Will be called after the function module is called in SAP.
     *
     * @param bapiObject The Bapi object as provided by the application code.
     */
    void afterExecution(Object bapiObject);
}
