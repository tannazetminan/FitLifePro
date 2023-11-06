package com.example.fitlifepro;

public class FitnessLvl {
    private String LvlName;
    private String LvlDesc;

    public FitnessLvl(String lvlName, String lvlDesc) {
        LvlName = lvlName;
        LvlDesc = lvlDesc;
    }

    public String getLvlName() {
        return LvlName;
    }

    public void setLvlName(String lvlName) {
        LvlName = lvlName;
    }

    public String getLvlDesc() {
        return LvlDesc;
    }

    public void setLvlDesc(String lvlDesc) {
        LvlDesc = lvlDesc;
    }
}
