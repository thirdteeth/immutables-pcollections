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
import org.thirdteeth.immutables.pcollections.examples.ImmutableExamplePStackType;

import java.util.ArrayList;
import java.util.List;

public final class ExamplePStackTest
{
  @Test
  public void testAdd()
  {
    final ImmutableExamplePStackType.Builder b =
            ImmutableExamplePStackType.builder();

    b.addIntegers(Integer.valueOf(0));
    b.addIntegers(Integer.valueOf(1));
    b.addIntegers(Integer.valueOf(2));

    final ImmutableExamplePStackType a0 = b.build();
    Assert.assertEquals(2L, a0.integers().get(0).longValue());
    Assert.assertEquals(1L, a0.integers().get(1).longValue());
    Assert.assertEquals(0L, a0.integers().get(2).longValue());
  }

  @Test
  public void testAddVarArgs()
  {
    final ImmutableExamplePStackType.Builder b =
            ImmutableExamplePStackType.builder();

    b.addIntegers(
            Integer.valueOf(0),
            Integer.valueOf(1),
            Integer.valueOf(0), // add a duplicate
            Integer.valueOf(2));

    final ImmutableExamplePStackType a0 = b.build();
    Assert.assertEquals(2L, a0.integers().get(0).longValue());
    Assert.assertEquals(0L, a0.integers().get(1).longValue());
    Assert.assertEquals(1L, a0.integers().get(2).longValue());
    Assert.assertEquals(0L, a0.integers().get(3).longValue());
  }

  @Test
  public void testAddAll()
  {
    final ImmutableExamplePStackType.Builder b =
            ImmutableExamplePStackType.builder();
    List<Integer> integerList = new ArrayList<>();
    integerList.add(Integer.valueOf(0));
    integerList.add(Integer.valueOf(1));
    integerList.add(Integer.valueOf(2));
    b.addAllIntegers(integerList);

    final ImmutableExamplePStackType a0 = b.build();
    Assert.assertEquals(2L, a0.integers().get(0).longValue());
    Assert.assertEquals(1L, a0.integers().get(1).longValue());
    Assert.assertEquals(0L, a0.integers().get(2).longValue());
  }
}
