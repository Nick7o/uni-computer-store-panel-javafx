package com.pobprojekt.javapobprojekt.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ClassType {
    None(true, null),
    Product(true, null),
        Peripheral(true, Product),
            Monitor(false, Peripheral),
            Mouse(false, Peripheral),
        ComputerHardware(true, Product),
            Processor(true, ComputerHardware),
                CPU(false, Processor),
                GPU(false, Processor),
            Memory(true, ComputerHardware),
                RAM(false, Memory),
                Disk(false, Memory);

    private boolean isAbstract;
    private ClassType parent;

    ClassType(boolean isAbstract, ClassType parent) {
        this.isAbstract = isAbstract;
        this.parent = parent;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public boolean isPeripheral() {
        return this == Peripheral || this == Monitor || this == Mouse;
    }

    public boolean isComputerHardware(){
        return this == ComputerHardware || isProcessor() || isMemory();
    }

    public boolean isProcessor() {
        return this == Processor || this == CPU || this == GPU;
    }

    public boolean isMemory(){
        return this == Memory || this == Disk || this == RAM;
    }

    public boolean isChildOf(ClassType other) {
        var classType = parent;
        while (classType != null) {
            if (classType == other)
                return true;

            classType = classType.parent;
        }

        return false;
    }

    @Override
    public String toString() {
        switch (this) {
            case None:
                return "Nic";
            case Product:
                return "Produkt";
            case Peripheral:
                return "Peryferia";
            case Monitor:
                return "Monitor";
            case Mouse:
                return "Myszka";
            case ComputerHardware:
                return "Sprzęt";
            case Processor:
                return "Układ obliczeniowy";
            case CPU:
                return "CPU";
            case GPU:
                return "GPU";
            case Memory:
                return "Pamięć";
            case RAM:
                return "RAM";
            case Disk:
                return "Dysk";
        }

        return "";
    }

    public static List<ClassType> getClassTypes(boolean displayAbstractTypes) {
        return Arrays.stream(ClassType.values())
                .filter(classType -> classType != None && (!classType.isAbstract() || displayAbstractTypes))
                .collect(Collectors.toList());
    }
}
