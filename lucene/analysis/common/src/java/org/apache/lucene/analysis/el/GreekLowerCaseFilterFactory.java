package org.apache.lucene.analysis.el;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.el.GreekLowerCaseFilter;
import org.apache.lucene.analysis.util.AbstractAnalysisFactory;
import org.apache.lucene.analysis.util.MultiTermAwareComponent;
import org.apache.lucene.analysis.util.TokenFilterFactory;

/** 
 * Factory for {@link GreekLowerCaseFilter}. 
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_glc" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.StandardTokenizerFactory"/&gt;
 *     &lt;filter class="solr.GreekLowerCaseFilterFactory"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre> 
 *
 */
public class GreekLowerCaseFilterFactory extends TokenFilterFactory implements MultiTermAwareComponent {
 
  @Override
  public void init(Map<String, String> args) {
    super.init(args);
    assureMatchVersion();
    if (args.containsKey("charset"))
      throw new IllegalArgumentException(
          "The charset parameter is no longer supported.  "
          + "Please process your documents as Unicode instead.");
  }

  @Override
  public GreekLowerCaseFilter create(TokenStream in) {
    return new GreekLowerCaseFilter(luceneMatchVersion, in);
  }

  @Override
  public AbstractAnalysisFactory getMultiTermComponent() {
    return this;
  }
}

