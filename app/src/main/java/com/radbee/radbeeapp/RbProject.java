package com.radbee.radbeeapp;

import java.util.List;

public class RbProject {

    private String projectName;
    private String ProjectDescription;
    private List<RbAdbCommand> rbAdbCommandList;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return ProjectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        ProjectDescription = projectDescription;
    }

    public List<RbAdbCommand> getRbAdbCommandList() {
        return rbAdbCommandList;
    }

    public void setRbAdbCommandList(List<RbAdbCommand> rbAdbCommandList) {
        this.rbAdbCommandList = rbAdbCommandList;
    }

    public void addRbAdbCommand(RbAdbCommand rbAdbCommand) {
        rbAdbCommandList.add(rbAdbCommand);
    }
}
