/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;

public class StubShooterSubsystem implements IShooterSubsystem {

    private static final String myName = TelemetryNames.Drive.name;

    private static IShooterSubsystem ourInstance;

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.Shooter.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new StubShooterSubsystem();

        SmartDashboard.putBoolean(TelemetryNames.Shooter.status, true);
    }

    public static IShooterSubsystem getInstance() {

        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }

        return ourInstance;
    }

    public StubShooterSubsystem() {
    }

    @Override
    public void periodic() {
        // TODO Auto-generated method stub

    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        // TODO Auto-generated method stub

    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTelemetry() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

}