package com.steps.ui;

import net.thucydides.core.steps.ScenarioSteps;

public class AbstractSteps extends ScenarioSteps {
    private static final long serialVersionUID = 1L;

    protected void refreshPage() {
        getDriver().navigate().refresh();
    }

}
