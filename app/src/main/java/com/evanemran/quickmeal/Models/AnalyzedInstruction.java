package com.evanemran.quickmeal.Models;

import java.util.List;

public class AnalyzedInstruction {
    public String name;
    public List<Step> steps;

    public String getName() {
        return name;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
