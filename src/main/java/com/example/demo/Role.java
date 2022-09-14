package com.example.demo;

public enum Role {
    ADMIN("ADMIN"), 
    OPERARIO("OPERARIO");

    private String label;

    Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static Role fromLabel(String label) {
        for (Role currentRole : Role.values()) {
            if (currentRole.getLabel().equals(label))
                return currentRole;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
