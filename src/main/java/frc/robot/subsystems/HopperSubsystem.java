/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.telemetry.ITelemetryProvider;
import frc.robot.telemetry.TelemetryNames;

public class HopperSubsystem extends SubsystemBase implements ITelemetryProvider {
  
  private static final String myName = TelemetryNames.Hopper.name;

  private static HopperSubsystem ourInstance;

  public static synchronized void constructInstance() {
    SmartDashboard.putBoolean(TelemetryNames.Hopper.status, false);

    if (ourInstance != null) {
      throw new IllegalStateException(myName + " already constructed");
    }

    ourInstance = new HopperSubsystem();

    SmartDashboard.putBoolean(TelemetryNames.Hopper.status, true);
  }

  /**
   * Creates a new HopperSubsystem.
   */
  public static HopperSubsystem getInstance() {
    
    if (ourInstance == null) {
      throw new IllegalStateException(myName + " not constructed yet");
    }

    return ourInstance;
  }

  private TalonSRX agitator;

  public HopperSubsystem() {
    agitator = new TalonSRX(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runAgitator(double speed) {
    agitator.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void updateTelemetry() {
    // TODO Auto-generated method stub

  }
}
