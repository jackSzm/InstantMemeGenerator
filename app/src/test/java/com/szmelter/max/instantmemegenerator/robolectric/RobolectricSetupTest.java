package com.szmelter.max.instantmemegenerator.robolectric;

import com.szmelter.max.instantmemegenerator.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class RobolectricSetupTest {

    @Test
    public void robolectricSetupIsCorrect() {
        assertThat(RuntimeEnvironment.application).isNotNull();
    }
}
