# Controls Layout / Interface

(This is an example start made by Thad. Feel free to delete it all. Delete this comment when you're happy with what it looks like)

Types of inputs:
- [digital] - when pressed, active
- [toggle]    - pressing toggles between active and inactive
- [analog]    - analog value

# Driver
## [analog]  **Stick** Left Joystick  U-D: Drivetrain Fwd/Backwards
## [analog]  **Stick** Right Joystick L-R: Drivetrain Left/Right
## [digital] **button** Drivetrain Turbo
### [action] Allow FWD/REV Max Speed to be 100% _May be Unused in 2020_
## [digital] **button** Drievtrain Crawl
### [action] Allow FWD/REV Max Speed to be 20%
## [digital] **button** Reverse Lift _Should be in odd location_
## [analog] **trigger** Intake FWD
### [action] Run Intake Rollers in FWD and Hopper Motor at Scale to 50% - 100%
## [analog] **trigger** Intake REV
### [action] Run Intake Rollers in REV and Hopper Motor at Scale to Max 50%
## [toggle]  **button** Flip Drivetrain
### [action] Flip the control of the robot swap the _Front_ of the robot.
## [digital] ## Deploy Intake Rollers
### [action] Run Intake Lift Motor FWD _Need Softlimits_
## [digital] ## Retract Intake Rollers
### [action] Run Intake Lift Motor Rev _Need Softlimits_

# Operator
## [digital] - **Switch** Spool Shooter to Shot Speed / Idle Shooter
### [Action] - Based on Preset or Auto Set Shooter Speed or Idle at 20%
## [digital] - **Switch** Preset Position / Camera Aim
### [Action] - POS1: _default preset 1_ Turret PID & WheelSpeed Setting 
### [Action] - POS2: Allow camera to control Turret/Wheel speed
## [analog] - **POT** Course Posistion Adjust
### [action] - Convert to whole numbers, and redduce t0 50 ticks over 270 deg sweep, ignore unless moved. .6 Deg per tick
## [analog] - **POT** Fine Posistion Adjust 
### [action] - Convert to whole numbers, and redduce t0 50 ticks over 270 deg sweep, ignore unless moved. .06 Deg per tick
## [digital] - **Button** FIRE!
### [action] - Check shooter at preset velocity +/- 2%, turret or camera _on target_ 
### [action] - run balivator, run intake, run hopper motor
## [digital] **Button** Reverse Ballevator
### [action] - Run Ballivator in reverse, run hopper motor
## [digital] **Button** Turret / Velocity Preset 1
### [action] - Set Turret Target / Set Velocity Target
## [digital] **Button** Turret / Velocity Preset 2
### [action] - Set Turret Target / Set Velocity Target
## [digital] **Button** Turret / Velocity Preset 3
### [action] - Set Turret Target / Set Velocity Target
## [digital] **Button** Turret / Velocity Preset 4
### [action] - Set Turret Target / Set Velocity Target
## [digital] **Button** Re-Home Turret
### [action] - Send Turret Home
## [digital] **Button** Run Climber
### [action] - Run Climber if FWD Direction 100% speed
