/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import org.slf4j.Logger;

import frc.robot.commands.PKManualCommand;
import frc.robot.subsystems.intake.IIntakeSubsystem;
import frc.robot.subsystems.intake.IntakeFactory;

import riolog.RioLogger;

public class IntakeSimpleManual extends PKManualCommand {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(IntakeSimpleManual.class.getName());

    // Handle to our subsystem
    private IIntakeSubsystem intake;

    public IntakeSimpleManual() {
        logger.info("constructing {}", getName());

        intake = IntakeFactory.getInstance();
        addRequirements(intake);

        logger.info("constructed");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        super.execute();

        double speed = oi.getIntakeSpeed();
        intake.pullIn(speed);
    }

}
