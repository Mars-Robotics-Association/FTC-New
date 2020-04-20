package org.firstinspires.ftc.teamcode.Sensor;

public abstract class ValueSensor extends Sensor
{
    @Override
    public abstract void Init();

    public abstract void Loop();
    public abstract void Start();
}
