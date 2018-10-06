/*
 * Copyright (C) 2016 Piotr Wittchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pwittchen.infinitescroll.library;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InfiniteScrollListenerTest {

  @Mock
  private LinearLayoutManager manager;

  @Test(expected = IllegalArgumentException.class)
  public void testShouldNotSetInfiniteScrollListenerWhenLayoutManagerIsNull() {
    // given
    int itemsPerRequest = 1;

    // when
    createListener(null, itemsPerRequest);

    // then throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldNotSetInfiniteScrollListenerWhenItemsPerRequestAreEqualToZero() {
    // given
    int itemsPerRequest = 0;

    // when
    createListener(manager, itemsPerRequest);

    // then throw an exception
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldNotSetInfiniteScrollListenerWhenItemsPerRequestAreLowerThanZero() {
    // given
    int itemsPerRequest = -1;

    // when
    createListener(manager, itemsPerRequest);

    // then throw an exception
  }

  @Test
  public void testCanLoadMoreItemsLogicIsCorrect() {
    // given
    int visibleItemsCount = 10;
    int totalItemsCount = 150;
    int pastVisibleItemsCount = 20;
    int itemsPerRequest = 100;
    InfiniteScrollListener listener = createListener(manager, itemsPerRequest);

    // when
    when(manager.getChildCount()).thenReturn(visibleItemsCount);
    when(manager.getItemCount()).thenReturn(totalItemsCount);
    when(manager.findFirstVisibleItemPosition()).thenReturn(pastVisibleItemsCount);
    boolean lastItemShown = visibleItemsCount + pastVisibleItemsCount > totalItemsCount;
    boolean canLoadMoreItemsExpected = lastItemShown && totalItemsCount >= itemsPerRequest;
    boolean canLoadMoreItems = listener.canLoadMoreItems();

    // then
    assertThat(canLoadMoreItems).isEqualTo(canLoadMoreItemsExpected);
  }

  @Test
  public void testCanLoadMoreItemsShouldBeTrue() {
    // given
    int visibleItemsCount = 10;
    int totalItemsCount = 20;
    int pastVisibleItemsCount = 15;
    int itemsPerRequest = 10;
    InfiniteScrollListener listener = createListener(manager, itemsPerRequest);

    // when
    when(manager.getChildCount()).thenReturn(visibleItemsCount);
    when(manager.getItemCount()).thenReturn(totalItemsCount);
    when(manager.findFirstVisibleItemPosition()).thenReturn(pastVisibleItemsCount);
    boolean canLoadMoreItems = listener.canLoadMoreItems();

    // then
    assertThat(canLoadMoreItems).isTrue();
  }

  @Test
  public void testCanLoadMoreItemsShouldBeFalse() {
    // given
    int visibleItemsCount = 10;
    int totalItemsCount = 30;
    int pastVisibleItemsCount = 15;
    int itemsPerRequest = 100;
    InfiniteScrollListener listener = createListener(manager, itemsPerRequest);

    // when
    when(manager.getChildCount()).thenReturn(visibleItemsCount);
    when(manager.getItemCount()).thenReturn(totalItemsCount);
    when(manager.findFirstVisibleItemPosition()).thenReturn(pastVisibleItemsCount);
    boolean canLoadMoreItems = listener.canLoadMoreItems();

    // then
    assertThat(canLoadMoreItems).isFalse();
  }

  @NonNull
  private InfiniteScrollListener createListener(LinearLayoutManager manager, int itemsPerRequest) {
    return new InfiniteScrollListener(itemsPerRequest, manager) {
      @Override
      public void onScrolledToEnd(int firstVisibleItemPosition) {
      }
    };
  }
}