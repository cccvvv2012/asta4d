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

package com.astamuse.asta4d.test.unit;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.astamuse.asta4d.Context;
import com.astamuse.asta4d.format.NamedPlaceholderFormatter;
import com.astamuse.asta4d.format.NumberPlaceholderFormatter;
import com.astamuse.asta4d.format.SymbolPlaceholderFormatter;
import com.astamuse.asta4d.test.render.infra.BaseTest;
import com.astamuse.asta4d.util.i18n.ParamMapResourceBundleHelper;
import com.astamuse.asta4d.util.i18n.ResourceBundleHelper;

public class ResourceBundleUtilTest extends BaseTest {

    @Test
    public void useHelperDefault() throws Exception {
        setUp("symbol_placeholder_messages");
        ResourceBundleHelper helper = new ResourceBundleHelper();
        assertEquals(helper.getMessage("weatherreport1", "Tomorrow", "sunny"), "Tomorrow's weather is sunny.");
        assertEquals(
                helper.getMessage("weatherreport2", helper.getExternalParamValue("weatherreport2", "date"),
                        helper.getExternalParamValue("weatherreport2", "weather")), "Today's weather is cloudy.");
    }

    @BeforeClass
    public void setDefaultLocale() {
        Locale.setDefault(Locale.ROOT);
    }

    @Test
    public void useSymbolFormatter() throws Exception {
        setUp("symbol_placeholder_messages");
        ResourceBundleHelper helper = new ResourceBundleHelper(new SymbolPlaceholderFormatter());
        assertEquals(helper.getMessage("weatherreport1", "Tomorrow", "sunny"), "Tomorrow's weather is sunny.");
        assertEquals(
                helper.getMessage("weatherreport2", helper.getExternalParamValue("weatherreport2", "date"),
                        helper.getExternalParamValue("weatherreport2", "weather")), "Today's weather is cloudy.");
    }

    @Test
    public void useSymbolFormatterJaJp() throws Exception {
        setUp("symbol_placeholder_messages");
        ResourceBundleHelper helper = new ResourceBundleHelper(Locale.JAPAN, new SymbolPlaceholderFormatter());
        assertEquals(helper.getMessage("weatherreport1", "明日", "晴れ"), "明日の天気は晴れです。");
        assertEquals(
                helper.getMessage("weatherreport2", helper.getExternalParamValue("weatherreport2", "date"),
                        helper.getExternalParamValue("weatherreport2", "weather")), "今日の天気は曇りです。");
    }

    @Test
    public void useNumberFormatter() throws Exception {
        setUp("number_placeholder_messages");
        ResourceBundleHelper helper = new ResourceBundleHelper(new NumberPlaceholderFormatter());
        assertEquals(helper.getMessage("weatherreport1", "Tomorrow", "sunny"), "Tomorrow's weather is sunny.");
        assertEquals(
                helper.getMessage("weatherreport2", helper.getExternalParamValue("weatherreport2", "date"),
                        helper.getExternalParamValue("weatherreport2", "weather")), "Today's weather is cloudy.");
    }

    @Test
    public void useNumberFormatterJaJp() throws Exception {
        setUp("number_placeholder_messages");
        ResourceBundleHelper helper = new ResourceBundleHelper(Locale.JAPAN, new NumberPlaceholderFormatter());
        assertEquals(helper.getMessage("weatherreport1", "明日", "晴れ"), "明日の天気は晴れです。");
        assertEquals(
                helper.getMessage("weatherreport2", helper.getExternalParamValue("weatherreport2", "date"),
                        helper.getExternalParamValue("weatherreport2", "weather")), "今日の天気は曇りです。");
    }

    @Test
    public void useNamedFormatter() throws Exception {
        setUp("named_placeholder_messages");
        ParamMapResourceBundleHelper helper = new ParamMapResourceBundleHelper(new NamedPlaceholderFormatter());
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("date", "Tomorrow");
            params.put("weather", "sunny");
            assertEquals(helper.getMessage("weatherreport1", params), "Tomorrow's weather is sunny.");
        }
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("date", helper.getExternalParamValue("weatherreport2", "date"));
            params.put("weather", helper.getExternalParamValue("weatherreport2", "weather"));
            assertEquals(helper.getMessage("weatherreport2", params), "Today's weather is cloudy.");
        }
    }

    @Test
    public void useNamedFormatterJaJp() throws Exception {
        setUp("named_placeholder_messages");
        ParamMapResourceBundleHelper helper = new ParamMapResourceBundleHelper(Locale.JAPAN, new NamedPlaceholderFormatter());
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("date", "明日");
            params.put("weather", "晴れ");
            assertEquals(helper.getMessage("weatherreport1", params), "明日の天気は晴れです。");
        }
        {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("date", helper.getExternalParamValue("weatherreport2", "date"));
            params.put("weather", helper.getExternalParamValue("weatherreport2", "weather"));
            assertEquals(helper.getMessage("weatherreport2", params), "今日の天気は曇りです。");
        }
    }

    private void setUp(String fileName) {
        Context context = Context.getCurrentThreadContext();
        context.setCurrentLocale(Locale.US);
        context.getConfiguration().setResourceNames("com.astamuse.asta4d.test.render.messages." + fileName);
    }
}
