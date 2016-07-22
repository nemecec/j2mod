package com.ghgande.j2mod.modbus.serial;

import com.ghgande.j2mod.modbus.util.ModProperties;

public class MeSerialParameters extends ModSerialParameters {

  public MeSerialParameters() {
    super();
  }

  public MeSerialParameters(String portName, int baudRate, int flowControlIn, int flowControlOut, int databits,
    int stopbits, int parity, boolean echo) {
    super(portName, baudRate, flowControlIn, flowControlOut, databits, stopbits, parity, echo);
  }

  public MeSerialParameters(ModProperties props, String prefix) {
    super(props, prefix);
  }

  public ModSerialPort getCommPort() {
    return new MeSerialPort(getPortName());
  }

}
