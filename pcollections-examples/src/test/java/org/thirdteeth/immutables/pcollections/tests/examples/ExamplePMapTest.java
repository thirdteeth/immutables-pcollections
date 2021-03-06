/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

package org.thirdteeth.immutables.pcollections.tests.examples;


import org.junit.Assert;
import org.junit.Test;
import org.pcollections.HashPMap;
import org.pcollections.HashTreePMap;
import org.thirdteeth.immutables.pcollections.examples.ImmutableExamplePMapType;

import java.util.HashMap;
import java.util.Map;

public final class ExamplePMapTest
{
  @Test
  public void testAdd()
  {
    final ImmutableExamplePMapType.Builder b =
            ImmutableExamplePMapType.builder();

    b.putIntegers("A", Integer.valueOf(0));
    b.putIntegers("B", Integer.valueOf(1));
    b.putIntegers("C", Integer.valueOf(2));

    final ImmutableExamplePMapType a0 = b.build();
    Assert.assertEquals(Integer.valueOf(0), a0.integers().get("A"));
    Assert.assertEquals(Integer.valueOf(1), a0.integers().get("B"));
    Assert.assertEquals(Integer.valueOf(2), a0.integers().get("C"));
  }

  @Test
  public void testAddAll()
  {
    final ImmutableExamplePMapType.Builder b =
            ImmutableExamplePMapType.builder();
    Map<String, Integer> integerMap = new HashMap<>();
    integerMap.put("A", Integer.valueOf(0));
    integerMap.put("B", Integer.valueOf(1));
    integerMap.put("C", Integer.valueOf(2));
    HashPMap pMap = HashTreePMap.from(integerMap);
    b.integers(pMap);

    final ImmutableExamplePMapType a0 = b.build();
    Assert.assertEquals(Integer.valueOf(0), a0.integers().get("A"));
    Assert.assertEquals(Integer.valueOf(1), a0.integers().get("B"));
    Assert.assertEquals(Integer.valueOf(2), a0.integers().get("C"));
  }

  @Test
  public void testSetJavaMap()
  {
    final ImmutableExamplePMapType.Builder b =
            ImmutableExamplePMapType.builder();

    final java.util.Map<String, Integer> m = new java.util.HashMap<>(3);
    m.put("A", Integer.valueOf(0));
    m.put("B", Integer.valueOf(1));
    m.put("C", Integer.valueOf(2));

    b.setJavaMapIntegers(m);

    final ImmutableExamplePMapType a0 = b.build();
    Assert.assertEquals(Integer.valueOf(0), a0.integers().get("A"));
    Assert.assertEquals(Integer.valueOf(1), a0.integers().get("B"));
    Assert.assertEquals(Integer.valueOf(2), a0.integers().get("C"));
  }
}
