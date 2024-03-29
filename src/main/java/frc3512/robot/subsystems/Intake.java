package frc3512.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc3512.lib.util.CANSparkMaxUtil;
import frc3512.lib.util.CANSparkMaxUtil.Usage;
import frc3512.robot.Constants;

public class Intake extends SubsystemBase {
  private CANSparkMax intakeMotor =
      new CANSparkMax(
          Constants.IntakeConstants.intakeMotorID, CANSparkLowLevel.MotorType.kBrushless);

  public Intake() {
    intakeMotor.restoreFactoryDefaults();

    CANSparkMaxUtil.setCANSparkMaxBusUsage(intakeMotor, Usage.kMinimal);
    intakeMotor.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);
    intakeMotor.setSmartCurrentLimit(Constants.IntakeConstants.currentLimit);
    intakeMotor.enableVoltageCompensation(Constants.GeneralConstants.voltageComp);

    intakeMotor.burnFlash();
  }

  public Command intakeGamePiece() {
    return run(
        () -> {
          intakeMotor.set(Constants.IntakeConstants.motorSpeed);
        });
  }

  public Command outtakeGamePiece() {
    return run(
        () -> {
          intakeMotor.set(-Constants.IntakeConstants.motorSpeed);
        });
  }

  public Command halfOuttakeGamePiece() {
    return run(
        () -> {
          intakeMotor.set(Constants.IntakeConstants.motorSpeed * 0.5);
        });
  }

  public Command stopIntake() {
    return run(
        () -> {
          intakeMotor.set(0.0);
        });
  }
}
