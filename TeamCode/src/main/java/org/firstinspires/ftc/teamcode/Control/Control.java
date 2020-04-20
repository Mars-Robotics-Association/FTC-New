package org.firstinspires.ftc.teamcode.Control;

import org.firstinspires.ftc.teamcode._Root;

public abstract class Control extends _Root
{
    //Variables that apply to all controls


    //Methods that apply to all controls
    @Override
    public void Init() {

    }

    public abstract void Start();
    public abstract void Loop();
}