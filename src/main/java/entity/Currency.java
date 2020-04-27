package entity;

public class Currency {
    public static final String gbPounds = "gbPounds";
    public static final String usDollars = "usDollars";
    public static final String euros = "euros";

    private int id;
    private String currencyType;
    private String displayName;

    public Currency() {
    }

    public Currency(int id, String currencyType, String displayName) {
        this.id = id;
        this.currencyType = currencyType;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
