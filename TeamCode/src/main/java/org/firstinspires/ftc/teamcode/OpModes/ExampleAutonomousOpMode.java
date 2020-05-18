package org.firstinspires.ftc.teamcode.OpModes;

public class ExampleAutonomousOpMode extends AutonomousOpMode
{

    @Override
    public void init() {
        InitAutonomousControl();
    }

    @Override
    public void start() {
        StartAutonomousControl();
    }

    @Override
    public void loop() {
        LoopAutonomousControl();
    }
}