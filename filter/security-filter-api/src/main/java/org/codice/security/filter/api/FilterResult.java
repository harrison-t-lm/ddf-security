/**
 * Copyright (c) Codice Foundation
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details. A copy of the GNU Lesser General Public License
 * is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 *
 **/
package org.codice.security.filter.api;

import org.apache.cxf.ws.security.tokenstore.SecurityToken;
import org.apache.shiro.authc.AuthenticationToken;

public class FilterResult implements AuthenticationToken {
    public enum FilterStatus {
        // completed - auth tokens retrieved ready to move on
        COMPLETED,

        // no tokens found, no attempt made to obtain any
        NO_ACTION,

        // performing action to obtain auth tokens, stop processing
        REDIRECTED
    }

    private FilterStatus status;

    private Object principal;

    private String authCredentials;

    private SecurityToken token;

    public FilterResult() {
        status = FilterStatus.NO_ACTION;
    }

    public FilterResult(FilterStatus fs, Object p, String creds) {
        this.status = fs;
        this.principal = p;
        this.authCredentials = creds;
    }

    public FilterResult(FilterStatus fs, Object p, SecurityToken t) {
        this.status = fs;
        this.principal = p;
        this.token = t;
    }

    public FilterStatus getStatus() {
        return status;
    }

    public void setStatus(FilterStatus status) {

        this.status = status;
    }

    public void setPrincipal(Object obj) {
        this.principal = obj;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthCredentials(String creds) {
        this.authCredentials = creds;
    }

    public void setSecurityToken(SecurityToken t) {
        this.token = t;
    }

    public boolean hasSecurityToken() { return this.token != null; }

    @Override
    public Object getCredentials() {
        return token != null ? token : authCredentials;
    }
}