package org.firstinspires.ftc.teamcode.Core;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config
@TeleOp
public class PIDTesting extends OpMode {
    PID pid;
    IMU imu;
    FtcDashboard dashboard;

    public static double p = 0;
    public static double i = 0;
    public static double d = 0;

    @Override
    public void init() {
        pid = new PID(p,i,d);
        imu = new IMU(this);
        imu.Start();
        dashboard = FtcDashboard.getInstance(); 
        dashboard.setTelemetryTransmissionInterval(25);
    }

    @Override
    public void loop() {
        pid.setPID(p,i,d);
        double pidVal = pid.getOutput(0, imu.GetAngularVelocity());
        telemetry.addData("angular vel ", imu.GetAngularVelocity());
        telemetry.addData("pid offset ", pidVal);
        telemetry.addData("p ", p);
        telemetry.addData("i ", i);
        telemetry.addData("d ", d);
        telemetry.update();

        TelemetryPacket packet = new TelemetryPacket();
        Canvas fieldOverlay = packet.fieldOverlay();

        packet.put("angular vel", imu.GetAngularVelocity());
        packet.put("pid offset", pidVal);
        dashboard.sendTelemetryPacket(packet);

    }
}
