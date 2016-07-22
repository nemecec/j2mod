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

import com.ghgande.j2mod.modbus.util.ModbusUtil;

/**
 * Descibes the types of Modbus Slaves
 */
public class ModbusSlaveType {

    public final static ModbusSlaveType TCP = new ModbusSlaveType("TCP");
    public final static ModbusSlaveType UDP = new ModbusSlaveType("UDP");
    public final static ModbusSlaveType SERIAL = new ModbusSlaveType("SERIAL");

    private final String name;

    public ModbusSlaveType(String name) {
        this.name = name;
    }

    /**
     * Returns true if this type is one of those listed
     *
     * @param types Array of types to check for
     * @return True if this is one of the array
     */
    public boolean is(ModbusSlaveType[] types) {
        if (!ModbusUtil.isBlank(types)) {
            for (int i = 0; i < types.length; i++) {
                if (equals(types[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a unique key for this port and type
     *
     * @param port Port number
     * @return Unique key
     */
    public String getKey(int port) {
        return toString() + port;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object obj) {
        return this.name.equals(((ModbusSlaveType)obj).name);
    }
}
