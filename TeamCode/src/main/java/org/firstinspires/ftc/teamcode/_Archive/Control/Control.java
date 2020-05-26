package org.firstinspires.ftc.teamcode._Archive.Control;

import org.firstinspires.ftc.teamcode._Archive._ArchiveRoot;

public abstract class Control extends _ArchiveRoot
{
    //Variables that apply to all controls


    //Methods that apply to all controls
    @Override
    public abstract void Init();

    public abstract void Start();
    public abstract void Loop();
}