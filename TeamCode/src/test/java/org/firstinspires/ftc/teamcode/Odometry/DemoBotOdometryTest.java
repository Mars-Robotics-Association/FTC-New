package org.firstinspires.ftc.teamcode.Odometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoBotOdometryTest {

    @Test
    void reset() {
    }

    @Test
    void setTargetDist() {

    }

    @Test
    void isAtTargetDist() {
        DemoBotOdometry odometry = new DemoBotOdometry();
        odometry.Reset();
        odometry.SetTargetDist(5);
        odometry.SetEncoderValsDirect(3,4);
        assertEquals(true, odometry.IsAtTargetDist(1));
    }
}