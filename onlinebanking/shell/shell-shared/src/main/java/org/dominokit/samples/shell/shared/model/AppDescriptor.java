package org.dominokit.samples.shell.shared.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppDescriptor {

    private String name;
    private String script;
    private String healthCheckUrl;
    private List<MenuEntry> menuEntries = new ArrayList<>();

    public AppDescriptor() {
    }

    public AppDescriptor(String name, String script) {
        this.name = name;
        this.script = script;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getHealthCheckUrl() {
        return healthCheckUrl;
    }

    public void setHealthCheckUrl(String healthCheckUrl) {
        this.healthCheckUrl = healthCheckUrl;
    }

    public List<MenuEntry> getMenuEntries() {
        return menuEntries;
    }

    public void setMenuEntries(List<MenuEntry> menuEntries) {
        this.menuEntries = menuEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppDescriptor that = (AppDescriptor) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(script, that.script);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, script);
    }

    @Override
    public String toString() {
        return "AppDescriptor{" +
                "\nname='" + name + '\'' +
                "\n, script='" + script + '\'' +
                "\n, healthCheckUrl='" + healthCheckUrl + '\'' +
                '}';
    }
}
