package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Shooter {

    TalonFX motor;
    TalonFXConfiguration config;

    public Shooter() {
        motor = new TalonFX(0);

        config = new TalonFXConfiguration()
                .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Coast));
        motor.getConfigurator().apply(config);
    }

    public void setMotor(double speed) {
        motor.set(speed);
    }

    public void stopMotor() {
        motor.set(0);
    }

}
