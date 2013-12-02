/*
 * Copyright 2013 Hans-Joachim Kliemeck.
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
package de.javakaffee.web.msm;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.catalina.connector.Response;

/**
 *
 * @author Hans-Joachim Kliemeck
 */
public class RequestTrackingHostValve extends AbstractRequestTrackingHostValve {
    public RequestTrackingHostValve(String ignorePattern, String sessionCookieName, MemcachedSessionService sessionBackupService, Statistics statistics, AtomicBoolean enabled, CurrentRequest currentRequest) {
        super(ignorePattern, sessionCookieName, sessionBackupService, statistics, enabled, currentRequest);
        
        // enable async since host valve is used by all applications
        setAsyncSupported(true);
    }

    protected String[] getResponseSetCookieHeaders(final Response response) {
        Collection<String> result = response.getHeaders("Set-Cookie");
        return result.toArray(new String[result.size()]);
    }
}
