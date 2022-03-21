package org.dominokit.samples.shell.shared.model;

public class MenuEntry {

    private String label;
    private String token;

    public MenuEntry() {
    }

    public MenuEntry(String label, String token) {
        this.label = label;
        this.token = token;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
