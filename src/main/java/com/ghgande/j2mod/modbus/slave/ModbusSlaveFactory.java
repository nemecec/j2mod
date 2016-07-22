/*
 * Copyright 2002-2016 jamod & j2mod development teams
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ghgande.j2mod.modbus.slave;

import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.net.AbstractModbusListener;
import com.ghgande.j2mod.modbus.serial.ModSerialParameters;
import com.ghgande.j2mod.modbus.util.ModCollections;
import com.ghgande.j2mod.modbus.util.ModbusUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Class that implements a <tt>ModbusIOException</tt>. Instances of this
 * exception are thrown when errors in the I/O occur.
 *
 * @author Steve O'Hara (4energy)
 * @version 2.0 (March 2016)
 */
public class ModbusSlaveFactory {

    private static final Logger logger = LoggerFactory.getLogger(ModbusSlaveFactory.class);
    private static Hashtable slaves = new Hashtable();

    /**
     * Creates a serial modbus slave or returns the one already allocated to this port
     *
     * @param serialParams Serial parameters for serial type slaves
     * @return new or existing Serial modbus slave associated with the port
     * @throws ModbusException If a problem occurs e.g. port already in use
     */
    public static synchronized ModbusSlave createSerialSlave(ModSerialParameters serialParams) throws ModbusException {
        if (serialParams == null) {
            throw new ModbusException("Serial parameters are null");
        }
        else if (ModbusUtil.isBlank(serialParams.getPortName())) {
            throw new ModbusException("Serial port name is empty");
        }
        if (slaves.containsKey(serialParams.getPortName())) {
            return (ModbusSlave) slaves.get(serialParams.getPortName());
        }
        else {
            ModbusSlave slave = new ModbusSlave(serialParams);
            slaves.put(serialParams.getPortName(), slave);
            return slave;
        }
    }

    /**
     * Closes this slave and removes it from the running list
     *
     * @param slave Slave to remove
     */
    public static synchronized void close(ModbusSlave slave) {
        if (slave != null) {
            slave.closeListener();
            slaves.remove(slave.getType().getKey(slave.getPort()));
        }
    }

    /**
     * Closes all slaves and removes them from the running list
     */
    public static synchronized void close() {
        Vector slaveList = ModCollections.getValues(slaves);
        for (int i = 0; i < slaveList.size(); i++) {
            ModbusSlave slave = (ModbusSlave) slaveList.elementAt(i);
            slave.close();
        }
    }

    /**
     * Returns the running slave listening on the given IP port
     *
     * @param port Port to check for running slave
     * @return Null or ModbusSlave
     */
    public static ModbusSlave getSlave(int port) {
        return (ModbusSlave) slaves.get(port + "");
    }

    /**
     * Returns the running slave listening on the given serial port
     *
     * @param port Port to check for running slave
     * @return Null or ModbusSlave
     */
    public static ModbusSlave getSlave(String port) {
        return ModbusUtil.isBlank(port) ? null : (ModbusSlave) slaves.get(port);
    }

    /**
     * Returns the running slave that utilises the give listener
     *
     * @param listener Listener used for this slave
     * @return Null or ModbusSlave
     */
    public static synchronized ModbusSlave getSlave(AbstractModbusListener listener) {
        Enumeration values = slaves.elements();
        while (values.hasMoreElements()) {
            ModbusSlave slave = (ModbusSlave) values.nextElement();
            if (slave.getListener().equals(listener)) {
                return slave;
            }
        }
        return null;
    }

}
