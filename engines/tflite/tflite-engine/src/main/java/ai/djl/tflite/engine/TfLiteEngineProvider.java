/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ai.djl.tflite.engine;

import ai.djl.engine.Engine;
import ai.djl.engine.EngineProvider;

/** {@code TfLiteEngineProvider} is the TFLite implementation of {@link EngineProvider}. */
public class TfLiteEngineProvider implements EngineProvider {

    private volatile Engine engine; // NOPMD
    private volatile boolean initialized; // NOPMD

    /** {@inheritDoc} */
    @Override
    public String getEngineName() {
        return TfLiteEngine.ENGINE_NAME;
    }

    /** {@inheritDoc} */
    @Override
    public int getEngineRank() {
        return TfLiteEngine.RANK;
    }

    /** {@inheritDoc} */
    @Override
    public Engine getEngine() {
        if (!initialized) {
            synchronized (TfLiteEngineProvider.class) {
                if (!initialized) {
                    initialized = true;
                    engine = TfLiteEngine.newInstance();
                }
            }
        }
        return engine;
    }
}
