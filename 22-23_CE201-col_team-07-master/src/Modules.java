public enum Modules {
    CE101_4_FY(2, "CE101-4-FY"),
    CE101_4_SP(3, "CE101-4-SP"),
    CE141_4_AU(4, "CE141-4-AU"),
    CE141_4_FY(5, "CE141-4-FY"),
    CE142_4_AU(6, "CE142-4-AU"),
    CE142_4_FY(7, "CE142-4-FY"),
    CE151_4_AU(8, "CE151-4-AU"),
    CE152_4_SP(9, "CE152-4-SP"),
    CE153_4_AU(10, "CE153-4-AU"),
    CE154_4_SP(11, "CE154-4-SP"),
    CE155_4_SP(12, "CE155-4-SP"),
    CE161_4_AU(13, "CE161-4-AU"),
    CE162_4_SP(14, "CE162-4-SP"),
    CE163_4_AU(15, "CE163-4-AU"),
    CE164_4_SP(16, "CE164-4-SP");

    private int val;
    private String name;
    Modules(int val, String name) {
        this.val = val;
        this.name = name;
    }

    public int getIndex() {
        return val;
    }
    public String getName() { return name; }

    public static Modules fromIndex(int i) {
        switch (i) {
            case 0:
            case 1:
            default:
                return null;
            case 2:
                return CE101_4_FY;
            case 3:
                return CE101_4_SP;
            case 4:
                return CE141_4_AU;
            case 5:
                return CE141_4_FY;
            case 6:
                return CE142_4_AU;
            case 7:
                return CE142_4_FY;
            case 8:
                return CE151_4_AU;
            case 9:
                return CE152_4_SP;
            case 10:
                return CE153_4_AU;
            case 11:
                return CE154_4_SP;
            case 12:
                return CE155_4_SP;
            case 13:
                return CE161_4_AU;
            case 14:
                return CE162_4_SP;
            case 15:
                return CE163_4_AU;
            case 16:
                return CE164_4_SP;
        }
    }
}