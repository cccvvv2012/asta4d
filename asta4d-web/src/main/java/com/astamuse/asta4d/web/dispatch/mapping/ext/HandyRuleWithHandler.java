/*
 * Copyright 2012 astamuse company,Ltd.
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
 * 
 */

package com.astamuse.asta4d.web.dispatch.mapping.ext;

import java.util.List;

import com.astamuse.asta4d.web.dispatch.mapping.UrlMappingRule;
import com.astamuse.asta4d.web.util.DeclareInstanceUtil;

public class HandyRuleWithHandler extends HandyRuleWithForward {

    public HandyRuleWithHandler(UrlMappingRule rule) {
        super(rule);
    }

    public HandyRuleWithHandler handler(Object... handlerList) {
        List<Object> list = rule.getHandlerList();
        for (Object handler : handlerList) {
            list.add(DeclareInstanceUtil.createInstance((handler)));
        }
        return this;
    }

}
