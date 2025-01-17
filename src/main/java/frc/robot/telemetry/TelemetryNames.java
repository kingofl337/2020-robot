/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.telemetry;

import frc.robot.modules.ModuleNames;
import frc.robot.sensors.SensorNames;
import frc.robot.subsystems.SubsystemNames;

public final class TelemetryNames {

    public final class Misc {
        public static final String name = "Misc";

        public static final String programmer = name + ".programmer";
        public static final String codeVersion = name + ".codeVersion";
        public static final String robotName = name + ".robotName";
    }

    /***************
     * Modules
     ***************/

    public final class PDP {
        public static final String name = "PDP";

        public static final String status = name + ".status";
        public static final String busVoltage = name + ".busVoltage";
        public static final String totalCurrent = name + ".totalCurrent";
        public static final String totalEnergy = name + ".totalEnergy";
    }

    /***************
     * Managers
     ***************/

    public final class Telemetry {
        public static final String name = "Telemetry";

        public static final String status = name + ".status";
    }

    public final class Preferences {
        public static final String name = "Preferences";

        public static final String status = name + ".status";
    }

    public final class Properties {
        public static final String name = "Properties";

        public static final String status = name + ".status";
        public static final String robot = name + ".robot";
    }

    public static class Scheduler {
        public static final String name = "Scheduler";

        public static final String status = name + ".status";
        // The current commands running on the robot
        public static final String currentCommands = name + ".currentCommands";
    }

    /***************
     * OI
     ***************/

    public final class OI {
        public static final String name = "OI";

        public static final String status = name + ".status";
    }

    public static class HMI {
        private static final String name = SubsystemNames.hmiName;

        public static final String rawSpeed = name + ".rawSpeed";
        public static final String rawTurn = name + ".rawTurn";
        public static final String turbo = name + ".turbo";
        public static final String crawl = name + ".crawl";
        public static final String reversed = name + ".reversed";
        public static final String oiSpeed = name + ".oiSpeed";
        public static final String oiTurn = name + ".oiTurn";

        public static final String speed = name + ".speed";
        public static final String turn = name + ".turn";
    }

    /***************
     * Drive
     ***************/

    public final class Drive {
        public static final String name = SubsystemNames.driveName;

        public static final String status = name + ".status";
    }

    public final class Gyro {
        public static final String name = SensorNames.gyroName;

        public static final String status = name + ".status";
        public static final String roll = name + ".roll";
        public static final String pitch = name + ".pitch";
        public static final String yaw = name + ".yaw";
        public static final String angle = name + ".angle";
        public static final String heading = name + ".heading";
    }

    /***************
     * Power Cells
     ***************/

    public final class Intake {
        public static final String name = SubsystemNames.intakeName;

        public static final String status = name + ".status";
    }

    public final class Hopper {
        public static final String name = SubsystemNames.hopperName;

        public static final String status = name + ".status";
    }

    public final class Ballevator {
        public static final String name = SubsystemNames.ballevatorName;

        public static final String status = name + ".status";
    }

    public final class Shooter {
        public static final String name = SubsystemNames.shooterName;

        public static final String status = name + ".status";
        public static final String angle = name + ".angle";
    }

    /***************
     * Control Panel
     ***************/

    public final class Wheel {
        public static final String name = SubsystemNames.wheelName;

        public static final String status = name + ".status";
    }

    public final class WheelColor {
        public static final String name = SensorNames.wheelColorName;

        public static final String status = name + ".status";
        // Raw color returned from sensor
        public static final String color = name + ".color";
        // Filtered matched color returned from software
        public static final String match = name + ".match";
        // Confidence associated with matched color
        public static final String confidence = name + ".confidence";
    }

    /***************
     * Climber
     ***************/

    public final class Climber {
        public static final String name = SubsystemNames.climberName;

        public static final String status = name + ".status";
        public static final String topLimit = name + ".topLimit";
        public static final String bottomLimit = name + ".bottomLimit";
    }

    /***************
     * Camera
     ***************/

    public final class Camera {
        public static final String name = SensorNames.cameraName;

        public static final String status = name + ".status";
    }

    /***************
     * Vision
     ***************/

    public final class RPI {
        public static final String name = ModuleNames.rpiName;

        //
        public static final String clockSpeed = name + ".clock_speed";
        //
        public static final String freeMemory = name + ".free_memory";
        //
        public static final String socTemp = name + ".soc_temp";
    }

    public final class Vision {
        public static final String name = ModuleNames.visionName;

        // Set by the vision program running on R-PI
        public static final String status = name + ".status";
        // Running count for iterations of program
        public static final String heart_beat = name + ".heart_beat";
        // Valid solution?
        public static final String locked = name + ".locked";
        // Delta offset (+ CCW; rotate CW to fix & - CW; rotate CCW to fix)
        public static final String angle = name + ".angle";
        // Incremented every time
        public static final String count = name + ".count";
    }

}