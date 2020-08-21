package org.firstinspires.ftc.teamcode.Core;

import org.firstinspires.ftc.teamcode._Archive.Input.EventInputListener;

public interface ControllerInputListener extends EventInputListener {
    @Override
    public void Started();
    @Override
    public void Stopped();

    void APressed();
    void BPressed();
    void XPressed();
    void YPressed();

    void AHeld();
    void BHeld();
    void XHeld();
    void YHeld();

    void AReleased();
    void BReleased();
    void XReleased();
    void YReleased();

    void LBPressed();
    void RBPressed();
    void LTPressed();
    void RTPressed();

    void LBHeld();
    void RBHeld();
    void LTHeld();
    void RTHeld();

    void LBReleased();
    void RBReleased();
    void LTReleased();
    void RTReleased();
}
