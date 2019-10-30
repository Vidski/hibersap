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

package org.hibersap.execution;

import java.util.Collection;
import java.util.Map;

public final class UnsafeCastHelper {

    private UnsafeCastHelper() {
        // use static methods
    }

    @SuppressWarnings("unchecked")
    public static Collection<Object> castToCollection(final Object value) {
        return (Collection<Object>) value;
    }

    @SuppressWarnings("unchecked")
    public static Collection<Map<String, Object>> castToCollectionOfMaps(final Object value) {
        return (Collection<Map<String, Object>>) value;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> castToMap(final Object value) {
        return Map.class.cast(value);
    }
}
