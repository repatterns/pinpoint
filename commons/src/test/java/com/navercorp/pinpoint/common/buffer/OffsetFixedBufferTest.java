/*
 * Copyright 2014 NAVER Corp.
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
 */

package com.navercorp.pinpoint.common.buffer;

import junit.framework.Assert;

import org.junit.Test;

import com.navercorp.pinpoint.common.buffer.Buffer;
import com.navercorp.pinpoint.common.buffer.FixedBuffer;
import com.navercorp.pinpoint.common.buffer.OffsetFixedBuffer;

/**
 * @author emeroad
 */
public class OffsetFixedBufferTest {

    @Test
    public void testFixedBuffer() throws Exception {
        new OffsetFixedBuffer(new byte[10], 10);
        try {
            new OffsetFixedBuffer(new byte[10], 11);
            Assert.fail();
        } catch (IndexOutOfBoundsException ignore) {
        }
        try {
            new OffsetFixedBuffer(new byte[10], -1);
            Assert.fail();
        } catch (IndexOutOfBoundsException ignore) {
        }
    }

    @Test
    public void testGetBuffer() throws Exception {
        final int putValue = 10;
        Buffer buffer = new OffsetFixedBuffer(new byte[10], 2);
        buffer.put(putValue);
        byte[] intBuffer = buffer.getBuffer();
        Assert.assertEquals(intBuffer.length, 4);

        Buffer read = new FixedBuffer(intBuffer);
        int value = read.readInt();
        Assert.assertEquals(putValue, value);
    }
}
