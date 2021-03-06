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

package com.astamuse.asta4d.test.render;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.testng.annotations.Test;

import com.astamuse.asta4d.render.GoThroughRenderer;
import com.astamuse.asta4d.render.Renderer;
import com.astamuse.asta4d.test.render.infra.BaseTest;
import com.astamuse.asta4d.test.render.infra.SimpleCase;
import com.astamuse.asta4d.util.ElementUtil;

public class AdvancedRenderingTest extends BaseTest {

    public static class TestRender {
        public Renderer continualSelectAll() {

            Renderer render = new GoThroughRenderer();

            // replace element
            Element elem = ElementUtil.parseAsSingle("<div>i am a danymic element</div>");
            Renderer repElem = Renderer.create("*", elem);
            repElem.add("*", "replacetext");

            render.add("#d1", repElem);

            Renderer attrSet = Renderer.create("div", "a", "b");
            attrSet.add("#d3[a=b]", "+class", "tc");

            render.add("#d2", attrSet);

            return render;
        }

        public Renderer setAnyTypeOuter(int count) {
            Renderer render = new GoThroughRenderer();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                list.add("listParam" + i);
            }
            render.add("#outerDiv", "outerValue");
            render.add("#inner", "outerList", list);
            render.add("#outer", "-count", null);
            return render;
        }

        public Renderer setAnyTypeInner(List<String> outerList) {
            Renderer render = new GoThroughRenderer();
            render.add("#innerList > li", outerList);
            render.add("#inner", "-outerList", null);
            return render;
        }
    }

    public AdvancedRenderingTest() {

    }

    @Test
    public void testContinualSelectAll() {
        new SimpleCase("AdvancedRendering_continualSelectAll.html");
    }

    @Test
    public void testDataRef() {
        new SimpleCase("AdvancedRendering_dataRef.html");
    }
}
