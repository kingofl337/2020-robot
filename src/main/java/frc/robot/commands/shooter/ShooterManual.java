/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.commands.PKManualCommand;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ShooterManual extends PKManualCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ShooterManual.class.getName());

    // Handle to our subsystem
    private IShooterSubsystem shooter;

    public ShooterManual() {
        logger.info("constructing {}", getName());

        shooter = ShooterFactory.getInstance();
        addRequirements(shooter);

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();

        double speed = oi.getShooterSpeed();
        shooter.setSpeed(29, speed);

        speed = oi.getTurretSpeed();
        shooter.setSpeed(20, speed);
    }

}
