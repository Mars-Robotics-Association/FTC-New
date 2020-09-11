package org.firstinspires.ftc.teamcode.Core;


public interface ControllerInputListener {

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
